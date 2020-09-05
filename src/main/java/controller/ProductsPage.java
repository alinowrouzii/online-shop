package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.RangeSlider;
import view.ProductBox;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ProductsPage implements Initializable {
    public GridPane productsPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i=0 ;i<7 ; i++){
            ProductBox box = new ProductBox("product"+(i+1), 5);
            box.setImage(new Image(new File("src/main/resources/icons/product"+(i+13)+".png").toURI().toString()));
            Random random = new Random();
            box.setRating(i%5.12);
            productsPane.add(box,i%3,i/3);
            int finalI = i;
            box.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    System.out.println("Open product page| product: "+ (finalI +1));
                    //TODO : open product page when click on product
                }
            });
        }

    }
}
