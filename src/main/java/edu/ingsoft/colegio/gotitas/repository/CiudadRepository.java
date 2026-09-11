package main.java.edu.ingsoft.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.model.Ciudad;

public class CiudadRepository {

    private ObservableList<Ciudad> listaCiudades = FXCollections.observableArrayList();

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

    public String findIdByName(String ciudadName) throws Exception {
        String sql = "select id_ciudad from ciudades where nombre_ciudad = ?;";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, ciudadName);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getString("id_ciudad");
            }

        } catch (Exception e) {
            System.out.println("Error al encontrar ID: " + e.getMessage());
        }
        return null;

    }
    
    public String findNameByID(String ciudadId) throws Exception {
        String sql = "select nombre_ciudad from ciudades where id_ciudad = ?;";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, ciudadId);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getString("nombre_ciudad");
            }

        } catch (Exception e) {
            System.out.println("Error al encontrar nombre: " + e.getMessage());
        }
        return null;

    }

}
