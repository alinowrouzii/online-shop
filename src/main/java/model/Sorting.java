package model;

import comperator.ProductNameComparator;
import comperator.ProductPriceComparator;
import comperator.ProductRatingComparator;

import java.util.ArrayList;
import java.util.Collections;

public class Sorting {

    public static ArrayList<Product> sortByPrice(ArrayList<Product> products){
        Collections.sort(products, new ProductPriceComparator()) ;
        return products;
    }
    public static ArrayList<Product> sortByName(ArrayList<Product> products){
        Collections.sort(products, new ProductNameComparator()) ;
        return products;
    }
    public static ArrayList<Product> sortByRating(ArrayList<Product> products){
        Collections.sort(products, new ProductRatingComparator()) ;
        return products;
    }
}
