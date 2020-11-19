package com.damytec.testadordehook.domain;

import com.damytec.testadordehook.domain.jpa.Hook;

import java.util.Date;

/**
 * @author lgdamy@raiadrogasil.com on 13/11/2020
 */
public class HookDTO {
    private String metodo;
    private String body;
    private String headers;
    private String origem;
    private String destino;
    private Date hora;

    public HookDTO() {
        super();
    }

    public HookDTO(Hook h) {
        super();
        this.metodo = h.getMetodo();
        this.body = h.getBody();
        this.destino = h.getDestino();
        this.origem = h.getOrigem();
        this.headers = h.getHeaders();
        this.hora = h.getHora();
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }


}
