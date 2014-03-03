package com.sakura.fim.webservice.client.fim;

import java.util.List;

import com.sakura.fim.model.InspectedPlace;

/**
 * Client that accesses webservice from montreal open data to get access to food
 * inspections reports
 */
public interface FIMClient {

    /**
     * Gets food inspections from montreal open data webservice
     * 
     * @return
     */
    List<InspectedPlace> getInspections();
}
