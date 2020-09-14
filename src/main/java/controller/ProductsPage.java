package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javassist.tools.rmi.ObjectNotFoundException;
import model.Product;
import model.ShoppingSystem;
import view.ProductBox;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsPage implements Initializable {
    public GridPane productsPane;
    public ShoppingSystem shoppingSystem;
    public Button loginButton;
    public JFXButton cartButton;
    public TextField byMinPrice;
    public TextField byMaxPrice;
    public TextField byProductName;
    public TextField byCategoryName;
    public CheckBox byProductExistence;
    private int startIndex;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shoppingSystem = SystemInitializer.getShoppingSystem();
        System.out.println("11"+shoppingSystem);

        showOnProductPage("first");

    }
    boolean showOnProductPage(String mode)  {
        if(mode.equals("first")){
            startIndex = 0;
        }else if(mode.equals("next") && shoppingSystem.getFilteredProducts().size() > startIndex+9){
            startIndex +=9;
        }else if(mode.equals("back") &&  startIndex-9>=0){
            startIndex -=9;
        }else{
            return false;
        }
        System.out.println("next startIndex"+startIndex);
        productsPane.getChildren().clear();
        productsPane.setGridLinesVisible(true);

        for(int i=startIndex; i<startIndex+9 && i<shoppingSystem.getFilteredProducts().size(); i++){
            Product product= shoppingSystem.getFilteredProducts().get(i);
            String productId = product.getProductId();
            ProductBox box = new ProductBox("product: "+product.getProductName(), 5);
            box.setImage(new Image(new File("src/main/resources/products/"+productId+".png").toURI().toString()));
            box.setPrice("Price: "+product.getPrice().toString());
            int columnIndex =i%3;
            int rowIndex =(i/3)%3;
            productsPane.add(box,columnIndex, rowIndex);
            int finalI = i;
            box.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    System.out.println("Open product page| product: "+ (finalI +1));
                    try {
                        showProduct(product);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                private void showProduct(Product product) throws IOException {
                    Stage stage;
                    FXMLLoader loader = new FXMLLoader(new File("src/main/java/view/ProductPage.fxml").toURI().toURL());
                    Pane root = loader.load();

                    ProductPageController controller = loader.getController();
                    controller.setInfoOfProduct(product.getProductId(),product.getProductName(),product.getProductBrand(),product.getCategory().getName(),product.getPrice().toString());

                    stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle(product.getProductName());
                    stage.show();
                }
            });
        }
        return true;
    }
    public void loginButtonClicked() {
        Stage stage;
        Parent root = null;
        stage = (Stage) loginButton.getScene().getWindow();
        try {
            root = FXMLLoader.load(new File("src/main/java/view/signUpPage.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(root != null){
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sign up");
            stage.show();
        }
    }

    public void cartButtonClicked() {
        Stage stage;
        Parent root = null;
        stage = (Stage) loginButton.getScene().getWindow();
        try {
            root = FXMLLoader.load(new File("src/main/java/view/CartPage.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(root != null){
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cart");
            stage.show();
        }
    }

    public void nextButtonClicked() {
        showOnProductPage("next");
    }

    public void backButtonClicked()  {
        showOnProductPage("back");
    }

    public void filterProducts() {
        if(!byMinPrice.getText().isEmpty() && !byMaxPrice.getText().isEmpty()){
            shoppingSystem.filterProductsByPrice(new BigInteger(byMinPrice.getText()),new BigInteger(byMaxPrice.getText()));
        }
        if(!byProductName.getText().isEmpty()){
            shoppingSystem.filterProductsByName(byProductName.getText());
        }
        if(!byCategoryName.getText().isEmpty()){
            try {
                shoppingSystem.filterProductsByCategoryName(byCategoryName.getText());
            } catch (ObjectNotFoundException ignored) {
                //TODO
            }
        }
        if(!byProductExistence.isSelected()){
            shoppingSystem.filterProductsByProductExistence();
        }
        //to refresh products page view
        SystemInitializer.showPage("products page");
    }

    public void disableFiltering() {
        shoppingSystem.disableFiltering();
        //to refresh products page view
        SystemInitializer.showPage("products page");
    }
}
