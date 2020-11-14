package com.damytec.testadordehook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
@RestController
@RequestMapping({"/seek","/logs",""})
public class HookSeekController {
    @Autowired
    private HookSaverService service;

    @GetMapping
    public String hookSeek(HttpServletRequest req,@RequestParam(required = false) String off) {
        String url = req.getRequestURL().toString();
        return HtmlConversor.getInstance().converter(service.buscarHooks(), url, off);
    }
}
