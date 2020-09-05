package model;

import java.util.ArrayList;

public class Sale {

    private String id;
    private ArrayList<Product> products;
    private SaleStatus saleStatus;
    private Date beginning;
    private Date ending;
    private double amount;

    public Sale(String id, Date beginning, Date ending, double amount){
        this.id=id;
        this.beginning=beginning;
        this.ending=ending;
        this.amount=amount;
        this.products=new ArrayList<Product>();
        this.saleStatus=SaleStatus.START_CHECK;
    }

    public void addProduct(Product productToAdd){
        //TODO: change the product's price
        products.add(productToAdd);
    }

    @Override
    public String toString() {
        String productList="";
        for(Product temp: products){
            productList+=temp.toString();
        }
        return "id='" + id + '\'' +
                ", products=" + productList +
                ", beginning=" + beginning +
                ", ending=" + ending +
                ", amount=" + amount ;
    }

    public String getId() {
        return id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }
}
