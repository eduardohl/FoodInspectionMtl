package com.sakura.fim.webservice.client.google;

import com.sakura.fim.webservice.client.google.response.GoogleGeocodeClientResponse;

public interface GeocodeClient {
    
    /**
     * 
     * @param locationDescription
     * @return
     */
    GoogleGeocodeClientResponse getMapLocation(String locationDescription);
}