package com.sakura.fim.webservice.client.google;

import com.sakura.fim.webservice.client.google.response.GoogleGeocodeClientResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GoogleGeocodeClient implements GeocodeClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(GoogleGeocodeClient.class.getName());

    @Value("${google.geocode.api.url}")
    private String googleGeocodingApiUrl;

    @Override
    public GoogleGeocodeClientResponse getMapLocation(String locationDescription) {
        LOGGER.debug("Trying to geolocate coordinates for " + locationDescription);

        RestTemplate rest = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJacksonHttpMessageConverter());
        rest.setMessageConverters(list);

        Map<String, String> params = new HashMap<>();
        params.put("address", locationDescription);
        params.put("sensor", "false");

        GoogleGeocodeClientResponse response = rest.getForObject(googleGeocodingApiUrl,
                GoogleGeocodeClientResponse.class, params);

        if (response != null && GoogleGeocodeClientResponse.RC_OK.equals(response.getStatus())) {
            LOGGER.debug("Geolocation found for " + locationDescription);
        } else {
            LOGGER.warn("Geolocation not found for " + locationDescription + " with error message: "
                    + response.getError_message() + " with status " + response.getStatus());
        }

        return response;
    }
}
