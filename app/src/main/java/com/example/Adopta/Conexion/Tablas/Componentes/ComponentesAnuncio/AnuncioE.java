package com.example.Adopta.Conexion.Tablas.Componentes.ComponentesAnuncio;

public class AnuncioE {
    private Integer id;
    private String nombre;
    private String especie;
    private Integer edad;
    private String unidadEdad;
    private String tamaño;
    private Double peso;
    private String unidadPeso;
    private String caracter;
    private String ubicacion;
    private String usuario;
    private String foto;

    public AnuncioE(Integer id, String nombre, String especie, Integer edad, String unidadEdad, String tamaño,
                    Double peso, String unidadPeso, String caracter, String ubicacion, String usuario, String foto) {

        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.unidadEdad = unidadEdad;
        this.tamaño = tamaño;
        this.peso = peso;
        this.unidadPeso = unidadPeso;
        this.caracter = caracter;
        this.ubicacion = ubicacion;
        this.usuario = usuario;
        this.foto = foto;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getUnidadEdad() {
        return unidadEdad;
    }

    public void setUnidadEdad(String unidadEdad) {
        this.unidadEdad = unidadEdad;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getUnidadPeso() {
        return unidadPeso;
    }

    public void setUnidadPeso(String unidadPeso) {
        this.unidadPeso = unidadPeso;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
