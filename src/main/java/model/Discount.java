package model;


import java.util.ArrayList;

public class Discount {
    private String code;
    private Date beginning;
    private Date ending;
    private String amount;
    private String maxDiscount;
    private int repeat;
    private ArrayList<User> includedUsers;

    public Discount(String code, Date beginning, Date ending, String amount, String maxDiscount, int repeat){
        this.code=code;
        this.beginning=beginning;
        this.ending=ending;
        this.amount=amount;
        this.maxDiscount=maxDiscount;
        this.repeat=repeat;
        includedUsers=new ArrayList<User>();
    }

    public void addUser(User userToAdd){
        includedUsers.add(userToAdd);
    }

    public String getCode() {
        return code;
    }

    public Date getBeginning() {
        return beginning;
    }

    public Date getEnding() {
        return ending;
    }

    public ArrayList<User> getIncludedUsers() {
        return includedUsers;
    }

    public int getRepeat() {
        return repeat;
    }

    public String getAmount() {
        return amount;
    }

    public String getMaxDiscount() {
        return maxDiscount;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setMaxDiscount(String maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }


    @Override
    public String toString() {
        String users="";
        for(User user:includedUsers){
            users+=user.toString();
            users+="\n";
        }
        return "Discount:" +
                "code='" + code + '\'' +
                ", beginning=" + beginning +
                ", ending=" + ending +
                ", amount='" + amount + '\'' +
                ", maxDiscount='" + maxDiscount + '\'' +
                ", repeat=" + repeat +
                ", includedUsers=" + users;
    }
}
