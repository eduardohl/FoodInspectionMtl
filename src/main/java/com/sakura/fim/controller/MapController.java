package com.sakura.fim.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sakura.fim.ConverterUtil;
import com.sakura.fim.model.InspectedPlace;
import com.sakura.fim.service.FoodInspectionService;

/**
 * Controller for Map requested services
 * 
 * @author eduardohl
 * 
 */
@Controller
@RequestMapping("/map")
public class MapController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MapController.class.getName());

    @Autowired
    private FoodInspectionService fimService;

    /**
     * Look up food inspection service and gets a list of inspections
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/ajax/inspected_places", method = RequestMethod.GET)
    @ResponseBody
    public List<InspectedPlace> getInspectedPlaces(
            @RequestParam(required = true, value = "startDate") String startDateString,
            @RequestParam(required = true, value = "endDate") String endDateString,
            @RequestParam(required = true, value = "category") String category, ModelMap model) {
        LOGGER.trace(String.format(
                "Getting inspected places for: StartDateString: %s, EndDateString: %s, category: %s", startDateString,
                endDateString, category));
        try {
            if (isValidInput(startDateString, endDateString, category)) {
                Date startDate = ConverterUtil.VIEW_FORM_FORMAT.parse(startDateString);
                Date endDate = ConverterUtil.VIEW_FORM_FORMAT.parse(endDateString);
                return fimService.getInspectedPlacesInRange(startDate, endDate, category);
            }
        } catch (ParseException e) {
            LOGGER.error(String
                    .format("Error parsing input data for recovering inspected places. StartDateString: %s, EndDateString: %s, category: %s",
                            startDateString, endDateString, category));
        }
        return Collections.emptyList();
    }

    private boolean isValidInput(String startDateString, String endDateString, String category) {
        return StringUtils.isNotBlank(startDateString) && StringUtils.isNotBlank(endDateString)
                && StringUtils.isNotBlank(category);
    }
}