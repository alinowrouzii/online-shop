package model;

import javassist.tools.rmi.ObjectNotFoundException;

import java.util.ArrayList;

public class UsersManager {
    private ArrayList<User> users;
    private ArrayList<Request> requests;
    public UsersManager(ArrayList<Request> requests){
        users = new ArrayList<>();
        this.requests = requests;
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

    void addUser(User userToAdd){
        users.add(userToAdd);
    }
    void removeUser(User userToRemove){
        users.remove(userToRemove);
    }
    void editProfile(String field,String alt,User user){
        String fieldToChange=field.toLowerCase();
        switch(fieldToChange){
            case "first name":
                user.setFirstName(alt);
                break ;
            case "last name":
                user.setLastName(alt);
                break;
            case "password":
                user.setPassword(alt);
                break ;
            case "email":
                user.setEmail(alt);
                break ;
            case "phone number":
                user.setPhoneNumber(alt);
                break ;
        }
    }
    User findUserById(String id) throws ObjectNotFoundException {
        for (User temp: users){
            if(temp.getId().equals(id))
                return temp;
        }
        throw new ObjectNotFoundException("User not found!") ;
    }
    ArrayList<User> getUsers(){
        return users;
    }
}
