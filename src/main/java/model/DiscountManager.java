package model;

import javassist.tools.rmi.ObjectNotFoundException;

import java.util.ArrayList;

public class DiscountManager {

    private ArrayList<Discount> discounts;

    public DiscountManager(){
        this.discounts = new ArrayList<>();
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

    Discount findDiscount(String code) throws ObjectNotFoundException{
        for(Discount temp: discounts){
            if(temp.getCode().equals(code))
                return temp;
        }
        throw new ObjectNotFoundException("discount not found");
    }
}
