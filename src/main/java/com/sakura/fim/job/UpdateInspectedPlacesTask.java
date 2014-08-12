package com.sakura.fim.job;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sakura.fim.model.InspectedPlace;
import com.sakura.fim.model.InspectedPlacesVersion;
import com.sakura.fim.model.dao.InspectedPlaceDAO;
import com.sakura.fim.model.dao.InspectedPlacesVersionDAO;
import com.sakura.fim.util.notifier.Notifier;
import com.sakura.fim.webservice.client.fim.FIMClient;
import com.sakura.fim.webservice.client.google.GeocodeClient;
import com.sakura.fim.webservice.client.google.response.GoogleGeocodeClientResponse;
import com.sakura.fim.webservice.client.google.response.Result;

/**
 * This job runs once a week and updates the inspections of food in the database
 * User: eduardolomonaco
 */
@Component
public class UpdateInspectedPlacesTask {

    public static final Logger LOGGER = LoggerFactory.getLogger(UpdateInspectedPlacesTask.class.getName());
    
    @Autowired
    private InspectedPlaceDAO ipDAO;
    
    @Autowired
    private Notifier notifier;

    @Autowired
    private InspectedPlacesVersionDAO ipVersionDAO;

    @Autowired
    private GeocodeClient geocodeClient;

    @Autowired
    private FIMClient fimClient;
    
    private UpdateInspectedPlacesTaskResult taskResult = new UpdateInspectedPlacesTaskResult();

    /**
     * Clears database and loads it with new data from inspection client. Run
     * every beggining of month
     */
    // @Scheduled(cron = "0 10 1 1 * ?")
    @Scheduled(cron = "0 14 22 * * ?")
    public void run() {
        taskResult.setExecutionTime(LocalDateTime.now());
        try {
            LOGGER.info("Starting update inspected places task.");
            
            Integer currentVersion = ipDAO.getMaxVersion() + 1;
            taskResult.setCurrentVersion(currentVersion);
            Integer nextInspectedPlaceVersion = currentVersion + 1;
            LOGGER.info(String.format("Next inspected place version is : %d", nextInspectedPlaceVersion));
            List<InspectedPlace> storeWithGeocoding = storeWithGeocoding(taskResult, nextInspectedPlaceVersion);
            validateVersion(nextInspectedPlaceVersion, storeWithGeocoding);
        } finally {
            notifier.send(new UpdateInspectedPlacesNotificationProducer(taskResult));
        }
    }

    private void validateVersion(Integer nextInspectedPlaceVersion, List<InspectedPlace> inspections) {
        List<InspectedPlace> insertedInspectedPlaces = ipDAO.findByVersion(nextInspectedPlaceVersion);
        long insertedInspectedPlacesCount = insertedInspectedPlaces.stream()
            .filter(ip -> ip.getLatitude() != null && ip.getLongitude() != null)
            .count();
        
        taskResult.setValidStoredInspectionsCount(insertedInspectedPlacesCount);
        
        //add many loggers
        InspectedPlacesVersion version = new InspectedPlacesVersion();
        version.setNumber(nextInspectedPlaceVersion);
        version.setValid(insertedInspectedPlacesCount >= inspections.size() * 0.90 ? true : false);
        ipVersionDAO.store(version);
    }

    private List<InspectedPlace> storeWithGeocoding(UpdateInspectedPlacesTaskResult taskResult, Integer nextInspectedPlaceVersion) {
        Map<String, String> geocodingsResult = new HashMap<>();
        List<InspectedPlace> inspections = fimClient.getInspections();
        taskResult.setRetrievedInspectionsCount(inspections.size());
        for (InspectedPlace inspection : inspections) {
            String address = inspection.getAddress() + inspection.getCity();
            GoogleGeocodeClientResponse response = geocodeClient.getMapLocation(address);
            if (GoogleGeocodeClientResponse.RC_OK.equals(response.getStatus())) {
                LOGGER.info(String.format("Geolocalized %s", address));
                Result result = response.getResults().get(0);
                inspection.setLatitude(result.getGeometry().getLocation().getLat());
                inspection.setLongitude(result.getGeometry().getLocation().getLng());
            } else {
                LOGGER.warn(String.format("Could not geo localize entry %s", address));
            }
            inspection.setVersion(nextInspectedPlaceVersion);

            ipDAO.store(inspection);
            geocodingsResult.put(response.getStatus(), inspection.getEstablishment());

            // Google Geocode API free usage does not allow more than 5 Geocodings per second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted thread sleep while waiting to geocode locations", e);
            }
        }
        taskResult.setGeocodingsResult(geocodingsResult);
        return inspections;
    }
}
