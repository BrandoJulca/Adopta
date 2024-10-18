package com.example.consumoapirest.UserDATA;

import java.util.ArrayList;
import java.util.List;

public class USEResponse {
    private Integer estado;
    private List<User> users = new ArrayList<User>();

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
