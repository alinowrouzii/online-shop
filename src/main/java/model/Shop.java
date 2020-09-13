package model;

import java.math.BigInteger;
import java.util.ArrayList;

public class Shop {
    private UsersManager usersManager;
    private ProductsManager productsManager;
    private CategoriesManager categoriesManager;
    private DiscountManager DiscountManager;
    private RequestManager requestManager;
    public Shop(){
        requestManager = new RequestManager();
        usersManager = new UsersManager(requestManager.getRequests());
        productsManager = new ProductsManager(requestManager.getRequests());
        categoriesManager = new CategoriesManager();
        DiscountManager = new DiscountManager();




        User user1 = new User("11112", "ali", "nowrouzi", "alinowrou@qq","9998885","132321");
        User user2 = new User("1212aaa", "mmm", "kkkk", "kjjj@qq","9998885","132321");
        User user3 = new User("djd222", "jjj", "nowrouzi", "mm@qq","789465","132321");
        User user4 = new User("rwlj123", "kkk", "jjjjj", "ll@qq","123456","132321");
        usersManager.addUser(user1);
        usersManager.addUser(user2);
        usersManager.addUser(user3);
        usersManager.addUser(user4);


        Categoory cat = new Categoory("books", new ArrayList<String>());
        categoriesManager.addCategory(cat); ;
        Seller seller = new Seller("rwlj123", "kkk", "jjjjj", "ll@qq","123456","132321","nothing");

        Product product = new Product("13","booooook",new BigInteger("111000"),cat,seller,5);
        Product product2 = new Product("14","book1",new BigInteger("22000"),cat,seller,5);
        Product product3 = new Product("15","book2",new BigInteger("33000"),cat,seller,5);
        Product product4 = new Product("16","book3",new BigInteger("44000"),cat,seller,5);
        Product product5 = new Product("17","book4",new BigInteger("55000"),cat,seller,5);
        Product product6 = new Product("18","book5",new BigInteger("66000"),cat,seller,5);
        Product product7 = new Product("19","book6",new BigInteger("77000"),cat,seller,5);
        Product product8 = new Product("20","book7",new BigInteger("88000"),cat,seller,5);
        Product product9 = new Product("21","book9",new BigInteger("99000"),cat,seller,5);
        Product product10 = new Product("22","book10",new BigInteger("17000"),cat,seller,5);
        Product product11 = new Product("23","book11",new BigInteger("132000"),cat,seller,5);
        Product product12 = new Product("24","book11",new BigInteger("132000"),cat,seller,5);
        productsManager.addProduct(product);
        productsManager.addProduct(product2);
        productsManager.addProduct(product3);
        productsManager.addProduct(product4);
        productsManager.addProduct(product5);
        productsManager.addProduct(product6);
        productsManager.addProduct(product7);
        productsManager.addProduct(product8);
        productsManager.addProduct(product9);
        productsManager.addProduct(product10);
        productsManager.addProduct(product11);
        productsManager.addProduct(product12);
        cat.addProduct(product);
        cat.addProduct(product2);
        cat.addProduct(product3);
        cat.addProduct(product4);
        cat.addProduct(product5);
        cat.addProduct(product6);
        cat.addProduct(product7);
        cat.addProduct(product8);
        cat.addProduct(product9);
        cat.addProduct(product10);
        cat.addProduct(product11);
    }

    void acceptRequest(Request request) {
        if(request instanceof SellerRequest){
            Seller seller = ((SellerRequest) request).getNewSeller();
            usersManager.addUser(seller);
        }else if(request instanceof ProductRequest){
            Product product = ((ProductRequest) request).getNewProduct();
            productsManager.addProduct(product);
        }else if(request instanceof SaleRequest){
            Sale sale = ((SaleRequest) request).getNewSale();
            //TODO : what should we do? :))
        }
        requestManager.removeRequest(request);
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }

    public ProductsManager getProductsManager() {
        return productsManager;
    }

    public CategoriesManager getCategoriesManager() {
        return categoriesManager;
    }

    public model.DiscountManager getDiscountManager() {
        return DiscountManager;
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }
}
