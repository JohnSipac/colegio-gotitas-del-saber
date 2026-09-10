package main.java.edu.ingsoft.colegio.gotitas.repository;

import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.edu.ingsoft.colegio.gotitas.model.Docente;
import java.sql.ResultSet;

public class DocenteRepository {

    public ObservableList<Docente> listaDocentes = FXCollections.observableArrayList();

    public ObservableList<Docente> findAll() throws Exception {
        String sql = "select * from docentes;";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                listaDocentes.add(new Docente(
                        rs.getString("id_docente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo_electronico")
                ));
            }
            return listaDocentes;

        } catch (Exception e) {
            System.out.println("Error al encontrar estudiantes" + e.getMessage());
            return null;
        }
    }

    public boolean createDocente(Docente docente) {
        String sql = "INSERT INTO docentes (idDocente, nombre, apellido, correoElectronico) VALUES (UUID(), ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnectionDataBase(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getApellido());
            pstmt.setString(3, docente.getCorreoElectronico());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar docente: " + e.getMessage());
            return false;
        }
    }

    public boolean updateDocente(Docente docente) {
        String sql = "update docentes set nombre = ?, apellido = ?, correo_electronico = ? where idDocente = ?";

        try (Connection conn = DataBaseConnection.getConnectionDataBase(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getApellido());
            pstmt.setString(3, docente.getCorreoElectronico());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            return filasAfectadas > 0;
        }catch (SQLException e) {
            System.out.println("Error al actualizar docente: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteDocente(Docente docente) {
        String sql = "DELETE FROM docentes WHERE idDocente = ?";
        
        try (Connection conn = DataBaseConnection.getConnectionDataBase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, docente.getIdDocente());
            
            int filasAfectadas = pstmt.executeUpdate();
            
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar docente: " + e.getMessage());
            return false;
        }
    }
}
