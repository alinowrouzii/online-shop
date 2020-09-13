package model;

import java.math.BigInteger;
import java.util.ArrayList;

public class Product {
    private String productId ;
    private ProductStatus productStatus ;
    private String productName ;
    private int amountOfProducts ;
    private String productBrand ;
    private BigInteger price ;
    private Seller seller ;
    private boolean isExists ;
    private Categoory categoory;
    private String productInfo ;
    private ArrayList<Rate> ratingList ;
    private double averageRating ;
    private ArrayList<Comment> commentsList ;

    public Product(String productId, String productName, BigInteger price, Categoory category, Seller seller , int amountOfProducts){
        this.productId = productId ;
        this.productName = productName ;
        this.price = price ;
        this.categoory = category ;
        this.seller = seller ;
        isExists = true ;
        ratingList = new ArrayList<>() ;
        commentsList = new ArrayList<>() ;
        averageRating = 0 ;
        productInfo = "" ;
        productStatus = ProductStatus.AWAITINGTOBUILD;
        this.amountOfProducts = amountOfProducts ;
    }
    public void setProductInfo(String productInfo){
        this.productInfo = productInfo ;
    }
    public void setProductStatus(ProductStatus status){
        this.productStatus = status ;
    }
    public void addComment(Comment comment){
        commentsList.add(comment) ;
    }

    public void rate(Rate rate){
        if(rate.getRate()>=1 && rate.getRate()<=5){
            ratingList.add(rate) ;
            updateAverageRating() ;
        }
    }

    private void updateAverageRating(){
        double sum = 0 ;
        for(Rate rate : ratingList){
            sum += rate.getRate() ;
        }
        averageRating = sum / ratingList.size() ;
    }

    public void addAmountOfProducts(int amount){
        if(amount > 0 ){
            this.amountOfProducts += amount ;
        }
        isExists = amountOfProducts > 0 ;
    }

    public boolean decreaseAmountOfProducts(int amount){
        if(amountOfProducts >= amount && amount>0 ){
            amountOfProducts -= amount;
            isExists = amountOfProducts>0 ;
            return true;
        }
        isExists = amountOfProducts>0 ;
        return false ;
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    public String getProductId() {
        return productId;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public BigInteger getPrice() {
        return price;
    }

    public boolean isExists() {
        return isExists;
    }

    public Categoory getCategory() {
        return categoory;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public ArrayList<Rate> getRatingList() {
        return ratingList;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public ArrayList<Comment> getCommentsList() {
        return commentsList;
    }
    void editProduct(String field, String alt){
        if(field.equals("price")){
            this.price = new BigInteger("alt") ;
        }else if(field.equals("product info")){
            this.productInfo = alt ;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", price=" + price +
                ", isExists=" + isExists +
                ", averageRating=" + averageRating +
                '}';
    }
    Seller getSeller(){
        return seller;
    }
}
