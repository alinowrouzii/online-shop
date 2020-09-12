package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ShoppingSystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public ShoppingSystem shoppingSystem;
    public TextField firstNameTextField;
    public TextField emailTextField;
    public TextField lastNameTextField;
    public TextField userNameTextField;
    public TextField passwordTextField;
    public Button registerBtn;
    public TabPane tabPane;
    public Button loginBtn;
    public Label wrongInputFormat;


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
        stage.setTitle("Main Menu page");
        stage.show();
    }
    public void registerClicked(ActionEvent actionEvent) throws IOException{
        if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") ||
        passwordTextField.getText().equals("") || userNameTextField.getText().equals("") || emailTextField.getText().equals("")){
            wrongInputFormat.setVisible(true);
            return;
        }
        shoppingSystem.signUp("user","",userNameTextField.getText(),firstNameTextField.getText(),
                lastNameTextField.getText(),emailTextField.getText(),"",passwordTextField.getText());
        Stage stage= (Stage) registerBtn.getScene().getWindow();
        Parent root=FXMLLoader.load(new File("src/main/java/view/MainMenuPage.fxml").toURI().toURL());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main Menu page");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem=SystemInitializer.getShoppingSystem();
    }

}
