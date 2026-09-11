package main.java.edu.ingsoft.colegio.gotitas.model;

public class Usuario {
    
    private String idUsuario;
    private String username;
    private String email;
    private String contrasenaHash;

    public Usuario(String idUsuario, String username, String email, String contrasenaHash) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.email = email;
        this.contrasenaHash = contrasenaHash;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

  
    
}
