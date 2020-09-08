package controller;

import model.Shop;
import model.ShoppingSystem;

public class SystemInitializer {
    private static ShoppingSystem shoppingSystem;
    public SystemInitializer(){
        shoppingSystem=new ShoppingSystem(new Shop());
    }
    public static ShoppingSystem getShoppingSystem(){
        return shoppingSystem;
    }
}
