package com.sakura.fim.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sakura.fim.model.InspectedPlace;
import com.sakura.fim.model.dao.InspectedPlaceDAO;
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
    private GeocodeClient geocodeClient;

    @Autowired
    private FIMClient fimClient;

    /**
     * Clears database and loads it with new data from inspection client. Run
     * every beggining of month
     */
    @Scheduled(cron = "0 10 1 1 * ?")
    public void run() {
        LOGGER.info("Starting update inspected places task.");

        // TODO setup versioning or safe refresh of database to avoid losing all
        // data
        ipDAO.deleteAll();

        for (InspectedPlace inspection : fimClient.getInspections()) {

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
            ipDAO.store(inspection);

            // Google Geocode API free usage does not allow more than 5
            // Geocodings per second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted thread sleep while waiting to geocode locations", e);
            }
        }
    }
}
