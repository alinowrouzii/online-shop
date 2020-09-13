package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Categoory;
import model.Product;
import model.Seller;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellerAccountController extends UserAccountController implements Initializable {

    public Label usernameLabel;
    public TextField fullNameTextField;
    public TextField emailTextField;
    public TextField phoneNumTextField;
    public Button submit;
    public Button chanePass;
    public Label idLabel;
    public ImageView profileImageView;
    public TableView<Product> productsView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Product product =new Product("123","ff",new BigInteger("12000"),new Categoory("book", new ArrayList<>()),new Seller("11125","ali","nn","qq@qq","123321","123321","qqw"),12);

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
