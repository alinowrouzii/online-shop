package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ShoppingSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {
    public ShoppingSystem shoppingSystem;
    public Label usernameLabel;
    public TextField fullNameTextField;
    public TextField emailTextField;
    public TextField phoneNumTextField;
    public Button submit;
    public Button chanePass;
    public Label idLabel;
    public ImageView profileImageView;
    public Label balanceLabel;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem  =SystemInitializer.getShoppingSystem();
    }
}
