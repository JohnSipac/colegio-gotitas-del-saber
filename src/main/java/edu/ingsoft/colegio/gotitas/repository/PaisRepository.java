package main.java.edu.ingsoft.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.model.Pais;

public class PaisRepository {
        
    ObservableList<Pais> listaPaises = FXCollections.observableArrayList();

    public ObservableList<Pais> findAll() throws Exception {
        String sql = "select * from paises;";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                listaPaises.add(new Pais(
                        rs.getString("id_pais"),
                        rs.getString("nombre_pais")
                ));
            }
            return listaPaises;

        } catch (Exception e) {
            System.out.println("Error al encontrar paises: " + e.getMessage());
            return null;
        }
    }
}
