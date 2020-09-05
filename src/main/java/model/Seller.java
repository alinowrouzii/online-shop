package model;

import javassist.tools.rmi.ObjectNotFoundException;

import java.util.ArrayList;

public class Seller extends User {
    private String company;
    private ArrayList<Product> products;
    private ArrayList<Sale> sales;

    public Seller(String id, String firstName, String lastName, String email, String phoneNumber, String password,String company){
        super(id,firstName,lastName,email,phoneNumber,password);
        this.company=company;
        products=new ArrayList<Product>();
        sales=new ArrayList<Sale>();
    }

    public void addProduct(Product productToAdd){
        products.add(productToAdd);
    }
    public void removeProduct(Product productToRemove){
        products.remove(productToRemove);
    }
    public void addSale(Sale sale){
        sales.add(sale);
    }
    public void removeSale(Sale sale){
        sales.remove(sale);
    }

    @Override
    public String toString() {
        return "Seller:" +super.toString()+'\''+
                "company='" + company + '\'' +
                '}';
    }

     public String getCompany(){
        return company;
     }

     public void setCompany(String company){
        this.company=company;
     }
     ArrayList<Sale> getSales(){
        return sales;
     }
     Sale findSaleViaId(String id) throws ObjectNotFoundException {
        for(Sale sale:sales){
            if(sale.getId().equals(id))
                return sale;
        }
        throw new ObjectNotFoundException("sale not found");
     }
     public ArrayList<Product> getProducts() {
        return products;
     }

     public Product getProduct(String productId) throws ObjectNotFoundException {
        for(Product product : products){
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        throw new ObjectNotFoundException("Product not found!");
    }
}
