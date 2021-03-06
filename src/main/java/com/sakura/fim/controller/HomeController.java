package com.sakura.fim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sakura.fim.model.dao.InspectedPlaceDAO;
import com.sakura.fim.service.FoodInspectionService;

/**
 * Home Controller
 * @author eduardohl
 *
 */
@Controller
public class HomeController {

    @Autowired
    private FoodInspectionService fimService;
    
    @Autowired
    private InspectedPlaceDAO ipDAO;
    
    @RequestMapping("/")
    public String noPath(Model model) {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("categories", fimService.getCategories());
        return "index";
    }
}
