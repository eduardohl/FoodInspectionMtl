package com.sakura.fim.webservice.client.fim.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sakura.fim.ConverterUtil;
import com.sakura.fim.model.InspectedPlace;
import com.sakura.fim.webservice.client.fim.FIMClient;
import com.sakura.fim.webservice.client.fim.response.FIMClientInspectedPlace;
import com.sakura.fim.webservice.client.fim.response.FIMClientResponse;

@Service
public class FIMClientImpl implements FIMClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(FIMClientImpl.class.getName());

    @Value("${contrevenants.data.url}")
    private String contrevenantsDataUrl;

    @Override
    public List<InspectedPlace> getInspections() {
        LOGGER.info("Getting inspections from " + contrevenantsDataUrl);

        FIMClientResponse response = new RestTemplate().getForObject(contrevenantsDataUrl, FIMClientResponse.class);
        if (response != null && response.getContrevenants() != null && !response.getContrevenants().isEmpty()) {
            LOGGER.info("Found " + response.getContrevenants().size() + " inspections to save in local database.");
        } else {
            LOGGER.warn("No inspections were returned from " + contrevenantsDataUrl);
        }

        List<InspectedPlace> inspectedPlaces = new ArrayList<InspectedPlace>();
        for (FIMClientInspectedPlace fcip : response.getContrevenants()) {
            InspectedPlace inspectedPlace;
            try {
                inspectedPlace = convertToInspectedPlace(fcip);
                inspectedPlaces.add(inspectedPlace);
            } catch (ParseException e) {
                LOGGER.error(String.format(
                        "Could not parse inspected place from open data set. Please verify entry: %s",
                        fcip.getDescription()), e);
            }
        }

        return inspectedPlaces;
    }

    private InspectedPlace convertToInspectedPlace(FIMClientInspectedPlace fcip) throws ParseException {
        InspectedPlace inspectedPlace = new InspectedPlace();
        inspectedPlace.setAddress(fcip.getAdresse());
        inspectedPlace.setCategory(fcip.getCategorie());
        inspectedPlace.setImportedDate(new Date());
        inspectedPlace.setInfractionDate(ConverterUtil.FIM_FILE_FORMAT.parse(fcip.getDate_infraction()));
        inspectedPlace.setJudgementDate(ConverterUtil.FIM_FILE_FORMAT.parse(fcip.getDate_jugement()));
        inspectedPlace.setDescription(fcip.getDescription());
        inspectedPlace.setEstablishment(fcip.getEtablissement());
        inspectedPlace.setAmount(Integer.parseInt(fcip.getMontant().replace("$", "").trim()));
        inspectedPlace.setOwner(fcip.getProprietaire());
        inspectedPlace.setCity(fcip.getVille());
        return inspectedPlace;
    }
}