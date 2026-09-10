package main.java.edu.ingsoft.colegio.gotitas.dto.response;

public class LoginResponse {
    private String username;
    private String email;
    private String contrasena_hash;

    public LoginResponse(String username, String email, String contrasena_hash) {
        this.username = username;
        this.email = email;
        this.contrasena_hash = contrasena_hash;
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

    public String getContrasena_hash() {
        return contrasena_hash;
    }

    public void setContrasena_hash(String contrasena_hash) {
        this.contrasena_hash = contrasena_hash;
    }

}
