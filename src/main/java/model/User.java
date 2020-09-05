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
    BigInteger getBalance(){
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

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }
    String getCredit(){
        return credit;
    }

    void setCredit(String credit) {
        this.credit = credit;
    }

    boolean isLoggedIn() {
        return isLoggedIn;
    }

    void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    public ArrayList<Log> getLogs() {
        return logs;
    }
}
