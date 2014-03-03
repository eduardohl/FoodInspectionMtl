package com.sakura.fim.service.impl;

import com.sakura.fim.model.InspectedPlace;
import com.sakura.fim.model.dao.InspectedPlaceDAO;
import com.sakura.fim.model.dao.impl.InspectedPlaceDAOImpl;
import com.sakura.fim.service.FoodInspectionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodInspectionServiceImpl implements FoodInspectionService {

    public static final Logger LOGGER = LoggerFactory.getLogger(InspectedPlaceDAOImpl.class.getName());
    
    @Autowired
    private InspectedPlaceDAO inspectedPlaceDAO;

    @Override
    public List<InspectedPlace> getAllInspectedPlaces() {
        LOGGER.debug("Getting all inspected places");
        return inspectedPlaceDAO.findAll();
    }
    
    @Override
    public List<InspectedPlace> getInspectedPlacesInRange(Date startDate, Date endDate, String category) {
        LOGGER.debug("Getting inspected places by range and category");
        Map<String, String> params = new HashMap<>();
        params.put("category", category);
        return inspectedPlaceDAO.queryInDateRange(params , startDate, endDate);
    }

    @Override
    public List<String> getCategories() {
        LOGGER.debug("Getting distinct categories");
        return inspectedPlaceDAO.getDistinct("category");
    }
}
