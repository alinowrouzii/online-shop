package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Categoory;
import model.Product;
import model.Seller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellerAccountController extends UserAccountController implements Initializable {
    public Button profileButton;
    public Button logsButton;
    public Button wallet;
    public Label usernameLabel;
    public TextField fullNameTextField;
    public TextField emailTextField;
    public TextField phoneNumTextField;
    public Button submit;
    public Button chanePass;
    public Label idLabel;
    public ImageView profileImageView;
    public TableView<Product> productsView;


    //    @FXML
//    private TableColumn<Product, String> Name;
//    @FXML
//    private TableColumn<Product, String> columnEmpLastName;
//    @FXML
//    private TableColumn<Product, String> columnEmpJobDesc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Product product =new Product("123","ff",new BigInteger("12000"),new Categoory("book",new ArrayList<String>()),new Seller("11125","ali","nn","qq@qq","123321","123321","qqw"),12);

        //TODO: get All seller product from shoppingSystem and convert to observableArrayList
        ObservableList<Product> data =
                FXCollections.observableArrayList(product);




        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(200);
        idCol.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));

        TableColumn amountCol = new TableColumn("amount Name");
        amountCol.setMinWidth(100);
        amountCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amountOfProducts"));

        TableColumn productNameCol = new TableColumn("product Name");
        productNameCol.setMinWidth(100);
        productNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));


        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(100);
        productNameCol.setCellValueFactory(new PropertyValueFactory<Product, BigInteger>("price"));

        TableColumn brandCol = new TableColumn("Price");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(new PropertyValueFactory<Product, BigInteger>("price"));

        productsView.setItems(data);
        productsView.getColumns().addAll(idCol,productNameCol,amountCol, priceCol,brandCol);

    }
}
