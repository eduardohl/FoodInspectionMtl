package com.sakura.fim.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sakura.fim.model.InspectedPlace;

/**
 * Persistance class for inspected place
 * User: eduardolomonaco
 */
public interface InspectedPlaceDAO {
    
    /**
     * Stores inspected place
     * @param inspectedPlace
     */
    void store(InspectedPlace inspectedPlace);

    /**
     * Query inspected place by dictionary
     * @param params
     * @return
     */
    List<InspectedPlace> query(Map<String, Object> params);
    
    List<InspectedPlace> findByVersion(Integer versionNumber);

    /**
     * Returns a list with all inspected places
     * @return
     */
    List<InspectedPlace> findAll();

    /**
     * Deletes all inspected places from db collection
     */
    void deleteAll();

    /**
     * Query inspected place by dictionary and time range
     * @param params
     * @return
     */
    List<InspectedPlace> queryInDateRange(Map<String, Object> params, Date startDate, Date endDate);

    /**
     * Returns all distinct entries of a field in a collection
     * @param attribute
     * @return
     */
    List<String> getDistinct(String attribute);
    
    /**
     * getMax collection version
     * @param attribute
     * @return
     */
    Integer getMaxVersion();
}
