package com.sakura.fim.service;

import java.util.Date;
import java.util.List;

import com.sakura.fim.model.InspectedPlace;

/**
 * Created with IntelliJ IDEA.
 * User: eduardolomonaco
 */
public interface FoodInspectionService {

    /**
     * Returns all inspected places from repository
     * @return
     */
    List<InspectedPlace> getAllInspectedPlaces();

    /**
     * Get all categories available for Inspected Places
     * @return
     */
    List<String> getCategories();

    /**
     * Get all inspectedplaces by time range and category
     * @param startDate
     * @param endDate
     * @param category
     * @return
     */
    List<InspectedPlace> getInspectedPlacesInRange(Date startDate, Date endDate, String category);
}
