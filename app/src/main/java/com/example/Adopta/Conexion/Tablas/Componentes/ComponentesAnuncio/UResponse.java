package com.example.Adopta.Conexion.Tablas.Componentes.ComponentesAnuncio;
import java.util.List;

public class UResponse {
    private int estado;
    private List<Anuncio> anuncios;

    public int getEstado() {
        return estado;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }
}
