package model;


import javassist.tools.rmi.ObjectNotFoundException;

import java.util.ArrayList;

public class ProductsManager {
    private ArrayList<Product> products;
    private ArrayList<Request> requests;
    public ProductsManager(ArrayList<Request> requests){
        products = new ArrayList<>();
        this.requests = requests;
    }
    public Product findProductById(String productId) throws ObjectNotFoundException {
        for(Product product: products){
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        throw new ObjectNotFoundException("Product not found");
    }
    boolean productIsExists(String productId){
        for(Product product : products){
            if(product.getProductId().equals(productId)){
                return true;
            }
        }
        return false ;
    }
    void removeProduct(Product product){
        products.remove(product);
        product.getSeller().removeProduct(product);
        product.getCategory().removeProduct(product);
    }
    void addProduct(Product product){
        products.add(product);
        product.getSeller().addProduct(product);
        product.getCategory().addProduct(product);
    }
    private boolean repeatedIdForProduct(int id){
//        if(products.size()<= id){
//            return true ;
//        }
        for(Request request :requests){
            if (request instanceof ProductRequest){
                if(request.getRequestId().equals(id)){
                    return true;
                }
            }
        }
        return false;
    }
    String getSuggestedProductId(){
        int id = Integer.parseInt(products.get(products.size()-1).getProductId())+1;
        while(repeatedIdForProduct(id)){
            id++;
        }
        return id+ "" ;
    }
    ArrayList<Product> getProducts(){
        return products;
    }
    public String compare(String firstProductId,String secondProductId) throws ObjectNotFoundException {
        Product p1 = findProductById(firstProductId);
        Product p2 = findProductById(secondProductId);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-8s%-10s%-10s\n","      ","product1","product2"));
        builder.append(String.format("%-8s%-10s%-10s\n","Name  ",p1.getProductName(),p2.getProductName()));
        builder.append(String.format("%-8s%-10s%-10s\n","Id    ",p1.getProductId(),p2.getProductId()));
        builder.append(String.format("%-8s%-10s%-10s\n","price ",p1.getPrice().toString(),p2.getPrice().toString()));
        builder.append(String.format("%-8s%-10s%-10s\n","brand ",p1.getProductBrand(),p2.getProductBrand()));
        builder.append(String.format("%-8s%-10s%-10s\n","rating",p1.getAverageRating(),p2.getAverageRating()));
        return builder.toString();
    }
}