package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class LoginPageController {
    public TextField userNameTextField;
    public Label wrongInformation;
    public Button signUpBtn;
    public PasswordField passwordTextField;
    public CheckBox rememberMe;
    public Button loginButton;

    @FXML
    public void login(ActionEvent actionEvent) {
//        wrongInformation.setTextFill(Color.DARKRED);
        wrongInformation.setVisible(true);

    }

    public void signUpBtnClicked(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) signUpBtn.getScene().getWindow();
        root = FXMLLoader.load(new File("src/main/java/view/signUpPage.fxml").toURI().toURL());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign up");
        stage.show();

    }

    public void backBtnClicked(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) signUpBtn.getScene().getWindow();
        root = FXMLLoader.load(new File("src/main/java/view/MainMenuPage.fxml").toURI().toURL());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign up");
        stage.show();
    }
}
