package com.damytec.testadordehook.domain;

/**
 * @author lgdamy@raiadrogasil.com on 21/11/2020
 */
public class WebAttributesDTO {

    private int size;
    private boolean metodo;
    private boolean url;
    private boolean body;
    private boolean headers;
    private boolean origem;
    private boolean hora;

    public WebAttributesDTO() {
        super();
        size = 10;
        metodo = true;
        url = true;
        body = true;
        headers = true;
        origem = true;
        hora = true;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isMetodo() {
        return metodo;
    }

    public void setMetodo(boolean metodo) {
        this.metodo = metodo;
    }

    public boolean isUrl() {
        return url;
    }

    public void setUrl(boolean url) {
        this.url = url;
    }

    public boolean isBody() {
        return body;
    }

    public void setBody(boolean body) {
        this.body = body;
    }

    public boolean isHeaders() {
        return headers;
    }

    public void setHeaders(boolean headers) {
        this.headers = headers;
    }

    public boolean isOrigem() {
        return origem;
    }

    public void setOrigem(boolean origem) {
        this.origem = origem;
    }

    public boolean isHora() {
        return hora;
    }

    public void setHora(boolean hora) {
        this.hora = hora;
    }
}
