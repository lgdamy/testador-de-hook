package com.damytec.testadordehook.controller;

import com.damytec.testadordehook.service.HookService;
import com.damytec.testadordehook.util.HtmlConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
@RestController
@RequestMapping({"/seek","/logs",""})
public class HookSeekController {
    @Autowired
    private HookService service;

    @GetMapping
    public String hookSeek(HttpServletRequest req,@RequestParam(required = false) String off, @RequestParam(required = false) Integer size) {
        if (size != null) {
            service.alterarTamanhoConsulta(size);
        }
        String url = req.getRequestURL().toString();
        return HtmlConversor.getInstance().converter(service.buscarHooks(), url, off);
    }
}
