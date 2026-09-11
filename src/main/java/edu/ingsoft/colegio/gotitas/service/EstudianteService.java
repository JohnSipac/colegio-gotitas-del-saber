package main.java.edu.ingsoft.colegio.gotitas.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.model.Ciudad;
import main.java.edu.ingsoft.colegio.gotitas.model.Estudiante;
import main.java.edu.ingsoft.colegio.gotitas.model.Pais;
import main.java.edu.ingsoft.colegio.gotitas.repository.CiudadRepository;
import main.java.edu.ingsoft.colegio.gotitas.repository.EstudianteRepository;
import main.java.edu.ingsoft.colegio.gotitas.repository.PaisRepository;

public class EstudianteService {

    private EstudianteRepository estudianteRepository;
    private PaisRepository paisRepository;
    private CiudadRepository ciudadRepository;
    private ObservableList<Ciudad> listaCiudades = FXCollections.observableArrayList();
    private ObservableList<Pais> listaPaises = FXCollections.observableArrayList();
    private ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();

    public ObservableList<Estudiante> findAllEstudiantes() throws Exception {
        listaEstudiantes = estudianteRepository.findAll();
        return listaEstudiantes;
    }

    public boolean createEstudiante(Estudiante estudiante) throws Exception {
        boolean exitoso = false;
        boolean creado = estudianteRepository.createEstudiante(estudiante);

        if (creado) {
            exitoso = true;
        }
        return exitoso;
    }

    public boolean updateEstudiante(Estudiante estudiante) throws Exception {
        boolean exitoso = false;
        boolean actualizado = estudianteRepository.updateEstudiante(estudiante);

        if (actualizado) {
            exitoso = true;
        }
        return exitoso;
    }

    public boolean deleteEstudiante(Estudiante estudiante) throws Exception {
        boolean exitoso = false;
        boolean eliminado = estudianteRepository.deleteEstudiante(estudiante);

        if (eliminado) {
            exitoso = true;
        }
        return exitoso;
    }

    public ObservableList<Pais> findAllPaises() throws Exception {
        listaPaises = paisRepository.findAll();
        return listaPaises;
    }
    
    public ObservableList<Ciudad> findAllCiudades() throws Exception {
       listaCiudades = ciudadRepository.findAll();
       return listaCiudades;
    }
    
    public String findIdByName(String ciudadName) throws Exception {
         String idCiudad = ciudadRepository.findIdByName(ciudadName);
       return idCiudad;

    }
    
    public String findNameById(String ciudadId) throws Exception {
        String ciudadName = ciudadRepository.findIdByName(ciudadId);
       return ciudadName;
    }
}
