package main.java.edu.ingsoft.colegio.gotitas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.edu.ingsoft.colegio.gotitas.model.Docente;
import main.java.edu.ingsoft.colegio.gotitas.service.DocenteService;

public class DocenteController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public DocenteController(DocenteService docenteService){
    
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
    
    private void confiTabla(){
    tvColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    tvColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
    tvColumnCorreoElectronico.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
    }
   
}
