package main.java.edu.ingsoft.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.ingsoft.colegio.gotitas.dto.response.LoginResponse;

public class AuthRepository {

    public LoginResponse findUserByEmail(LoginRequest loginRequest) {
        String sql = "SELECT username, email, contrasena_hash FROM usuarios WHERE email = ?";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, loginRequest.getEmail());
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return new LoginResponse(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("contrasena_hash")
                );
            }

        } catch (Exception e) {
            System.out.println("Error al encontrar el Email: " + e.getMessage());
        }

        return null;

    }

}
