package controller;

import exception.HasNotLoggedInException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ShoppingSystem;
import model.User;

import javax.jws.soap.SOAPBinding;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {
    public ShoppingSystem shoppingSystem;
    public TextField lastNameTextField;
    public TextField emailTextField;
    public TextField phoneNumTextField;
    public Button submit;
    public Button chanePass;
    public Label idLabel;
    public ImageView profileImageView;
    public Label balanceLabel;
    public User currentUser;
    public PasswordField passwordField;
    public PasswordField newPass;
    public PasswordField confirmPass;
    public TextField firstNameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem  =SystemInitializer.getShoppingSystem();
        try {
            currentUser=shoppingSystem.getCurrentUser();
        } catch (HasNotLoggedInException e) {
            e.printStackTrace();
        }
        firstNameField.setText(currentUser.getFirstName());
        idLabel.setText(currentUser.getId());
        lastNameTextField.setText(currentUser.getLastName());
        emailTextField.setText(currentUser.getEmail());
        phoneNumTextField.setText(currentUser.getPhoneNumber());
        balanceLabel.setText(currentUser.getBalance().toString());
        passwordField.setText(currentUser.getPassword());
    }
    public void changePassClicked(ActionEvent actionEvent){
        //TODO: change password
    }
    public void submitChanges(ActionEvent actionEvent){
        //TODO: submit changes
    }

    public void changeProfilePicture() {

        Stage stage = (Stage)profileImageView.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("All Images", "*.*")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println(file.getPath());
        }
        Image image = null;
        try {
            assert file != null;
            image = new Image(new FileInputStream(file.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        profileImageView.setImage(image);
    }


}
