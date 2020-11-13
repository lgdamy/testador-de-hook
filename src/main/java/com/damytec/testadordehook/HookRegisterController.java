package com.damytec.testadordehook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
@RestController
public class HookRegisterController {
    @Autowired
    private HookSaverService service;

    @RequestMapping({"/fire","/fire/**"})
    public String hookFire(@RequestBody(required = false) Object body, HttpServletRequest req) {
        service.register(req, body);
        return "Pong!";
    }
}
