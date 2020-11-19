package com.damytec.testadordehook.domain.jpa;

import com.damytec.testadordehook.domain.HookDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lgdamy@raiadrogasil.com on 19/11/2020
 */
@Entity
public class Hook implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String metodo;

    @Lob
    private String body;

    @Lob
    private String headers;

    private String origem;

    private String destino;

    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;

    public Hook() {
    }

    public Hook(HookDTO h) {
        super();
        this.metodo = h.getMetodo();
        this.body = h.getBody();
        this.destino = h.getDestino();
        this.origem = h.getOrigem();
        this.headers = h.getHeaders();
        this.hora = h.getHora();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
}
