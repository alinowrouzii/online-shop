package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.ShoppingSystem;

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
    public TextField category;
    public TextField brand;
    public CheckBox sellerCheckBox;
    public Button sellerRegisterBtn;
    public PasswordField sellerPassword;
    public TextField sellerUsername;
    public TextField sellerEmail;
    public TextField sellerLastName;
    public TextField sellerFirstName;
    public CheckBox checkbox;

    @FXML
    public void loginClicked() {
        SystemInitializer.showPage("login page");
    }

    public void backClicked() {
        SystemInitializer.showPage("main menu page");

    }
    public void registerClicked() {
        if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") ||
        passwordTextField.getText().equals("") || userNameTextField.getText().equals("") || emailTextField.getText().equals("")
        || !checkbox.isSelected()){
            wrongInputFormat.setVisible(true);
            return;
        }
        shoppingSystem.signUp("user","",userNameTextField.getText(),firstNameTextField.getText(),
                lastNameTextField.getText(),emailTextField.getText(),"",passwordTextField.getText());
        SystemInitializer.showPage("main menu page");
    }
    public void sellerRegisterClicked() {
        if(sellerEmail.getText().equals("") || sellerFirstName.getText().equals("") || sellerLastName.getText().equals("")
         || sellerPassword.getText().equals("") || sellerUsername.getText().equals("") || !sellerCheckBox.isSelected()
         || brand.getText().equals("") || category.getText().equals("")){
            wrongInputFormat.setVisible(true);
            return;
        }
        shoppingSystem.signUp("seller",brand.getText(),sellerUsername.getText(),sellerFirstName.getText(),sellerLastName.getText()
        ,sellerEmail.getText(),"",sellerPassword.getText());
        SystemInitializer.showPage("main menu page");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem=SystemInitializer.getShoppingSystem();
        wrongInputFormat.setVisible(false);
    }

}
