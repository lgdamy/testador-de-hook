package com.damytec.testadordehook.controller;

import com.damytec.testadordehook.domain.WebAttributesDTO;
import com.damytec.testadordehook.service.HookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.damytec.testadordehook.util.Constants.AVAILABLE_SIZES;


/**
 * @author lgdamy@raiadrogasil.com on 21/11/2020
 */
@Controller
public class WebController {

    @Autowired
    private HookService service;

    private static final String INDEX = "home";

    @RequestMapping("/")
    public String index(@ModelAttribute WebAttributesDTO attributes,  Model model, HttpServletRequest req) {
        model.addAttribute("attributes", attributes);
        model.addAttribute("hooks", service.buscarHooks(attributes.getSize()));
        model.addAttribute("sizes", AVAILABLE_SIZES);
        model.addAttribute("baseUrl", req.getRequestURL().toString());

        return INDEX;
    }
}
