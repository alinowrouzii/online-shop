package model;


import java.util.ArrayList;

public class Categoory {
    private String categoryName ;
    private ArrayList<String> specifiedFeatures ;
    private ArrayList<Product> productsList ;

    public Categoory(String categoryName , ArrayList<String> specifiedFeatures) {
        this.categoryName = categoryName;
        this.specifiedFeatures = specifiedFeatures ;
        productsList = new ArrayList<Product>() ;
    }

    void addProduct(Product product){
        productsList.add(product) ;
    }
    void removeProduct(Product product){
        productsList.remove(product);
    }
    public String getName(){
        return categoryName ;
    }
    public ArrayList<Product> getProducts() {
        return productsList ;
    }

    @Override
    public String toString() {
        String res = "Categoory{" +
                "categoryName: '" + categoryName + '\'' +
                "| specifiedFeatures: " ;
        for (String specifiedFeature: specifiedFeatures ) {
            res = res + specifiedFeature + ", " ;
        }
        return res;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName ;
    }

    public void setfeatures(ArrayList<String> features) {
        this.specifiedFeatures = features ;
    }
}
