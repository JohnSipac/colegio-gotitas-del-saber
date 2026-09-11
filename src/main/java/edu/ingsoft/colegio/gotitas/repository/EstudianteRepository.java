package main.java.edu.ingsoft.colegio.gotitas.repository;

import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.model.Estudiante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;

public class EstudianteRepository {

    private ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    private Date sqlDate;

    public ObservableList<Estudiante> findAll() throws Exception {
        String sql = "select * from estudiantes;";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                listaEstudiantes.add(new Estudiante(
                        rs.getString("id_estudiante"),
                        rs.getString("id_ciudad"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("correo_electronico")
                ));
            }
            return listaEstudiantes;

        } catch (Exception e) {
            System.out.println("Error al encontrar estudiantes" + e.getMessage());
            return null;
        }
    }

    public boolean createEstudiante(Estudiante estudiante) throws Exception {
        boolean creado = false;
        String sql = "insert into estudiantes values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, estudiante.getIdEstudiante());
            pstm.setString(2, estudiante.getIdCiudad());
            pstm.setString(3, estudiante.getNombreEstudiante());
            pstm.setString(4, estudiante.getApellidoEstudiante());
            pstm.setDate(5, sqlDate.valueOf(estudiante.getFechaNacimiento()));
            pstm.setString(6, estudiante.getEmail());

            int filas = pstm.executeUpdate();

            if (filas > 0) {
                creado = true;
            }
            return creado;

        } catch (Exception e) {
            System.out.println("Error al crear estudiante:" + e.getMessage());
            return creado;
        }

    }

    public boolean updateEstudiante(Estudiante estudiante) throws Exception {
        boolean actualizado = false;
        String sql = "update estudiantes  set id_ciudad = ?, nombre = ?, apellido = ?, fecha_nacimiento = ?, correo_electronico = ? where id_estudiante = ?;";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, estudiante.getIdCiudad());
            pstm.setString(2, estudiante.getNombreEstudiante());
            pstm.setString(3, estudiante.getApellidoEstudiante());
            pstm.setDate(4, sqlDate.valueOf(estudiante.getFechaNacimiento()));
            pstm.setString(5, estudiante.getEmail());
            pstm.setString(6, estudiante.getIdEstudiante());

            int filas = pstm.executeUpdate();

            if (filas > 0) {
                actualizado = true;
            }
            return actualizado;

        } catch (Exception e) {
            System.out.println("Error al actualizar estudiante: " + e.getMessage());
            return actualizado;
        }

    }

    public boolean deleteEstudiante(Estudiante estudiante) throws Exception {
        boolean eliminado = false;
        String sql = "delete from estudiantes where id_estudiante = ?";

        try (PreparedStatement pstm = DataBaseConnection.getConnectionDataBase().prepareStatement(sql)) {
            pstm.setString(1, estudiante.getIdEstudiante());

            int filas = pstm.executeUpdate();

            if (filas > 0) {
                eliminado = true;
            }
            return eliminado;

        } catch (Exception e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
            return eliminado;
        }
    }
}
