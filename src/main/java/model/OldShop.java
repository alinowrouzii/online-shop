package model;

import javassist.tools.rmi.ObjectNotFoundException;

import java.math.BigInteger;
import java.util.ArrayList;

public class OldShop {
    private ArrayList<User> users;
    private ArrayList<Product> products;
    private ArrayList<Categoory> categories ;
    private ArrayList<Request> requests;
    private ArrayList<Discount> discounts;
    public OldShop(){
        discounts=new ArrayList<>();
        users=new ArrayList<>();
        products=new ArrayList<>();
        categories=new ArrayList<>();
        requests=new ArrayList<>();
        users.add(new Manager("manager","","","","","0"));



        User user1 = new User("11112", "ali", "nowrouzi", "alinowrou@qq","9998885","132321");
        User user2 = new User("1212aaa", "mmm", "kkkk", "kjjj@qq","9998885","132321");
        User user3 = new User("djd222", "jjj", "nowrouzi", "mm@qq","789465","132321");
        User user4 = new User("rwlj123", "kkk", "jjjjj", "ll@qq","123456","132321");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);


        Categoory cat = new Categoory("books", new ArrayList<String>());
        categories.add(cat) ;
        Seller seller = new Seller("rwlj123", "kkk", "jjjjj", "ll@qq","123456","132321","nothing");

        Product product = new Product("2","book",new BigInteger("111000"),cat,seller,5);
        Product product2 = new Product("3","book1",new BigInteger("22000"),cat,seller,5);
        Product product3 = new Product("5","book2",new BigInteger("33000"),cat,seller,5);
        Product product4 = new Product("6","book3",new BigInteger("44000"),cat,seller,5);
        Product product5 = new Product("12","book4",new BigInteger("55000"),cat,seller,5);
        Product product6 = new Product("13","book5",new BigInteger("66000"),cat,seller,5);
        Product product7 = new Product("14","book6",new BigInteger("77000"),cat,seller,5);
        Product product8 = new Product("15","book7",new BigInteger("88000"),cat,seller,5);
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        cat.addProduct(product);
        cat.addProduct(product2);
        cat.addProduct(product3);
        cat.addProduct(product4);
        cat.addProduct(product5);
        cat.addProduct(product6);
        cat.addProduct(product8);
        cat.addProduct(product7);
    }
    void addDiscount(Discount discount){
        discounts.add(discount);
    }
    void removeDiscount(Discount discount){
        discounts.remove(discount);
    }
    ArrayList<Discount> getDiscounts(){
        return discounts;
    }
    void addRequest(Request requestToAdd){
        requests.add(requestToAdd);
    }
    void removeRequest(Request requestToRemove){
        requests.remove(requestToRemove);
    }
    public ArrayList<Request> getRequests(){
        return requests;
    }
    //check if one's username is used before
    boolean repeatedIdForUsers(String id){
        for (User temp: users){
            if(temp.getId().equals(id)){
                return true;
            }
        }
        for(Request request :requests){
            if (request instanceof SellerRequest){
                if(request.getRequestId().equals(id)){
                    return true;
                }
            }
        }
        return false;
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
    //TODO : use this when need to create productID
    String getSuggestedProductId(){
        int id = Integer.parseInt(products.get(products.size()-1).getProductId())+1;
        while(repeatedIdForProduct(id)){
            id++;
        }
        return id+ "" ;
    }
    boolean categoryIsExists(String categoryName){
        for(Categoory category : categories){
            if(category.getName().equals(categoryName)){
                return true ;
            }
        }
        return false ;
    }
    void addUser(User userToAdd){
        users.add(userToAdd);
    }
    void removeUser(User userToRemove){
        users.remove(userToRemove);
    }
    void editProfile(String field,String alt,User user){
        String fieldToChange=field.toLowerCase();
        switch(fieldToChange){
            case "firstname":
                user.setFirstName(alt);
                break ;
            case "lastname":
                user.setLastName(alt);
                break;
            case "password":
                user.setPassword(alt);
                break ;
            case "email":
                user.setEmail(alt);
                break ;
            case "phonenumber":
                user.setPhoneNumber(alt);
                break ;
        }
    }
    void addCategory(Categoory category){
        categories.add(category);
    }
    void removeCategory(Categoory category){
        categories.remove(category);
    }

    ArrayList<Product> getProducts(){
        return products;
    }
    ArrayList<Categoory> getCategories(){
        return categories;
    }

    ArrayList<User> getUsers() {
        return users;
    }
    User findUserById(String id) throws ObjectNotFoundException {
        for (User temp: users){
            if(temp.getId().equals(id))
                return temp;
        }
        throw new ObjectNotFoundException("User not found!") ;
    }
    Categoory findCategoryByName(String categoryName) throws ObjectNotFoundException {
        for (Categoory temp: categories){
            if(temp.getName().equals(categoryName))
                return temp;
        }
        throw new ObjectNotFoundException("User not found!") ;
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
    Product findProductById(String productId) throws ObjectNotFoundException {
        for(Product product : products){
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        throw new ObjectNotFoundException("product not found!");
    }
    Discount findDiscount(String code) throws ObjectNotFoundException{
        for(Discount temp: discounts){
            if(temp.getCode().equals(code))
                return temp;
        }
        throw new ObjectNotFoundException("discount not found");
    }


    void acceptRequest(Request request) {
        if(request instanceof SellerRequest){
            Seller seller = ((SellerRequest) request).getNewSeller();
            users.add(seller);
        }else if(request instanceof ProductRequest){
            Product product = ((ProductRequest) request).getNewProduct();
            product.getSeller().addProduct(product);
            product.getCategory().addProduct(product);
            products.add(product);
        }else if(request instanceof SaleRequest){
            Sale sale = ((SaleRequest) request).getNewSale();
            Seller seller=((SaleRequest) request).getSeller();
            seller.addSale(sale);
        }
            requests.remove(request);
    }

    public Request getRequest(String requestId) throws ObjectNotFoundException {
        for(Request request : requests){
            if(request.getRequestId().equals(requestId)){
                return request;
            }
        }
        throw new ObjectNotFoundException("Request not found!") ;
    }

}
