package main.java.edu.ingsoft.colegio.gotitas.model;

import java.time.LocalDate;

public class Estudiante {

    private String idEstudiante;
    private String idCiudad;
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private LocalDate fechaNacimiento;
    private String email;

    public Estudiante(String idEstudiante, String idCiudad, String nombreEstudiante, String apellidoEstudiante, LocalDate fechaNacimiento, String email) {
        this.idEstudiante = idEstudiante;
        this.idCiudad = idCiudad;
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

}
