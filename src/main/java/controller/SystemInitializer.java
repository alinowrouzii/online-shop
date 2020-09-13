package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Shop;
import model.ShoppingSystem;

import java.io.File;
import java.io.IOException;

public class SystemInitializer {
    private static ShoppingSystem shoppingSystem;
    private static Stage primaryStage;
    public SystemInitializer(Stage primaryStage){
        SystemInitializer.primaryStage = primaryStage;
        shoppingSystem=new ShoppingSystem(new Shop());
    }
    public static ShoppingSystem getShoppingSystem(){
        return shoppingSystem;
    }

    public static void showPage(String page){
        String title = "";
        if(page.contains("main menu page")){
            title = "MainMenuPage";
        }else if(page.contains("login page")){
            title = "LoginPage";
        }else if(page.contains("sign up page")){
            title = "signUpPage";
        } else if(page.contains("products page")){
            title = "ProductsPage";
        } else if(page.contains("cart page")){
            title = "CartPage";
        }else if(page.contains("user page")){
            title = "UserAccountPage";
        }else if(page.contains("seller page")){
            title = "SellerAccountPage";
        }else if(page.contains("manager page")){
            title = "ManagerAccountPage";
        }else if(page.contains("about us page")){
            //TODO: returns void as temporary
            return;
        }
        try {
            Parent root;
            root = FXMLLoader.load(new File("src/main/java/view/" + title + ".fxml").toURI().toURL());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Sign up");
            primaryStage.show();
        }catch(IOException e) {
            System.err.println("cant load this page: "+title);
        }




    }

}
