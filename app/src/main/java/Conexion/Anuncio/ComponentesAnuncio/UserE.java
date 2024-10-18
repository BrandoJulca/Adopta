package Conexion.Anuncio.ComponentesAnuncio;

public class UserE {
    private Integer id;
    private String correo;
    private String pass;
    private String usuario;
    private String numeroContacto;

    public UserE(Integer id, String correo, String pass, String usuario, String numeroContacto) {
        this.id = id;
        this.correo = correo;
        this.pass = pass;
        this.usuario = usuario;
        this.numeroContacto = numeroContacto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }
}


