package main.java.edu.ingsoft.colegio.gotitas.model;

public class Docente {

    private String idDocente;
    private String nombre;
    private String apellido;
    private String CorreoElectronico;

    public Docente(String idDocente, String nombre, String apellido, String CorreoElectronico) {
        this.idDocente = idDocente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.CorreoElectronico = CorreoElectronico;
    }
    
    public Docente(){}

    public String getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }
}
