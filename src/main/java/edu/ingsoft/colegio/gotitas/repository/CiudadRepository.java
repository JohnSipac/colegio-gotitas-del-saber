package main.java.edu.ingsoft.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.model.Ciudad;

public class CiudadRepository {
    
    ObservableList<Ciudad> listaCiudades = FXCollections.observableArrayList();

    public ObservableList<Ciudad> findAll() throws Exception {
        String sql = "select * from ciudades;";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                listaCiudades.add(new Ciudad(
                        rs.getString("id_ciudad"),
                        rs.getString("id_pais"),
                        rs.getString("nombre_ciudad")
                ));
            }
            return listaCiudades;

        } catch (Exception e) {
            System.out.println("Error al encontrar ciudades: " + e.getMessage());
            return null;
        }
    }
    
}
