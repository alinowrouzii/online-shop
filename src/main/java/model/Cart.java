package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Cart {
    private HashMap<Product,Integer> products;
    private BigInteger totalPrice ;
    private static final int REMOVE_TIME = 45 ;
    Cart(){
        products = new HashMap<>();
        totalPrice = new BigInteger("0") ;
    }
    boolean addProduct(Product product,int amount) {
        if(product.decreaseAmountOfProducts(amount)) {
            int oldAmount = 0;
            //TODO: should be checked
            if (products.get(product) != null) {
                oldAmount = products.get(product);
            }
            removeProductAfter(REMOVE_TIME, product, amount);
            updateTotalPrice();
            products.put(product, amount + oldAmount);
            return true;
        }
        return false;
    }

    private void updateTotalPrice() {
        totalPrice = new BigInteger("0");
        for (Product product : products.keySet()){
            int amount = products.get(product);
            totalPrice = totalPrice.add(product.getPrice().multiply(new BigInteger(amount+"")));
        }
    }

    private void removeProductAfter(int minutes, Product product,int amount){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(minutes*60*60*1000);
                removeProduct(product.getProductId(), amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    void removeProduct(String productId,int amount) {
        for (Product product :products.keySet()) {
            if(product.getProductId().equals(productId)){
                if(products.get(product)>amount){
                    int newAmount = products.get(product) - amount;
                    products.put(product,newAmount);
                    product.addAmountOfProducts(amount);
                    return;
                }else if(products.get(product) == amount){
                    products.remove(product);
                    product.addAmountOfProducts(amount);
                    return;
                }
            }
        }
    }
    public BigInteger getTotalPrice() {
        return totalPrice ;
    }
    public HashMap<Product,Integer> getProducts() {
        return products;
    }
}
