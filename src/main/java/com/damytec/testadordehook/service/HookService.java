package com.damytec.testadordehook.service;

import com.damytec.testadordehook.domain.HookDTO;
import com.damytec.testadordehook.domain.jpa.Hook;
import com.damytec.testadordehook.repository.HookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
@Service
public class HookService {

    private HookRepository repo;

    private ObjectMapper mapper;

    private int size;

    private static final int MAX_SIZE = 100;

    @Autowired
    public HookService(ObjectMapper mapper, HookRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
        this.size = 10;
    }

    @Transactional
    public void register(HttpServletRequest req, @Nullable Object body) {
        Enumeration<String> headerNames = req.getHeaderNames();
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = req.getHeader(key);
            mvm.add(key, value);
        }
        String corpo = null;
        if ( body != null) {
            try {
                corpo = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            } catch (Exception e) {
                corpo = body instanceof String ? (String) body : body.toString();
            }
        }

        String headers = null;
        try {
            headers = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mvm);
        } catch (Exception ignored) {}
        HookDTO hook = new HookDTO();
        hook.setHora(new Date());
        hook.setHeaders(this.shorten(headers));
        hook.setBody(this.shorten(corpo));
        hook.setMetodo(req.getMethod());
        hook.setOrigem(this.shorten(req.getRemoteAddr()));
        hook.setDestino(this.shorten(req.getRequestURL().toString()));
        this.incrementar(hook);
    }

    private void incrementar(HookDTO hook) {
        if (repo.count() > MAX_SIZE) {
            repo.delete(repo.findFirstByOrderByHora());
        }
        repo.save(new Hook(hook));
    }

    public List<HookDTO> buscarHooks() {
        Page<Hook> hooks = repo.findAll(PageRequest.of(0, this.size, Sort.by(Sort.Direction.DESC, "hora")));
        return hooks.getContent().stream().map(HookDTO::new).collect(Collectors.toList());
    }

    public void alterarTamanhoConsulta(int size) {
        this.size = size <= 1 ? 1 : size >= MAX_SIZE ? MAX_SIZE : size;
    }

    private String shorten(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return str.length() > 200 ? str.substring(0,200).concat("(...)") : str;
    }
}
