package main.java.edu.ingsoft.colegio.gotitas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.edu.ingsoft.colegio.gotitas.model.Docente;
import main.java.edu.ingsoft.colegio.gotitas.service.DocenteService;
import main.java.edu.ingsoft.colegio.gotitas.util.SceneManager;

public class DocenteController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private SceneManager stage;
    private DocenteService docenteService;

    public DocenteController(DocenteService docenteService, SceneManager stage) {
        this.docenteService = docenteService;
        this.stage = stage;
    }

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCorreoElectronico;

    @FXML
    private TableView<Docente> tvDocentes;
    @FXML
    private TableColumn<Docente, String> tvColumnNombre;
    @FXML
    private TableColumn<Docente, String> tvColumnApellido;
    @FXML
    private TableColumn<Docente, String> tvColumnCorreoElectronico;

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnLimpiar;

    private void confiTabla() {
        tvColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tvColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tvColumnCorreoElectronico.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        tvDocentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtNombre.setText(newSelection.getNombre());
                txtApellido.setText(newSelection.getApellido());
                txtCorreoElectronico.setText(newSelection.getCorreoElectronico());
            }
        });
    }

    @FXML
    public void createDocente() {
        Docente docenteSeleccionado = tvDocentes.getSelectionModel().getSelectedItem();

        if (docenteSeleccionado != null) {
            stage.showInfoAlert("Error Info", "Advertencia", "El docente ya existe. Por favor, presione 'Limpiar' para registrar uno nuevo.", Alert.AlertType.WARNING);
            return;
        }
        Docente nuevoDocente = new Docente(
                null,
                txtNombre.getText(),
                txtApellido.getText(),
                txtCorreoElectronico.getText()
        );

        if (docenteService.createDocente(nuevoDocente)) {
            stage.showInfoAlert("Que exitoso", "Éxito", "Docente registrado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();

        } else {
            stage.showInfoAlert("Error Info", "Error", "No se pudo registrar el docente.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void updateDocente() {
        Docente docenteSeleccionado = tvDocentes.getSelectionModel().getSelectedItem();

        if (docenteSeleccionado == null) {
            stage.showInfoAlert("Error Info", "Advertencia", "Debe seleccionar un docente de la tabla para actualizar.", Alert.AlertType.WARNING);
            return;
        }

        Docente docenteActualizado = new Docente(
                docenteSeleccionado.getIdDocente(),
                txtNombre.getText(),
                txtApellido.getText(),
                txtCorreoElectronico.getText()
        );

        if (docenteService.updateDocente(docenteActualizado)) {
            stage.showInfoAlert("Que exitoso", "Éxito", "Docente actualizado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            stage.showInfoAlert("Error Info", "Error", "No se pudo actualizar el docente.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void eliminarDocente() {
        Docente docenteSeleccionado = tvDocentes.getSelectionModel().getSelectedItem();

        if (docenteSeleccionado == null) {
            stage.showInfoAlert("Error Info","Advertencia", "Debe seleccionar un docente de la tabla para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        if (docenteService.deleteDocente(docenteSeleccionado)) {
            stage.showInfoAlert("Que exitoso","Éxito", "Docente eliminado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            // TODO: Llamar a tu método para recargar la lista de la tabla
        } else {
            stage.showInfoAlert("Error Info","Error", "No se pudo eliminar el docente.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCorreoElectronico.clear();
        tvDocentes.getSelectionModel().clearSelection();
    }
}
