package model;

public class Manager extends User {
    public Manager(String id, String firstName, String lastName, String email, String phoneNumber, String password){
        super(id,firstName,lastName,email,phoneNumber,password);
    }

    @Override
    public String toString() {
        return "Manager: "+super.toString();
    }

}
