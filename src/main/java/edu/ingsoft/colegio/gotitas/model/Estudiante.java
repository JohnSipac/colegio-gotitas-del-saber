package main.java.edu.ingsoft.colegio.gotitas.model;

<<<<<<< .merge_file_LuJ2vJ
public class Estudiante {

    private String idEstudiante;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String nombreSeccion;
    private String nombreCurso;
    private String nombreDocente;
    private String apellidoDocente;

    public Estudiante(String idEstudiante, String nombre, String apellido, String correoElectronico, String nombreSeccion, String nombreCurso, String nombreDocente, String apellidoDocente) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.nombreSeccion = nombreSeccion;
        this.nombreCurso = nombreCurso;
        this.nombreDocente = nombreDocente;
        this.apellidoDocente = apellidoDocente;
=======
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
>>>>>>> .merge_file_gWQD8i
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

<<<<<<< .merge_file_LuJ2vJ
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
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getApellidoDocente() {
        return apellidoDocente;
    }

    public void setApellidoDocente(String apellidoDocente) {
        this.apellidoDocente = apellidoDocente;
    }
=======
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
    
    
>>>>>>> .merge_file_gWQD8i

}
