package main.java.edu.ingsoft.colegio.gotitas.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.edu.ingsoft.colegio.gotitas.model.Ciudad;
import main.java.edu.ingsoft.colegio.gotitas.model.Estudiante;
import main.java.edu.ingsoft.colegio.gotitas.service.EstudianteService;
import main.java.edu.ingsoft.colegio.gotitas.util.SceneManager;

public class EstudianteController implements Initializable {

    private EstudianteService estudianteService;
    private SceneManager sceneManager;

    public EstudianteController(EstudianteService estudianteService, SceneManager sceneManager) {
        this.estudianteService = estudianteService;
        this.sceneManager = sceneManager;
    }
    

    @FXML
    private ComboBox<String> cmbCiudad;
    @FXML
    private TextField txtFieldNombre;
    @FXML
    private TextField txtFieldApellido;
    @FXML
    private DatePicker dtpFechaNacimiento;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TableView<Estudiante> tvEstudiantes;
    @FXML
    private TableColumn<Estudiante, String> tableColumnId;
    @FXML
    private TableColumn<Estudiante, String> tableColumnCiudad;
    @FXML
    private TableColumn<Estudiante, String> tableColumnNombre;
    @FXML
    private TableColumn<Estudiante, String> tableColumnApellido;
    @FXML
    private TableColumn<Estudiante, LocalDate> tableColumnFechaNacimiento;
    @FXML
    private TableColumn<Estudiante, String> tableColumnEmail;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarCiudades();
            configurarTabla();
            cargarEstudiantes();

            tvEstudiantes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        txtFieldNombre.setText(newValue.getNombreEstudiante());
                        txtFieldApellido.setText(newValue.getApellidoEstudiante());
                        txtFieldEmail.setText(newValue.getEmail());
                        dtpFechaNacimiento.setValue(newValue.getFechaNacimiento());

                        String nombreCiudad = estudianteService.findNameById(newValue.getIdCiudad());
                        if (nombreCiudad != null) {
                            cmbCiudad.setValue(nombreCiudad);
                        }
                    } catch (Exception e) {
                        System.out.println("Error al cargar datos del estudiante seleccionado: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Error al inicializar controlador: " + e.getMessage());
        }
    }

    private void cargarCiudades() {
        try {
            ObservableList<Ciudad> ciudades = estudianteService.findAllCiudades();
            if (ciudades != null) {
                for (Ciudad c : ciudades) {
                    cmbCiudad.getItems().add(c.getNombreCiudad());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar ciudades: " + e.getMessage());
        }
    }

    private void configurarTabla() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        tableColumnCiudad.setCellValueFactory(new PropertyValueFactory<>("idCiudad"));
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEstudiante"));
        tableColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoEstudiante"));
        tableColumnFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void cargarEstudiantes() {
        try {
            ObservableList<Estudiante> lista = estudianteService.findAllEstudiantes();
            tvEstudiantes.setItems(lista);
        } catch (Exception e) {
            System.out.println("Error al cargar estudiantes en la tabla: " + e.getMessage());
        }
    }

    public void createEstudiante() {
        try {
            String nombreCiu = cmbCiudad.getValue();
            String nombre = txtFieldNombre.getText();
            String apellido = txtFieldApellido.getText();
            LocalDate fechaNacimiento = dtpFechaNacimiento.getValue();
            String email = txtFieldEmail.getText();

            if (nombreCiu == null || nombre.isBlank() || apellido.isBlank() || fechaNacimiento == null || email.isBlank()) {
                sceneManager.showInfoAlert("Advertencia", "Campos vacíos", "Por favor completa todos los campos", Alert.AlertType.WARNING);
                return;
            }

            String idCiudad = estudianteService.findIdByName(nombreCiu);
            String idUnico = UUID.randomUUID().toString();

            Estudiante estudiante = new Estudiante(idUnico, idCiudad, nombre, apellido, fechaNacimiento, email);
            boolean creado = estudianteService.createEstudiante(estudiante);

            if (creado) {
                sceneManager.showInfoAlert("Éxito", "Estudiante creado", "El estudiante se ha creado correctamente", Alert.AlertType.INFORMATION);
                cargarEstudiantes();
                limpiarCampos();
            } else {
                sceneManager.showInfoAlert("Error", "No creado", "No se ha podido crear al estudiante", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            sceneManager.showInfoAlert("Error", "Excepción", "Error: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void updateEstudiante() {
        try {
            Estudiante estudianteSeleccionado = tvEstudiantes.getSelectionModel().getSelectedItem();

            if (estudianteSeleccionado == null) {
                sceneManager.showInfoAlert("Advertencia", "Selección requerida", "Debes seleccionar un estudiante de la tabla", Alert.AlertType.WARNING);
                return;
            }

            String nombreCiu = cmbCiudad.getValue();
            String nombre = txtFieldNombre.getText();
            String apellido = txtFieldApellido.getText();
            LocalDate fechaNacimiento = dtpFechaNacimiento.getValue();
            String email = txtFieldEmail.getText();

            if (nombreCiu == null || nombre.isBlank() || apellido.isBlank() || fechaNacimiento == null || email.isBlank()) {
                sceneManager.showInfoAlert("Advertencia", "Campos vacíos", "Por favor completa todos los campos", Alert.AlertType.WARNING);
                return;
            }

            String idCiudad = estudianteService.findIdByName(nombreCiu);

            Estudiante estudianteActualizado = new Estudiante(
                    estudianteSeleccionado.getIdEstudiante(), 
                    idCiudad, 
                    nombre, 
                    apellido, 
                    fechaNacimiento, 
                    email
            );

            boolean actualizado = estudianteService.updateEstudiante(estudianteActualizado);

            if (actualizado) {
                sceneManager.showInfoAlert("Éxito", "Estudiante actualizado", "Los datos se han actualizado correctamente", Alert.AlertType.INFORMATION);
                cargarEstudiantes();
                limpiarCampos();
            } else {
                sceneManager.showInfoAlert("Error", "No actualizado", "No se ha podido actualizar al estudiante", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            sceneManager.showInfoAlert("Error", "Excepción", "Error: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void deleteEstudiante() {
        try {
            Estudiante estudianteSeleccionado = tvEstudiantes.getSelectionModel().getSelectedItem();

            if (estudianteSeleccionado == null) {
                sceneManager.showInfoAlert("Advertencia", "Selección requerida", "Debes seleccionar un estudiante de la tabla para eliminar", Alert.AlertType.WARNING);
                return;
            }

            boolean eliminado = estudianteService.deleteEstudiante(estudianteSeleccionado);

            if (eliminado) {
                sceneManager.showInfoAlert("Éxito", "Estudiante eliminado", "El registro se ha eliminado correctamente", Alert.AlertType.INFORMATION);
                cargarEstudiantes();
                limpiarCampos();
            } else {
                sceneManager.showInfoAlert("Error", "No eliminado", "No se ha podido eliminar al estudiante", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            sceneManager.showInfoAlert("Error", "Excepción", "Error: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        cmbCiudad.setValue(null);
        txtFieldNombre.clear();
        txtFieldApellido.clear();
        txtFieldEmail.clear();
        dtpFechaNacimiento.setValue(null);
        tvEstudiantes.getSelectionModel().clearSelection();
    }
}