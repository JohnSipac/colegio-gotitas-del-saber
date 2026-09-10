package main.java.edu.ingsoft.colegio.gotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID; 
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class RegistroRepository {
    
    public Usuario findUserByEmail(String email) {
        String sql = "SELECT id_usuario, username, email, contrasena_hash FROM usuarios WHERE email = ?";
        
        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, email);
            
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("id_usuario"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("contrasena_hash")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por email: " + e.getMessage());
        }
        return null;
    }
    
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM usuarios WHERE email = ?";
        
        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, email);
            try (ResultSet rs = pstm.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar email: " + e.getMessage());
        }
        return false;
    }
    
    public boolean registrarUsuario(Usuario usuario, String plainPassword) {
        String sql = "INSERT INTO usuarios (id_usuario, username, email, contrasena_hash) VALUES (?, ?, ?, ?)";
        String idNuevo = UUID.randomUUID().toString();
        

        String hash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        
        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, idNuevo);             
            pstm.setString(2, usuario.getUsername()); 
            pstm.setString(3, usuario.getEmail());             
            pstm.setString(4, hash);                           
            
            return pstm.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}