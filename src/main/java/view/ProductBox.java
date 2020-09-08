package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

import javax.swing.*;

public class ProductBox extends VBox {
    private ImageView view;
    private Image image;
    private Hyperlink name;
    private Rating rating;
    private Label price;
    public ProductBox(String name,int rating){
        this.name = new Hyperlink(name);
        this.rating = new Rating(rating);
        price = new Label();

        initialize();
    }
    private void initialize() {
//        setPrefSize(200,200);
        view = new ImageView();
        view.setFitWidth(180);
        view.setFitHeight(210);
//        view.setPreserveRatio(true);

        rating.setUpdateOnHover(false);
        rating.setPartialRating(false);
        rating.setMax(5);


        setAlignment(Pos.BASELINE_CENTER);
        setPadding(new Insets(6,4,3,4));
        setSpacing(10);
//        name.setLayoutX(71.0);
//        name.setLayoutY(150);
//        rating.setLayoutX(10.0);
//        rating.setLayoutY(185.0);

        getChildren().addAll(view, name,price, rating);
        changeBackgroundOnHoverUsingEvents(this);
    }
    public void changeBackgroundOnHoverUsingEvents(final Node node) {
        node.setStyle("-fx-effect:none;");
        node.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                node.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
            }
        });
        node.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                node.setStyle("-fx-effect:none;");
            }
        });
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
    public void setPrice(String price){
        this.price.setText(price);
    }
}