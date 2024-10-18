package com.example.Adopta;

public class UniversidadE {
    private Integer id;
    private String Nombre;
    private String Direccion;
    private String Distrito;

    public UniversidadE(Integer id, String nombre, String direccion, String distrito) {
        this.id = id;
        this.Nombre = nombre;
        this.Direccion = direccion;
        this.Distrito = distrito;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }
}

