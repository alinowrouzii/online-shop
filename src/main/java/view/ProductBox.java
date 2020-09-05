package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

import javax.swing.*;

public class ProductBox extends VBox {
    private ImageView view;
    private Image image;
    private Hyperlink name;
    private Rating rating;
    public ProductBox(String name,int rating){
        this.name = new Hyperlink(name);
        this.rating = new Rating(rating);
        initialize();
    }
    private void initialize() {
//        setPrefSize(200,200);
        view = new ImageView();
        view.setFitWidth(180);
        view.setFitHeight(210);
        view.setPreserveRatio(true);

        rating.setUpdateOnHover(false);
        rating.setPartialRating(false);
        rating.setMax(5);

        setAlignment(Pos.BASELINE_CENTER);
        setPadding(new Insets(6,4,3,4));
//        name.setLayoutX(71.0);
//        name.setLayoutY(150);
//        rating.setLayoutX(10.0);
//        rating.setLayoutY(185.0);

        getChildren().addAll(view, name, rating);
    }
    public void setImage(Image image){
        this.image = image;
        this.view.setImage(image);
    }

    public void addEventHandler(EventHandler eventHandler) {
    }
    public void setRating(double num){
        rating.setRating(num);
    }
}
