package model;

import java.math.BigInteger;
import java.util.ArrayList;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String credit;
    private ArrayList<Log> logs;
    private ArrayList<Discount> discounts;
    private BigInteger balance;
    private boolean isLoggedIn;

    public User(String id, String firstName, String lastName, String email, String phoneNumber, String password){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
        logs=new ArrayList<Log>();
        discounts=new ArrayList<Discount>();
        balance=new BigInteger("0");
    }
    void increaseBalance(BigInteger amount){
        balance = balance.add(amount);
    }
    boolean decreaseBalance(BigInteger amount){
        if(balance.compareTo(amount)>0){
            balance = balance.subtract(amount);
            return true;
        }
        return false;
    }
    public BigInteger getBalance(){
        return balance;
    }
    public void addLog(Log log){
        logs.add(log);
    }
    public void removeLog(Log log){
        logs.remove(log);
    }

    public void addDiscount(Discount discount){
        discounts.add(discount);
    }

    public void removeDiscount(Discount discount){
        discounts.remove(discount);
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    public String getId() {
        return id;
    }
    //no need for setId method

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getCredit(){
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    public ArrayList<Log> getLogs() {
        return logs;
    }
}
