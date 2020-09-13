package controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.ShoppingSystem;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public Button loginButton;
    public Button signUpButton;
    public Button productsButton;
    public Button aboutButton;
    public ShoppingSystem shoppingSystem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem=SystemInitializer.getShoppingSystem();
    }

    public void loginButtonClicked() {
        SystemInitializer.showPage("login page");

    }

    public void signUpButtonClicked() {
        SystemInitializer.showPage("sign up page");
    }

    public void productsButtonClicked() {
        SystemInitializer.showPage("products page");

    }

    public void aboutButtonClicked() {
        SystemInitializer.showPage("about us page");
    }
}
