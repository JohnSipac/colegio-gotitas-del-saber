package main.java.edu.ingsoft.colegio.gotitas.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.edu.ingsoft.colegio.gotitas.service.RegistroService;
import main.java.edu.ingsoft.colegio.gotitas.util.SceneManager;

public class RegistroController implements Initializable {

    private RegistroService registroService;
    private SceneManager sceneManager;


    @FXML private TextField txtUsername;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;

    public RegistroController(RegistroService registroService, SceneManager sceneManager) {
        this.registroService = registroService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

 
    @FXML
    public void saveRegistro() throws Exception {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            sceneManager.showInfoAlert(
                    "CAMPOS FALTANTES",
                    "Revisar Formulario",
                    "Todos los campos son obligatorios.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        if (!email.contains("@")) {
            sceneManager.showInfoAlert(
                    "CORREO INVÁLIDO",
                    "Formato Incorrecto",
                    "El correo electrónico debe incluir el carácter '@'.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        if (!password.equals(confirmPassword)) {
            sceneManager.showInfoAlert(
                    "CONTRASEÑAS NO COINCIDEN",
                    "Error de Confirmación",
                    "La contraseña y la confirmación no son iguales.",
                    Alert.AlertType.ERROR
            );
            return;
        }

        try {
            boolean registrado = registroService.registrar(username, email, password);

            if (registrado) {
                sceneManager.showInfoAlert(
                        "REGISTRO EXITOSO",
                        "¡Cuenta Creada!",
                        "El usuario se ha registrado correctamente.",
                        Alert.AlertType.INFORMATION
                );
                sceneManager.showLoginView();
            } else {
                sceneManager.showInfoAlert(
                        "ERROR DE REGISTRO",
                        "Error del Sistema",
                        "Ocurrió un fallo al intentar guardar la cuenta.",
                        Alert.AlertType.ERROR
                );
            }

        } catch (IllegalArgumentException e) {
            sceneManager.showInfoAlert(
                    "ERROR DE VALIDACIÓN",
                    "Usuario Existente",
                    e.getMessage(),
                    Alert.AlertType.ERROR
            );
        }
    }

    @FXML
    public void handlebackToLogin() throws Exception {
        sceneManager.showLoginView();
    }
}