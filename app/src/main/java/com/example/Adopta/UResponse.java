package com.example.Adopta;

import java.util.ArrayList;
import java.util.List;

public class UResponse {
    private Integer estado;
    private List<Universidad> universidades = new ArrayList<Universidad>();

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<Universidad> getUniversidades() {
        return universidades;
    }

    public void setUniversidades(List<Universidad> universidades) {
        this.universidades = universidades;
    }
}

