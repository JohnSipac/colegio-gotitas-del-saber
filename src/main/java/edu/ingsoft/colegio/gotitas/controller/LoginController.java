package main.java.edu.ingsoft.colegio.gotitas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.java.edu.ingsoft.colegio.gotitas.config.DataBaseConnection;
import main.java.edu.ingsoft.colegio.gotitas.service.AuthService;
import main.java.edu.ingsoft.colegio.gotitas.util.SceneManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.java.edu.ingsoft.colegio.gotitas.dto.request.LoginRequest;
import main.java.edu.ingsoft.colegio.gotitas.dto.response.LoginResponse;

public class LoginController implements Initializable {

    private final AuthService authService;
    private final SceneManager sceneManager;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldPass;

    public LoginController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void handleLogin() throws Exception {
        if (txtFieldEmail.getText().isEmpty() || txtFieldPass.getText().isEmpty()) {
            sceneManager.showInfoAlert("Te faltan campos", "Revisar información", "Uno o más campos están vacíos... ¯\\_(ツ)_/¯", AlertType.WARNING);
        } else {
            try {
                LoginResponse responseService = authService.login(new LoginRequest(txtFieldEmail.getText(), txtFieldPass.getText()));
                
                
                LoginResponse usuarioLog = new LoginResponse(responseService.getUsername(), responseService.getEmail(), responseService.getContrasena_hash());
                
               sceneManager.showMainMenu(usuarioLog);

            } catch (RuntimeException e) {
                sceneManager.showInfoAlert("Datos incorrectos", "Revisa tu información", "Intenta de nuevo", Alert.AlertType.INFORMATION);
            }
        }
    }
    
    public void handleRegistro() throws Exception{
        sceneManager.showRegistroView();
    }
}


