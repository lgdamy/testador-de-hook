package com.damytec.testadordehook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
@RestController
@RequestMapping("/seek")
public class HookSeekController {
    @Autowired
    private HookSaverService service;

    @GetMapping
    public String hookSeek() {
        return HtmlConversor.converter(service.buscarHooks());
    }
}
