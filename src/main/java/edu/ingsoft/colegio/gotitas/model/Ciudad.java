package main.java.edu.ingsoft.colegio.gotitas.model;

public class Ciudad {
    private String idCiudad;
    private String idPais;
    private String nombreCiudad;

    public Ciudad(String idCiudad, String idPais, String nombreCiudad) {
        this.idCiudad = idCiudad;
        this.idPais = idPais;
        this.nombreCiudad = nombreCiudad;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
    
    
    
}
