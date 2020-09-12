package controller;

import exception.HasNotLoggedInException;
import exception.UserNotAllowedException;
import javassist.tools.rmi.ObjectNotFoundException;
import model.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Interface {
    private ShoppingSystem shoppingSystem;
    public Interface(Shop shop) {
        shoppingSystem=new ShoppingSystem(shop);
    }

    public void setInput(String input)  {
        if(input.contains("login page")) {
            loginRegisterPage();
        } else if(input.contains("personal page")) {
            personalPage();
        } else if(input.contains("products page")){
            productsPage();
        }
    }

    private void personalPage() {
        if(shoppingSystem.isLoggedIn()){
            if(shoppingSystem.isManager()){
                managerPage() ;
            }else if(shoppingSystem.isSeller()){
                sellerPage();
            }else{
                userPage();
            }
        }
    }
    private void loginRegisterPage(){
        String input = getProperty("") ;
        String[] words = input.split(" ") ;
        if(input.contains("create account")){
            createAccountPage(words[2],words[3]) ;
        }else if(input.contains("login")){
            loginPage(words[1],words[2]) ;
        }else if(input.contains("logout")){
            shoppingSystem.logout();
        }
    }

    private void loginPage(String userName, String password){
        if(!shoppingSystem.login(userName,password)){
            System.out.println("there was a problem logging in");
        } else{
            System.out.println("logged in");
        }
    }
    private void createAccountPage(String type,String username){
//        String[] words=input.split(" ");
//        String type=words[2];
//        String username=words[3];
        if(type.equals("manager")) {
            System.out.println("Only managers can add a new manager");
        } else if(type.equals("user")){
            if(shoppingSystem.signUp(type,"",username,getProperty("firstName"),getProperty("lasName"),getProperty("email")
                    ,getProperty("phoneNumber"),getProperty("password"))){
                System.out.println("you can log in now");
            }
            else {
                System.out.println("this username is used before");
            }
        }
        else if(type.equals("seller")){
            if(shoppingSystem.signUp(type,getProperty("company"),username,getProperty("firstName")
                    ,getProperty("lastName"),getProperty("email"),getProperty("phoneNumber"),getProperty("password"))){
                System.out.println("you can login after the manager's accept");
            }
            else {
                System.out.println("this username is used before");
            }
        }

    }
    private void viewPersonalInfoPage(){
        try{
            System.out.println(shoppingSystem.userInfo());
        }catch(HasNotLoggedInException e){
            System.err.println(e.getMessage());
            System.err.println("you haven't logged in yet");
        }
    }

    private void managerPage(){
        String input = getProperty("") ;
        String secondInput = getProperty("") ;
        if(input.contains("manage personal info")){
            managePersonalInfoPage(secondInput) ;
        }else if(input.contains("manage users")){
            manageUsersPage(secondInput) ;
        }else if(input.contains("manage all products")){
            manageAllProductsPage(secondInput);
        }else if(input.contains("manage discount code")){
            manageDiscountCodesPage(secondInput);
        }else if(input.contains("manage requests")){
            manageRequestsPage(secondInput) ;
        }else if(input.contains("manage categories")){
            manageCategoriesPage(secondInput) ;
        }
    }
    private void managePersonalInfoPage(String input) {
        String[] words = input.split(" ") ;
        if(input.contains("view personal info")){
            viewPersonalInfoPage() ;
        }else if(input.contains("edit")) {
            editPersonalInfoPage(words[1], words[2]);
        }
    }
    private void editPersonalInfoPage(String field, String alt){
        if(shoppingSystem.isLoggedIn()){
            if(field.toLowerCase().equals("username")){
                System.out.println("you can't change your username");
            }
            else {

                shoppingSystem.editProfile(field.toLowerCase(),alt);
            }
        }
    }

    private void manageUsersPage(String input){
        if(input.contains("view all users")){
            if(shoppingSystem.isManager()){
                ArrayList<User> users=shoppingSystem.usersList();
                for(int i=0;i<users.size();i++){
                    System.out.println((i+1)+") "+users.get(i).getId());
                }
            }
            else {
                System.out.println("Managers Only!");
            }
        }else if(input.contains("view")){
            if(shoppingSystem.isManager()){
                String username=input.split(" ")[1];
                try {
                    System.out.println(shoppingSystem.manageUserInfo(username).toString());
                } catch (ObjectNotFoundException e) {
                    System.err.println("wrong username") ;
                }
            }
            else {
                System.out.println("Managers Only!");
            }
        }else if(input.contains("delete user")){
            if(shoppingSystem.isManager()){
                String username=input.split(" ")[2];
                try {
                    if(shoppingSystem.deleteUser(username)){
                        System.out.println(username+" removed");
                    }else{
                        System.out.println("Managers Only!");
                    }
                } catch (ObjectNotFoundException e) {
                    System.err.println("wrong username");
                }
            }
//            else {
//            }
        }else if(input.contains("change type")){
            String[] words=input.split(" ");
            try{
                shoppingSystem.changeUserType(words[2],words[3]) ;
                System.out.println("User "+words[2]+"'s type changed to "+words[3]);
            }catch(ObjectNotFoundException e){
                System.err.println("wrong username") ;
            }catch(UserNotAllowedException e){
                System.err.println(e.getMessage());
                System.out.println("Managers Only!");
            }
        }
        else if(input.contains("create manager profile")){
            if(shoppingSystem.isManager()){
                boolean b=shoppingSystem.signUp("manager","",getProperty("username"),getProperty("firstname"),
                        getProperty("lastname"),getProperty("email"),getProperty("phone number"),getProperty("password"));
                if(b){
                    System.out.println("manager added");
                }
                else {
                    System.out.println("this username is used before");
                }
            }
            else {
                System.out.println("Managers Only!");
            }
        }
    }
    private void manageAllProductsPage(String input) {
        if (input.contains("remove")){
            try {
                shoppingSystem.removeProduct(input.trim().split(" ")[1]);
            } catch (ObjectNotFoundException e) {
                System.err.println("Product Not Found!") ;
            } catch (UserNotAllowedException e) {
                System.err.println(e.getMessage()) ;
            }
        }else if(input.contains("view all products")){
            viewAllProducts();
        }
    }
    private void manageDiscountCodesPage(String input) {
        String[] words = input.split(" ");
        if(input.contains("view discount codes")){
            for(Discount temp: shoppingSystem.getDiscounts()){
                System.out.println(temp.toString());
            }
        }else if(input.contains("view discount code")){
            try {
                Discount discount=shoppingSystem.viewDiscount(input.split(" ")[3]);
                System.out.println(discount.toString());
            }catch (ObjectNotFoundException e){
                System.err.println("discount not found");
            }catch(UserNotAllowedException e){
                System.out.println("you're not a manager");
            }
        }else if(input.contains("create discount code")){
            createDiscountPage(words[3],words[4],words[5],words[6],words[7],words[8]);
        }else if(input.contains("edit discount code")){
            editDiscountPage(words[3], words[4], words[5]);
        }else if(input.contains("remove discount code")){
            removeDiscountPage(words[3]);
        }
    }
    private void createDiscountPage(String code, String beginning, String ending, String amount, String max, String repeat){
        if(shoppingSystem.isManager()){
            shoppingSystem.createDiscount(code,beginning,ending,amount,max,repeat);
        }
        else {
            System.out.println("you are not a manager");
        }
    }
    private void editDiscountPage(String discountId,String field, String alt){
        if(shoppingSystem.isManager()){
            try {
                shoppingSystem.editDiscount(discountId,field,alt);
            }catch (ObjectNotFoundException e){
                System.err.println("discount not found");
            }
        }
        else {
            System.out.println("you are not a manager");
        }
    }
    private void removeDiscountPage(String discountId){
        try {
            shoppingSystem.deleteDiscount(discountId);
        } catch (ObjectNotFoundException e) {
            System.err.println(e.getMessage()) ;
        } catch (UserNotAllowedException e) {
            System.err.println(e.getMessage()) ;
        }
    }
    private void manageRequestsPage(String input){
        String[] parts = input.split(" ") ;
        if(input.contains("view all requests")){
            try {
                for(Request request : shoppingSystem.getRequests()){
                    System.out.println(request.toString());
                }
            } catch (UserNotAllowedException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("details")){
            try {
                System.out.println(shoppingSystem.getRequest(parts[1]).getDetails());
            } catch (ObjectNotFoundException | UserNotAllowedException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("accept") || input.contains("decline")){
            boolean accept = input.contains("accept") ;
            try {
                shoppingSystem.setRequestAccepted(accept, parts[1]);
            } catch (ObjectNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }

    }
    private void manageCategoriesPage(String in){
        try {
            String[] temp = in.trim().split(" ");
            ArrayList<String> arr = (ArrayList<String>) Arrays.asList(temp);
            if(in.contains("view all categories")){
                viewAllCategories();
            }
            else if (in.contains("edit")) {
                arr.remove(0);
                arr.remove(1);
                arr.remove(2);
                shoppingSystem.editCategory(temp[1], temp[2], arr);
            } else if (in.contains("add")) {
                arr.remove(0);
                arr.remove(1);
                shoppingSystem.addCategory(temp[1], arr);
            } else if (in.contains("remove")) {
                shoppingSystem.removeCategory(temp[1]);
            }
        }catch (ObjectNotFoundException e) {
            System.err.println("Category Not Found!") ;
        }
    }

    private String getProperty(String property){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your "+property);
        return scanner.nextLine();
    }

    private void viewAllCategories() {
        for (Categoory category: shoppingSystem.getCategories()){
            System.out.println(category.toString());
        }
    }
    private void viewAllProducts() {
        for(Product product: shoppingSystem.getProducts()){
            System.out.println(product.toString());
        }
    }

    private void sellerPage(){
        String input = getProperty("") ;
        String secondInput = getProperty("") ;
        if(input.contains("manage personal info")){
            managePersonalInfoPage(secondInput) ;
        }
        else if(input.contains("view company information")){
            try{
                System.out.println(((Seller)shoppingSystem.getCurrentUser()).getCompany());
            }
            catch (HasNotLoggedInException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("view sales history")){
            viewLogsHistory();
        }else if(input.contains("manage products")){
            manageProductsPage(secondInput);
        }else if (input.contains("show categories")){
            viewAllCategories() ;
        }else if(input.contains("manage offs")){
            manageOffsPage(secondInput);
        }else if(input.contains("view balance")){
            try {
                System.out.println(shoppingSystem.getBalance().toString());
            } catch (HasNotLoggedInException e) {
                System.err.println(e.getMessage()) ;
            }
        }
    }
    private void manageOffsPage(String input) {
        if (input.contains("view offs")){
            try{
                for (Sale sale:shoppingSystem.getOffs())
                    System.out.println(sale.toString());
            }catch(UserNotAllowedException e){
                System.err.println(e.getMessage()) ;
            }
        }else if(input.contains("view off")){
            String id=input.split(" ")[2];
            try {
                Sale offToShow=shoppingSystem.getOff(id);
                System.out.println(offToShow.toString());
            } catch (ObjectNotFoundException e) {
                System.err.println("off not found");
            }catch(UserNotAllowedException e){
                System.err.println(e.getMessage()) ;
            }
        }else if(input.contains("edit off")){
            String id=input.split(" ")[2];
            try {
                Sale offToEdit = shoppingSystem.getOff(id);

                Scanner scanner=new Scanner(System.in);
                System.out.println("which field do you want to change?");
                String field=scanner.nextLine();
                System.out.println("write the new value...");
                String alt=scanner.nextLine();
            } catch (ObjectNotFoundException e) {
                System.err.println("off not found");
            } catch(UserNotAllowedException e){
                System.err.println(e.getMessage()) ;
            }
        }
    }
    private void manageProductsPage(String input) {
        String[] parts = input.split(" ") ;
        if(input.contains("view all products")){
            try {
                for(Product product: shoppingSystem.getSellerProducts()){
                    System.out.println(product.toString());
                }
            } catch (UserNotAllowedException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("view")){
            try {
                Product productToView = shoppingSystem.getSellerProduct(parts[1]) ;
                System.out.println(productToView.toString());
            } catch (ObjectNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (UserNotAllowedException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("view buyers")){
            try {
                for (Log log: shoppingSystem.getSellerLogs(parts[2])){
                    System.out.println(log.toString());
                }
            } catch (ObjectNotFoundException | UserNotAllowedException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("edit")){
            try {
                shoppingSystem.editProduct(parts[1], parts[2], parts[3]);
            } catch (ObjectNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (UserNotAllowedException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("add product")){
            int amount = Integer.parseInt(parts[5]);
            try {
                shoppingSystem.addProductRequest(parts[2],new BigInteger(parts[3]),parts[4],amount);
            } catch (UserNotAllowedException | ObjectNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("remove product")){
            try {
                shoppingSystem.removeProduct(parts[2]);
            } catch (ObjectNotFoundException | UserNotAllowedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    private void viewLogsHistory() {
        try{
            for (Log log: shoppingSystem.getCurrentUser().getLogs()) {
                System.out.println(log.toString());
            }
        }catch( HasNotLoggedInException e){
            System.err.println(e.getMessage()) ;
        }
    }

    private void userPage(){
        String input = getProperty("") ;
        String secondInput = getProperty("") ;
        if (input.contains("manage personal info")){
            managePersonalInfoPage(secondInput);
        }else if (input.contains("view cart")){
            viewCartPage(secondInput);
        }else if (input.contains("view orders")){
            viewOrdersPage();
        }else if (input.contains("view balance")){
            viewBalancePage();
        }else if (input.contains("view discount codes")){
            viewDiscountCodesPage();
        }
    }
    private void viewDiscountCodesPage() {
        //TODO
    }
    private void viewBalancePage() {
        //TODO
    }
    private void viewOrdersPage() {
        //TODO
    }
    private void viewCartPage(String secondInput) {
        //TODO
    }

    private void productsPage(){
        String input = getProperty("") ;
        String[] words = input.split(" ") ;
        if(input.contains("view products")){
            viewAllProducts() ;
        } else if(input.contains("view product")){
            viewProductPage(words[2]);
        } else if(input.contains("view categories")){
            viewAllCategories() ;
        }else if(input.contains("filtering")){
            input = getProperty("") ;
            filteringPage(input) ;
        }else if(input.contains("sorting")){
            input = getProperty("") ;
            sortingPage(input) ;
        }else if(input.contains("compare")){
            try {
                System.out.println(shoppingSystem.compareTwoProduct(words[1],words[2]));
            } catch (ObjectNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }else if(input.contains("comments")){
            //TODO : comment on product
        }
    }

    private void sortingPage(String input) {
        String[] parts = input.split(" ") ;
        if(input.contains("show available sorts")){
            System.out.println("1. Sort by price\n"+
                    "2. sort by rating\n"+
                    "3. sort by name");
        }else if(input.contains("sort by")){
            sortProducts(parts[2]);
        }else if(input.contains("current sort")){
            System.out.println(shoppingSystem.getCurrentSort().toString());
        }else if(input.contains("disable sort")){
            shoppingSystem.disableSorting();
        }
    }
    private void sortProducts(String sortType) {
        if(sortType.contains("price")){
            shoppingSystem.sortProductByPrice();
        }else if(sortType.contains("rating")){
            shoppingSystem.sortProductByRating();
        } else if(sortType.contains("name")){
            shoppingSystem.sortProductByName();
        }
        for(Product product: shoppingSystem.getSortedProducts()){
            System.out.println(product.toString());
        }
    }

    private void filteringPage(String input) {

        if(input.contains("show available filters")){
            System.out.println("filter by 'categoryName'\n"
                    +"filter by 'productName'\n"
                    +"filer by 'productPrice'\n"
                    +"filter by 'productExisting'") ;
        } else if (input.contains("filter")){
            input = getProperty("") ;
            filterProduct(input);
        } else if (input.contains("current filter")){
            for (ProductFilterOptions filter: shoppingSystem.getCurrentFilters()){
                System.out.println(filter.toString());
            }
        } else if (input.contains("disable filters")){
            shoppingSystem.disableFiltering();
        }
    }
    private void filterProduct(String input) {
        if(input.contains("product name")){
            shoppingSystem.filterProductsByName(input.trim().split(" ")[2]) ;
        }else if(input.contains("category name")){
            try{
                shoppingSystem.filterProductsByCategoryName(input.trim().split(" ")[2]) ;
            }catch(ObjectNotFoundException e){
                System.err.println("category Not Found!");
            }
        }else if(input.contains("product price")){
            shoppingSystem.filterProductByPrice(new BigInteger(input.trim().split(" ")[2]),new BigInteger(input.trim().split(" ")[3])) ;
        }else if(input.contains("product existing")){
            shoppingSystem.filterByExistingProducts() ;
        }
        for (Product product : shoppingSystem.getFilteredProducts()){
            System.out.println(product.toString());
        }
    }

    private void viewProductPage(String productId) {
        try {
            System.out.println(shoppingSystem.getProduct(productId));
            String input = getProperty("") ;
            if(input.contains("digest")){
                System.out.println(shoppingSystem.getProduct(productId).getProductInfo());
            }else if(input.contains("add to cart")){
                shoppingSystem.addProductToCart(productId,3);
            }else if(input.contains("attributes")){

            }
        } catch (ObjectNotFoundException e) {
            System.err.println(e.getMessage()) ;
        }


    }
}
