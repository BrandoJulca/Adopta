package com.example.Adopta.Conexion.Tablas.Componentes.ComponentesUser;
import java.util.List;

public class UResponse {
    private int estado;
    private List<User> Users;

    public int getEstado() {
        return estado;
    }

    public List<User> getUsers() {
        return Users;
    }
}
