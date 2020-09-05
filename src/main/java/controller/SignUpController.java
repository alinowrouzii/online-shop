package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SignUpController {
    public TextField firstNameTextField;
    public TextField emailTextField;
    public TextField lastNameTextField;
    public TextField userNameTextField;
    public TextField passwordTextField;
    public Button registerBtn;
    public TabPane tabPane;
    @FXML
    public Button loginBtn;


    @FXML
    public void loginClicked(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) loginBtn.getScene().getWindow();
        root = FXMLLoader.load(new File("src/main/java/view/LoginPage.fxml").toURI().toURL());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login page");
        stage.show();
    }

    public void backClicked(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) loginBtn.getScene().getWindow();
        root = FXMLLoader.load(new File("src/main/java/view/MainMenuPage.fxml").toURI().toURL());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login page");
        stage.show();
    }
}
