package controller;

import javassist.tools.rmi.ObjectNotFoundException;
import model.Shop;

import java.util.Scanner;

public class Manage {
    private static Interface interFace;
    private static Shop shop ;

    public static void main(String[] args) throws ObjectNotFoundException {
        Scanner scanner = new Scanner(System.in);
        shop = new Shop() ;
        interFace = new Interface(shop) ;
        while (true) {
            System.out.println("hello");
            String input = scanner.nextLine();
            interFace.setInput(input);
        }
    }
}
