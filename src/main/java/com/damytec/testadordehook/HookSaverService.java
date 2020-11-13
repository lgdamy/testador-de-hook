package com.damytec.testadordehook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
@Service
public class HookSaverService {

    private List<HookDTO> lastHooks;

    private ObjectMapper mapper;

    @Autowired
    public HookSaverService(ObjectMapper mapper) {
        this.mapper = mapper;
        this.lastHooks = new ArrayList<>();
    }

    public void register(HttpServletRequest req, @Nullable Object body) {
        Enumeration headerNames = req.getHeaderNames();
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = req.getHeader(key);
            mvm.add(key, value);
        }
        String corpo = "SEM CORPO (BEM MAGRINHO)";
        if ( body != null) {
            try {
                corpo = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            } catch (Exception e) {
                corpo = body instanceof String ? (String) body : body.toString();
            }
        }

        String headers = "SEM CABEÇA (BEM BURRINHO)";
        if ( headers != null) {
            try {
                headers = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mvm);
            } catch (Exception ignored) {}
        }
        HookDTO hook = new HookDTO();
        hook.setHora(new Date());
        hook.setHeaders(headers);
        hook.setBody(corpo);
        hook.setMetodo(req.getMethod());
        hook.setOrigem(req.getRemoteAddr());
        hook.setDestino(req.getRequestURL().toString());
        incrementar(hook);
    }

    private void incrementar(HookDTO hook) {
        if (lastHooks.size() < 10) {
            lastHooks.add(hook);
            return;
        }
        lastHooks.remove(0);
        lastHooks.add(hook);
    }

    public List<HookDTO> buscarHooks() {
        return lastHooks.stream()
                .sorted(Comparator.comparing(HookDTO::getHora, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
