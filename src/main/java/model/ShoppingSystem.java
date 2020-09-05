package model;


import exception.HasNotLoggedInException;
import exception.UserNotAllowedException;
import javassist.tools.rmi.ObjectNotFoundException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShoppingSystem {
    private Shop shop;
    private User currentUser ;
    private boolean isLoggedIn;
    private Cart cartProducts ;
    private ArrayList<Product> filteredProducts;
    private ArrayList<Product> sortedProducts;
    private HashSet<ProductFilterOptions> currentFilters;
    private ProductSortOptions currentSort ;
    public ShoppingSystem(Shop shop){
        filteredProducts = new ArrayList<>() ;
        sortedProducts=new ArrayList<>();
        this.shop=shop;
        this.isLoggedIn = false;
        cartProducts = new Cart();
        currentFilters = new HashSet<>();
        currentSort = ProductSortOptions.WITHOUTSORT;
    }

    public boolean signUp(String type,String company,String id, String firstName, String lastName, String email, String phoneNumber, String password) {
        if(!shop.getUsersManager().repeatedIdForUsers(id)){
            User user;
            switch(type){
                case "seller" :
                    user=new Seller(id,firstName,lastName,email,phoneNumber,password,company);
                    shop.getRequestManager().addRequest(new SellerRequest((Seller) user));
                    break;
//                case "manager" :
//                    // should be changed
//                    user=new Manager(id,firstName,lastName,email,phoneNumber,password);
//                    shop.addUser(user);
//                    break ;
                case "user" :
                    user=new User(id,firstName,lastName,email,phoneNumber,password);
                    shop.getUsersManager().addUser(user);
                    break ;
            }
            return true ;
        }
        return false ;
    }

    public boolean login(String userId , String password){
        if(!isLoggedIn){
            for(User user : shop.getUsersManager().getUsers()){
                if(user.getId().equals(userId)&& user.getPassword().equals(password) && !user.isLoggedIn()){
                    currentUser = user;
                    user.setLoggedIn(true);
                    isLoggedIn = true;
                    return true ;
                }
            }
        }
        return false;
    }

    public void logout() {
        isLoggedIn=false;
        cartProducts = new Cart();
        currentUser = null;
        disableFiltering();
        disableSorting();
    }

    public ArrayList<Product> getProducts(){
       return shop.getProductsManager().getProducts();
    }

    public void addProductToCart(String productId){
        for(Product product:shop.getProductsManager().getProducts()){
            if(product.getProductId().equals(productId) && product.isExists()){
                cartProducts.addProduct(product) ;
                return ;
            }
        }
    }

    public void removeProductFromCart(String productId){
        cartProducts.removeProduct(productId) ;
    }

    public ArrayList<Categoory> getCategories() {
        return shop.getCategoriesManager().getCategories();
    }

    public  void filterProductsByCategoryName(String categoryName) throws ObjectNotFoundException {
        currentFilters.add(ProductFilterOptions.FILTERBYCATEGORYNAME);
        if(filteredProducts.size()>0){
            this.filteredProducts = Search.searchByCategoryName(categoryName, filteredProducts) ;
            return;
        }
        this.filteredProducts = Search.searchByCategoryName(shop.getCategoriesManager().getCategories(), categoryName) ;
    }

    public void filterProductsByName(String productName) {
        currentFilters.add(ProductFilterOptions.FILTERBYNAME);
        if(filteredProducts.size()>0){
            this.filteredProducts = Search.searchByProductName(productName, filteredProducts) ;
            return;
        }
        this.filteredProducts = Search.searchByProductName(productName, shop.getProductsManager().getProducts()) ;
    }

    public void filterProductByPrice(BigInteger minPrice, BigInteger maxPrice){
        currentFilters.add(ProductFilterOptions.FILTERBYPRICE);
        if(filteredProducts.size()>0){
            this.filteredProducts = Search.searchByPrice(filteredProducts,minPrice,maxPrice) ;
            return;
        }
        this.filteredProducts = Search.searchByPrice(shop.getProductsManager().getProducts(),minPrice,maxPrice) ;
    }

    public void filterByExistingProducts(){
        currentFilters.add(ProductFilterOptions.FILTERBYEXISTING);
        if(filteredProducts.size()>0){
            this.filteredProducts = Search.searchByExisting(filteredProducts) ;
            return;
        }
        this.filteredProducts = Search.searchByExisting(shop.getProductsManager().getProducts()) ;
    }

    public ArrayList<Product> getCartProduct(){
        return cartProducts.getProducts() ;
    }

    public void payment(){
        //TODO : pay for the products and update info
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    public String userInfo() throws HasNotLoggedInException {
        if (isLoggedIn) {
            return currentUser.toString();
        }
        throw new HasNotLoggedInException("User has not loggedIn!") ;
    }
    public void editProfile(String field, String alt){
        shop.getUsersManager().editProfile(field,alt,currentUser);
    }
    public boolean isManager(){
        return currentUser instanceof Manager ;
    }
    public ArrayList<User> usersList(){
        return shop.getUsersManager().getUsers();
    }
    public User manageUserInfo(String username) throws ObjectNotFoundException {
        return shop.getUsersManager().findUserById(username);
    }
    public boolean deleteUser(String username) throws ObjectNotFoundException {
        if(currentUser instanceof Manager){
            shop.getUsersManager().removeUser(shop.getUsersManager().findUserById(username));
            return true ;
        }
        return false ;
    }
    public void changeUserType(String username,String role) throws ObjectNotFoundException, UserNotAllowedException {
        if(currentUser instanceof Manager) {
            User curr = shop.getUsersManager().findUserById(username);
            shop.getUsersManager().removeUser(curr);
            User alt = null;
            switch (role) {
            case "user":
                alt = new User(curr.getId(), curr.getFirstName(), curr.getLastName()
                        , curr.getEmail(), curr.getPhoneNumber(), curr.getPassword());
                break;
            case "manager" :
                alt = new Manager(curr.getId(), curr.getFirstName(), curr.getLastName()
                        , curr.getEmail(), curr.getPhoneNumber(), curr.getPassword());
                break;
            case "seller":
                alt = new Seller(curr.getId(), curr.getFirstName(), curr.getLastName()
                        , curr.getEmail(), curr.getPhoneNumber(), curr.getPassword(), "");
                break ;
            }

            if(alt != null){
                shop.getUsersManager().addUser(alt);
            }
        }else {
            throw new UserNotAllowedException("User not allowed") ;
        }
    }
    public void addCategory(String categoryName , ArrayList<String> features){
        if(currentUser instanceof Manager){
            if (!shop.getCategoriesManager().categoryIsExists(categoryName)){
                shop.getCategoriesManager().addCategory(new Categoory(categoryName,features)) ;
            }
        }
    }
    public void removeCategory(String categoryName) throws ObjectNotFoundException {
        if(currentUser instanceof Manager) {
            Categoory currentCategory = shop.getCategoriesManager().findCategoryByName(categoryName) ;
            shop.getCategoriesManager().removeCategory(currentCategory);
        }
    }
    public void editCategory(String categoryName ,String fieldToChange , ArrayList<String> features) throws ObjectNotFoundException {
        if(currentUser instanceof Manager) {
            Categoory currentCategory = shop.getCategoriesManager().findCategoryByName(categoryName);
            if (fieldToChange.toLowerCase().equals("name")) {
                currentCategory.setCategoryName(features.get(0));
            } else if (fieldToChange.toLowerCase().equals("features")) {
                currentCategory.setfeatures(features);
            }
        }
    }
    public void removeProduct(String productId) throws ObjectNotFoundException, UserNotAllowedException {
        if(currentUser instanceof Manager){
            Product product = shop.getProductsManager().findProductById(productId);
            shop.getProductsManager().removeProduct(product);
            return ;
        }else if(currentUser instanceof Seller){
            for(Product product : ((Seller)currentUser).getProducts()){
                if(product.getProductId().equals(productId)){
                    shop.getProductsManager().removeProduct(product);
                    return;
                }
            }
        }
        throw new UserNotAllowedException("User not allowed");
    }
    public  ArrayList<Discount> getDiscounts(){
        return shop.getDiscountManager().getDiscounts();
    }
    public Discount viewDiscount(String code) throws ObjectNotFoundException, UserNotAllowedException {
        if(currentUser instanceof Manager){
            return shop.getDiscountManager().findDiscount(code);
        }
        throw new UserNotAllowedException("User not allowed") ;
    }
    public void editDiscount(String code,String field, String alt) throws ObjectNotFoundException{
        Discount discount=shop.getDiscountManager().findDiscount(code);
        if(field.equals("amount"))
            discount.setAmount(alt);
        else if(field.equals("repeat"))
            discount.setRepeat(Integer.parseInt(alt));
        else if(field.equals("beginning") || field.equals("ending")){
            String[] cal=alt.split("/");
            int year=Integer.parseInt(cal[0]);
            int month=Integer.parseInt(cal[1]);
            int day=Integer.parseInt(cal[2]);
            Date date=new Date(year,month,day);
            if(field.equals("beginning"))
                discount.setBeginning(date);
            else
                discount.setEnding(date);
        }
    }
    public  void deleteDiscount(String code) throws ObjectNotFoundException, UserNotAllowedException {
        if (currentUser instanceof Manager) {
            Discount discount = shop.getDiscountManager().findDiscount(code);
            shop.getDiscountManager().removeDiscount(discount);
            return;
        }
        throw new UserNotAllowedException("User not allowed") ;
    }
    public void createDiscount(String code, String beginning,String ending,String amount, String max, String repeat){
        String[] beginCal=beginning.split("/");
        String[] endCal=ending.split("/");
        Date begin=new Date(Integer.parseInt(beginCal[0]),Integer.parseInt(beginCal[1]),Integer.parseInt(beginCal[2]));
        Date end=new Date(Integer.parseInt(endCal[0]),Integer.parseInt(endCal[1]),Integer.parseInt(endCal[2]));
        Discount discount=new Discount(code,begin,end,amount,max,Integer.parseInt(repeat));
        shop.getDiscountManager().addDiscount(discount);
    }
//    public String companyInfo() throws UserNotAllowedException {
//        if(!(currentUser instanceof Seller))
//            throw new UserNotAllowedException("User not allowed!") ;
//        return ((Seller) currentUser).getCompany();
//    }
//    public ArrayList<Log> getLogsHistory() throws UserNotAllowedException {
//        if(!(currentUser instanceof Manager))
//            return currentUser.getLogs();
//        throw new UserNotAllowedException("User not allowed") ;
//    }
    public ArrayList<Sale> getOffs() throws UserNotAllowedException {
        if(currentUser instanceof Seller)
            return ((Seller) currentUser).getSales();
        throw new UserNotAllowedException("User not allowed") ;
    }
    public Sale getOff(String id) throws ObjectNotFoundException, UserNotAllowedException {
        if(currentUser instanceof Seller)
            return ((Seller) currentUser).findSaleViaId(id);
        throw new UserNotAllowedException("User not allowed") ;
    }
    public void editSale(Sale sale,String field, String alt){
        if(field.equals("id")){
            sale.setId(alt);
        }
        else if(field.equals("beginning")){
            String[] calender=alt.split("/");
            int year=Integer.parseInt(calender[0]);
            int month=Integer.parseInt(calender[1]);
            int day=Integer.parseInt(calender[2]);
            sale.setBeginning(new Date(year,month,day));
        }
        else if(field.equals("ending")){
            String[] calender=alt.split("/");
            int year=Integer.parseInt(calender[0]);
            int month=Integer.parseInt(calender[1]);
            int day=Integer.parseInt(calender[2]);
            sale.setEnding(new Date(year,month,day));
        }
    }
    public void addOff(String id,Date beginning,Date ending,double amount)throws UserNotAllowedException{
        if(currentUser instanceof Seller){
            ((Seller) currentUser).addSale(new Sale(id,beginning,ending,amount));
            return;
        }
        throw new UserNotAllowedException("you are not a seller");
    }

    public boolean isSeller() {
        return currentUser instanceof Seller;
    }

    public Product getSellerProduct(String productId) throws ObjectNotFoundException, UserNotAllowedException {
        if(currentUser instanceof Seller){
            return ((Seller) currentUser).getProduct(productId);
        }
        throw new UserNotAllowedException("User not allowed") ;
    }

    public ArrayList<Product> getSellerProducts() throws UserNotAllowedException {
        if(currentUser instanceof Seller) {
            return ((Seller) currentUser).getProducts();
        }
        throw new UserNotAllowedException("User not allowed");
    }

    /**
     * Returns logs that contain entered product
     *
     * @param productId
     * @return
     * @throws ObjectNotFoundException
     * @throws UserNotAllowedException
     */
    public ArrayList<Log> getSellerLogs(String productId) throws ObjectNotFoundException, UserNotAllowedException {
        ArrayList<Log> result = new ArrayList<>();
        if(currentUser instanceof Seller){
            Product product = ((Seller) currentUser).getProduct(productId);
            for(Log log : currentUser.getLogs()){
                if(log instanceof SellLog){
                    if(((SellLog)log).getSelledProductsList().contains(product)){
                        result.add(log);
                    }
                }
            }
            return result;
        }
        throw new UserNotAllowedException("User not allowed") ;
    }
    public void editProduct(String productId, String field, String alt) throws ObjectNotFoundException, UserNotAllowedException {
        if(currentUser instanceof Seller){
            Product productToEdit = ((Seller) currentUser).getProduct(productId);
            productToEdit.editProduct(field, alt);
        }
        throw new UserNotAllowedException("User not allowed") ;
    }
    public User getCurrentUser() throws HasNotLoggedInException {
        if (isLoggedIn){
            return currentUser;
        }
        throw new HasNotLoggedInException("User not logged in!") ;
    }
    public Product getProduct(String productId) throws ObjectNotFoundException {
        for (Product product : shop.getProductsManager().getProducts()){
            if (product.getProductId().equals(productId)){
                return product;
            }
        }
        throw new ObjectNotFoundException("Product not found!") ;
    }

    public ArrayList<Product> getFilteredProducts() {
        if (filteredProducts.size()>0){
            return filteredProducts ;
        }
        return shop.getProductsManager().getProducts() ;
    }
    public void disableFiltering(){
        currentFilters.clear() ;
        filteredProducts.clear();
    }

    public void sortProductByPrice(){
        currentSort = ProductSortOptions.SORTBYPRICE;
        if (filteredProducts.size()>0){
            sortedProducts = Sorting.sortByPrice(filteredProducts) ;
            return ;
        }
        sortedProducts = Sorting.sortByName(shop.getProductsManager().getProducts()) ;
    }
    public void sortProductByName(){
        currentSort = ProductSortOptions.SORTBYNAME;

        if (filteredProducts.size()>0){
            sortedProducts = Sorting.sortByName(filteredProducts) ;
            return ;
        }
        sortedProducts = Sorting.sortByName(shop.getProductsManager().getProducts()) ;
    }
    public void sortProductByRating(){
        currentSort = ProductSortOptions.SORTBYRATING;
        if (filteredProducts.size()>0){
            sortedProducts = Sorting.sortByRating(filteredProducts) ;
            return ;
        }
        sortedProducts = Sorting.sortByName(shop.getProductsManager().getProducts()) ;
    }

    public ArrayList<Product> getSortedProducts(){
        return sortedProducts ;
    }
    public void disableSorting() {
        currentSort = ProductSortOptions.WITHOUTSORT;
        sortedProducts.clear();
    }

    public ArrayList<Request> getRequests() throws UserNotAllowedException {
        if(currentUser instanceof Manager){
            return shop.getRequestManager().getRequests();
        }
        throw new UserNotAllowedException("User not allowed") ;
    }

    public Request getRequest(String requestId) throws ObjectNotFoundException, UserNotAllowedException {
        if(currentUser instanceof Manager && isLoggedIn){
            return shop.getRequestManager().getRequest(requestId);
        }
        throw new UserNotAllowedException("User not allowed") ;
    }
    public void setRequestAccepted(boolean accept,String requestId ) throws ObjectNotFoundException {
        if(currentUser instanceof Manager && isLoggedIn){
            Request request = shop.getRequestManager().getRequest(requestId);
            if(accept){
                shop.acceptRequest(request);
            }else{
                shop.getRequestManager().getRequests().remove(request);
            }
        }
    }
    public void addProductRequest(String productName,BigInteger price, String categoryName,int amount) throws UserNotAllowedException, ObjectNotFoundException {
        if(currentUser instanceof Seller && isLoggedIn){
            String productId= shop.getProductsManager().getSuggestedProductId();
            Categoory category = shop.getCategoriesManager().findCategoryByName(categoryName) ;
            Product product = new Product(productId,productName,price,category,(Seller)currentUser,amount);
            ProductRequest productRequest = new ProductRequest(product);
            shop.getRequestManager().addRequest(productRequest);
            return;
        }
        throw new UserNotAllowedException("User not allowed!");
    }

    public BigInteger getBalance() throws HasNotLoggedInException {
        if(isLoggedIn) {
            return currentUser.getBalance();
        }
        throw new HasNotLoggedInException("User not loggedIn  yet!");
    }
    public HashSet<ProductFilterOptions> getCurrentFilters() {
        return currentFilters;
    }
    public ProductSortOptions getCurrentSort(){
        return currentSort;
    }
    public String compareTwoProduct(String p1, String p2) throws ObjectNotFoundException {
        return shop.getProductsManager().compare(p1, p2);
    }
}
