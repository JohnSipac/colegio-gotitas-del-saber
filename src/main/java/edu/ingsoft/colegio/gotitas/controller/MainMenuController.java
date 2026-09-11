package main.java.edu.ingsoft.colegio.gotitas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.edu.ingsoft.colegio.gotitas.dto.response.LoginResponse;
import main.java.edu.ingsoft.colegio.gotitas.util.SceneManager;

public class MainMenuController implements Initializable {

    @FXML
    private Label lblUsername;

    private final LoginResponse usuario;
    private final SceneManager sceneManager; 

    public MainMenuController(LoginResponse usuario, SceneManager sceneManager) {
        this.usuario = usuario;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (usuario != null && lblUsername != null) {
            
            lblUsername.setText(usuario.getUsername().toUpperCase());
        }
    }

    @FXML
    public void backToLogin() throws Exception {
        sceneManager.showLoginView();
    }

}
