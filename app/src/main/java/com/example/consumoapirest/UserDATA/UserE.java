package com.example.consumoapirest.UserDATA;

public class UserE {
    private int ID;
    private String Correo;
    private String Pass;
    private String Usuario;
    private int NumeroContacto;

    // Constructor
    public UserE(int ID, String correo, String pass, String usuario, int numeroContacto) {
        this.ID = ID;
        this.Correo = correo;
        this.Pass = pass;
        this.Usuario = usuario;
        this.NumeroContacto = numeroContacto;
    }

    // Getters y Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public int getNumeroContacto() {
        return NumeroContacto;
    }

    public void setNumeroContacto(int numeroContacto) {
        NumeroContacto = numeroContacto;
    }
}

