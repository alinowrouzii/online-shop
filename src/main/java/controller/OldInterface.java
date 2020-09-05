package controller;//package model;
//
//import exception.HasNotLoggedInException;
//import exception.UserNotAllowedException;
//import javassist.tools.rmi.ObjectNotFoundException;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class OldInterface {
//    private ShoppingSystem shoppingSystem;
//    public OldInterface(Shop shop) {
//        shoppingSystem=new ShoppingSystem(shop);
//    }
//
//    public void setInput(String input)  {
//        if(input.contains("create account")){
//            String[] words=input.split(" ");
//            String type=words[2];
//            String username=words[3];
//            if(type.equals("manager")) {
//                System.out.println("Only managers can add a new manager");
//            } else if(type.equals("user")){
//                if(shoppingSystem.signup(type,"",username,getProperty("firstName"),getProperty("lasName"),getProperty("email")
//                ,getProperty("phoneNumber"),getProperty("password"))){
//                    System.out.println("you can log in now");
//                }
//                else {
//                    System.out.println("this username is used before");
//                }
//            }
//            else if(type.equals("seller")){
//                if(shoppingSystem.signup(type,getProperty("company"),username,getProperty("firstName")
//                ,getProperty("lastName"),getProperty("email"),getProperty("phoneNumber"),getProperty("password"))){
//                    System.out.println("you can login after the manager's accept");
//                }
//                else {
//                    System.out.println("this username is used before");
//                }
//            }
//        }
//        else if(input.contains("login")){
//            String[] words=input.split(" ");
//            String password=getProperty("password");
//            if(!shoppingSystem.login(words[1],password)){
//                System.out.println("there was a problem logging in");
//            } else{
//                System.out.println("logged in");
//            }
//        }else if(input.contains("view personal info")){
//            try{
//                System.out.println(shoppingSystem.userInfo());
//            }catch(HasNotLoggedInException e){
//                System.err.println(e.getMessage());
//                System.err.println("you haven't logged in yet");
//            }
//        }else if(input.contains("edit discount code")){
//            String[] words=input.split(" ");
//            String code=words[3];
//            if(shoppingSystem.isManager()){
//                System.out.println("which field do you want to change?");
//                Scanner scanner=new Scanner(System.in);
//                String field=scanner.nextLine();
//                System.out.println("new value:");
//                String alt=scanner.nextLine();
//                try {
//                    shoppingSystem.editDiscount(code,field,alt);
//                }catch (ObjectNotFoundException e){
//                    System.err.println("discount not found");
//                }
//            }
//            else {
//                System.out.println("you are not a manager");
//            }
//        }else if(input.contains("remove discount code")){
//            try {
//                shoppingSystem.deleteDiscount(input.split(" ")[3]);
//            }catch (ObjectNotFoundException e){
//                System.err.println("discount not found");
//            }
//        }
//        else if(input.contains("edit")){
//            if(shoppingSystem.isLoggedIn()){
//                String[] words=input.split(" ");
//                String field=words[1];
//                if(field.toLowerCase().equals("username")){
//                    System.out.println("you can't change your username");
//                }
//                else {
//                    String changed=getProperty(field);
//                    shoppingSystem.editProfile(field.toLowerCase(),changed);
//                }
//            }
//        }else if(input.contains("manage users")){
//            if(shoppingSystem.isManager()){
//                ArrayList<User> users=shoppingSystem.usersList();
//                for(int i=0;i<users.size();i++){
//                    System.out.println((i+1)+") "+users.get(i).getId());
//                }
//            }
//            else {
//                System.out.println("Managers Only!");
//            }
//        }else if(input.contains("view discount codes")){
//            for(Discount temp: shoppingSystem.getDiscounts()){
//                System.out.println(temp.toString());
//            }
//        }else if(input.contains("view discount code")){
//            try {
//                Discount discount=shoppingSystem.viewDiscount(input.split(" ")[3]);
//                System.out.println(discount.toString());
//            }catch (ObjectNotFoundException e){
//                System.err.println("discount not found");
//            }catch(UserNotAllowedException e){
//                System.out.println("you're not a manager");
//            }
//        }else if(input.contains("view company information")){
//            try{
//                System.out.println(shoppingSystem.companyInfo());
//            }
//            catch(UserNotAllowedException e){
//                System.err.println(e.getMessage());
//            }
//        }else if(input.contains("view sales history")){
//            try{
//                for (Log log: shoppingSystem.getLogsHistory()){
//                    System.out.println(log.toString());
//                }
//            }catch(UserNotAllowedException e){
//                System.err.println(e.getMessage()) ;
//            }
//        }else if (input.contains("view offs")){
//            try{
//                for (Sale sale:shoppingSystem.getOffs())
//                    System.out.println(sale.toString());
//            }catch(UserNotAllowedException e){
//                System.err.println(e.getMessage()) ;
//            }
//        }else if(input.contains("view off")){
//            String id=input.split(" ")[2];
//            try {
//                Sale offToShow=shoppingSystem.getOff(id);
//                System.out.println(offToShow.toString());
//            } catch (ObjectNotFoundException e) {
//                System.err.println("off not found");
//            }catch(UserNotAllowedException e){
//                System.err.println(e.getMessage()) ;
//                System.err.println("you aren't a seller");
//            }
//        }else if(input.contains("edit off")){
//            String id=input.split(" ")[2];
//            try {
//                Sale offToEdit = shoppingSystem.getOff(id);
//
//                Scanner scanner=new Scanner(System.in);
//                System.out.println("which field do you want to change?");
//                String field=scanner.nextLine();
//                System.out.println("write the new value...");
//                String alt=scanner.nextLine();
//                shoppingSystem.editSale(offToEdit,field,alt);
//            } catch (ObjectNotFoundException e) {
//                System.err.println("off not found");
//            } catch(UserNotAllowedException e){
//                System.err.println(e.getMessage()) ;
//            }
//        }else if(input.contains("add off")){
//            try {
//                Scanner scanner=new Scanner(System.in);
//                System.out.println("Sale id:");
//                String id=scanner.nextLine();
//                System.out.println("Beginning date");
//                String beg=scanner.nextLine();
//                String[] calender=beg.split("/");
//                int year=Integer.parseInt(calender[0]);
//                int month=Integer.parseInt(calender[1]);
//                int day=Integer.parseInt(calender[2]);
//                Date beginning=new Date(year,month,day);
//                System.out.println("ending date");
//                beg=scanner.nextLine();
//                calender=beg.split("/");
//                year=Integer.parseInt(calender[0]);
//                month=Integer.parseInt(calender[1]);
//                day=Integer.parseInt(calender[2]);
//                Date ending=new Date(year,month,day);
//                System.out.println("amount");
//                double amount=scanner.nextDouble();
//                shoppingSystem.addOff(id,beginning,ending,amount);
//            }catch (UserNotAllowedException e){
//                System.err.println(e.getMessage());
//            }
//        }
//        else if(input.contains("view")){
//            if(shoppingSystem.isManager()){
//                String username=input.split(" ")[1];
//                try {
//                    System.out.println(shoppingSystem.manageUserInfo(username).toString());
//                } catch (ObjectNotFoundException e) {
//                    System.err.println("wrong username") ;
//                }
//            }
//            else {
//                System.out.println("Managers Only!");
//            }
//        }else if(input.contains("delete user")){
//            if(shoppingSystem.isManager()){
//                String username=input.split(" ")[2];
//                try {
//                    if(shoppingSystem.deleteUser(username)){
//                        System.out.println(username+" removed");
//                    }else{
//                       System.out.println("Managers Only!");
//                    }
//                } catch (ObjectNotFoundException e) {
//                    System.err.println("wrong username");
//                }
//            }
////            else {
////            }
//        }else if(input.contains("change type")){
//            String[] words=input.split(" ");
//            try{
//                shoppingSystem.changeUserType(words[2],words[3]) ;
//                System.out.println("User "+words[2]+"'s type changed to "+words[3]);
//            }catch(ObjectNotFoundException e){
//                System.err.println("wrong username") ;
//            }catch(UserNotAllowedException e){
//                System.err.println(e.getMessage());
//                System.out.println("Managers Only!");
//            }
//        }
//        else if(input.contains("create manager profile")){
//            if(shoppingSystem.isManager()){
//                boolean b=shoppingSystem.signup("manager","",getProperty("username"),getProperty("firstname"),
//                        getProperty("lastname"),getProperty("email"),getProperty("phone number"),getProperty("password"));
//                if(b){
//                    System.out.println("manager added");
//                }
//                else {
//                    System.out.println("this username is used before");
//                }
//            }
//            else {
//                System.out.println("Managers Only!");
//            }
//        }
//        else if(input.contains("products")){
//            String in = getProperty("");
//            if(in.contains("view categories")){
//               for(Categoory category : shoppingSystem.getCategories()){
//                   System.out.println(category.toString());
//               }
//            }else if(in.contains("filtering")){
//                in = getProperty("") ;
//                if(in.contains("show available filters")){
//                    System.out.println("filter by 'categoryName'\n"
//                    +"filter by 'productName'\n"
//                    +"filer by 'productPrice'\n"
//                    +"filter by 'productExisting'") ;
//                } else if (in.contains("filter")){
//                    ArrayList<Product> products = new ArrayList<>();
//                    if(in.contains("productName")){
//                        products = shoppingSystem.filterProductsByName(in.trim().split(" ")[2]) ;
//
//                    }else if(in.contains("categoryName")){
//                        try{
//                            products = shoppingSystem.filterProductsByCategoryName(in.trim().split(" ")[2]) ;
//                        }catch(ObjectNotFoundException e){
//                            products = new ArrayList<>();
//                            System.err.println("category Not Found!");
//                        }
//                    }else if(in.contains("productPrice")){
//                        products = shoppingSystem.filterProductByPrice(new BigInteger(in.trim().split(" ")[2]),new BigInteger(in.trim().split(" ")[3])) ;
//                    }else if(in.contains("productExisting")){
//                        products = shoppingSystem.filterByExistingProducts() ;
//                    }
//                    for (Product product : products){
//                        System.out.println(product.toString());
//                    }
//                }
//            }else if(in.contains("manage all products")){
//                in = getProperty("") ;
//                if (in.contains("remove")){
//                    try {
//                        shoppingSystem.removeProduct(in.trim().split(" ")[1]);
//                    } catch (ObjectNotFoundException e) {
//                        System.err.println("Product Not Found!") ;
//                    } catch (UserNotAllowedException e) {
//                        System.err.println(e.getMessage()) ;
//                    }
//                }
//            }else if(in.contains("manage categories")){
//                try {
//                    in = getProperty("");
//                    String[] temp = in.trim().split(" ");
//                    ArrayList<String> arr = (ArrayList<String>) Arrays.asList(temp);
//                    if (in.contains("edit")) {
//                        arr.remove(0);
//                        arr.remove(1);
//                        arr.remove(2);
//                        shoppingSystem.editCategory(temp[1], temp[2], arr);
//                    } else if (in.contains("add")) {
//                        arr.remove(0);
//                        arr.remove(1);
//                        shoppingSystem.addCategory(temp[1], arr);
//                    } else if (in.contains("remove")) {
//                        shoppingSystem.removeCategory(temp[1]);
//                    }
//                }catch (ObjectNotFoundException e) {
//                    System.err.println("Category Not Found!") ;
//                }
//            }
//
//        }
//    }
//
//    private String getProperty(String property){
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("Enter your "+property);
//        return scanner.nextLine();
//    }
//}