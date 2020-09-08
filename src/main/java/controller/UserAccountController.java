package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
