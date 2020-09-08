package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Categoory;
import model.Product;
import model.Seller;
import model.ShoppingSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {
    public ShoppingSystem shoppingSystem;
    public Button profileButton;
    public Button logsButton;
    public Button wallet;
    public ImageView profilePicture;
    public Label usernameLabel;
    public Label typeLabel;
    public TextField fullNameTextField;
    public TextField emailTextField;
    public TextField phoneNumTextField;
    public Button submit;
    public Button chanePass;
    public Label idLabel;
    public ImageView profileImageView;


    public void changeProfilePicture(MouseEvent mouseEvent) {

        Stage stage = (Stage)profileImageView.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("All Images", "*.*")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println(file.getPath().toString());
        }
        Image image = null;
        try {
            image = new Image(new FileInputStream(file.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        profileImageView.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem=SystemInitializer.getShoppingSystem();
    }
}
