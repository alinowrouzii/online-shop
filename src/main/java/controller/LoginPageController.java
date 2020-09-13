package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.ShoppingSystem;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginPageController implements Initializable {
    public TextField userNameTextField;
    public Label wrongInformation;
    public Button signUpBtn;
    public ShoppingSystem shoppingSystem;
    public PasswordField passwordTextField;
    public Button loginButton;

    @FXML
    public void login() {
//        wrongInformation.setTextFill(Color.DARKRED);
        if(userNameTextField.getText().equals("") || passwordTextField.getText().equals(""))
            wrongInformation.setVisible(true);
        else {
            if (shoppingSystem.login(userNameTextField.getText(),passwordTextField.getText())){
                SystemInitializer.showPage("products page");
            }
            else {
                wrongInformation.setVisible(true);
            }
        }

    }

    public void signUpBtnClicked() {
        SystemInitializer.showPage("sign up page");
        System.out.println("successful");
    }

    public void backBtnClicked() {
        SystemInitializer.showPage("main menu page");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem=SystemInitializer.getShoppingSystem();
    }
}
