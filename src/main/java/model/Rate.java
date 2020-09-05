package model;

public class Rate {
    private Product product;
    private Seller seller ;
    private int rate ;
    public Rate(Seller seller,Product product , int rate) {
        this.seller = seller ;
        this.product = product;
        this.rate = rate;
    }
    public int getRate() {
        return rate;
    }
}
