package model;

import javassist.tools.rmi.ObjectNotFoundException;

import java.math.BigInteger;
import java.util.ArrayList;

public class Search {

    public static ArrayList<Product> searchByExisting(ArrayList<Product> products){
        ArrayList<Product> result = new ArrayList<Product>();
        for(Product product : products){
            if(product.isExists()){
                result.add(product);
            }
        }
        return result;
    }
    public static ArrayList<Product> searchByBrand(ArrayList<Product> products, String brand){
        ArrayList<Product> result = new ArrayList<Product>();
        for (Product product:products){
            if(product.getProductBrand().equals(brand)){
                result.add(product);
            }
        }
        return result ;
    }
    public static ArrayList<Product> searchByPrice(ArrayList<Product> products, BigInteger minPrice, BigInteger maxPrice){
        ArrayList<Product> result = new ArrayList<Product>();
        for (Product product : products){
            if(product.getPrice().compareTo(minPrice)>=0 && product.getPrice().compareTo(maxPrice)<=0){
                result.add(product);
            }
        }
        return result ;
    }
    public static ArrayList<Product> searchByCategoryName(ArrayList<Categoory> categories,String categoryName) throws ObjectNotFoundException {
        for(Categoory category: categories){
            if(category.getName().equals(categoryName)){
                return category.getProducts() ;
            }
        }
        throw new ObjectNotFoundException("Object not found!");
    }

    public static ArrayList<Product> searchByCategoryName(String categoryName ,ArrayList<Product> products) {
        ArrayList<Product> res = new ArrayList<Product>() ;
        for(Product product: products){
            if(product.getCategory().getName().equals(categoryName)){
                res.add(product);
            }
        }
        return res;
    }

        public static ArrayList<Product> searchByProductName(String productName,ArrayList<Product> products) {
        ArrayList<Product> result = new ArrayList<Product>();
        for (Product product : products){
            if(product.getProductName().contains(productName)){
                result.add(product);
            }
        }
        return result;
    }
}
