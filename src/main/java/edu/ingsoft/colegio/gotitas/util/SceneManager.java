package main.java.edu.ingsoft.colegio.gotitas.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.edu.ingsoft.colegio.gotitas.controller.LoginController;
import main.java.edu.ingsoft.colegio.gotitas.repository.AuthRepository;
import main.java.edu.ingsoft.colegio.gotitas.service.AuthService;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceneManager {

    private Stage primaryStage;
    private final String FXML_PATH = "/main/resources/view/";

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "login-view.fxml"));

        loader.setControllerFactory(
                clazz -> {
                    if (clazz == LoginController.class) {
                        AuthRepository authRepository = new AuthRepository();
                        AuthService authService = new AuthService(authRepository);
                        return new LoginController(authService, this);
                    }
                    try {
                        return clazz.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException("Error al crear el constructor " + e.getMessage());
                    }
                }
        );

        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }
    
    public void showDashboardView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "dashboard-view.fxml"));

        loader.setControllerFactory(
                clazz -> {
                    if (clazz == LoginController.class) {
                        AuthRepository authRepository = new AuthRepository();
                        AuthService authService = new AuthService(authRepository);
                        return new LoginController(authService, this);
                    }
                    try {
                        return clazz.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException("Error al crear el constructor " + e.getMessage());
                    }
                }
        );

        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }
    
     //ventana modal para mostrar alertas
    public void showInfoAlert(String title, String head, String content, AlertType type) {
    Alert alert = new Alert(type);
    alert.initOwner(this.primaryStage);
    alert.setTitle(title);
    alert.setHeaderText(head);
    alert.setContentText(content);

    try {
        Image customIcon = new Image(getClass().getResourceAsStream("/main/resources/images/zarate.png"));
        ImageView imageView = new ImageView(customIcon);
        
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setPreserveRatio(true);
        
        alert.getDialogPane().setGraphic(imageView);
        
    } catch (Exception e) {
        System.out.println("No se pudo cargar la imagen: " + e.getMessage());
    }

    alert.showAndWait();
}

}
