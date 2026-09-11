package main.java.edu.ingsoft.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import java.sql.SQLException;
import main.java.edu.ingsoft.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.ingsoft.colegio.gotitas.dto.response.LoginResponse;

public class AuthRepository {

    private boolean sqlStatus = false;

    public AuthRepository() {
    }
    

    // Divide y vencerás: un metodo debe ser engargado de realizar unicamente una tarea específica
    // el nombre de ese método debe ser modular, directo
    public LoginResponse findUserByEmail(LoginRequest loginRequest){
        String sql = "select d.nombre, d.apellido, u.contrasena_hash from usuarios as u right join docentes as d on d.id_docente = u.id_docente where email = ?;";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, loginRequest.getEmail());
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return new LoginResponse(rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena_hash"));
            }

        } catch (Exception e) {
            System.out.println("Error al encontrar el Email" + e.getMessage());
        }

        return null;
        
    }

}
