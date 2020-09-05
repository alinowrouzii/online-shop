package model;

import java.math.BigInteger;
import java.util.ArrayList;
public class Cart {
    private ArrayList<Product> products;
    private BigInteger totalPrice ;
    private static final int REMOVE_TIME = 45 ;
    Cart(){
        products = new ArrayList<Product>();
        totalPrice = new BigInteger("0") ;
    }
    void addProduct(Product product) {
        products.add(product);
        product.decreaseAmountOfProducts(1);
        updateTotalPrice();
        removeProductAfter(REMOVE_TIME, product) ;
    }

    private void updateTotalPrice() {
        totalPrice = new BigInteger("0");
        for (Product product : products){
            totalPrice = totalPrice.add(product.getPrice());
        }
    }

    private void removeProductAfter(int minutes, Product product){
        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Thread.sleep(minutes*60*60*1000);
                    products.remove(product);
                    updateTotalPrice();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    void removeProduct(String productId) {
        for (Product product :products) {
            if(product.getProductId().equals(productId)){
                products.remove(product);
                product.addAmountOfProducts(1);
                break ;
            }
        }
    }
    public BigInteger getTotalPrice() {
        return totalPrice ;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
}
