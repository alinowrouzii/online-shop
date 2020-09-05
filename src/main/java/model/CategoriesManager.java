package model;

import javassist.tools.rmi.ObjectNotFoundException;

import java.util.ArrayList;

public class CategoriesManager {
    private ArrayList<Categoory> categories;
    public CategoriesManager() {
        categories = new ArrayList<Categoory>();
    }

    public void addCategory(Categoory category) {
        categories.add(category);
    }
    public void removeCategory(Categoory category){
        categories.remove(category);
    }

    Categoory findCategoryByName(String categoryName) throws ObjectNotFoundException {
        for (Categoory temp: categories){
            if(temp.getName().equals(categoryName))
                return temp;
        }
        throw new ObjectNotFoundException("User not found!") ;
    }

    public ArrayList<Categoory> getCategories() {
        return categories;
    }
    boolean categoryIsExists(String categoryName){
        for(Categoory category : categories){
            if(category.getName().equals(categoryName)){
                return true ;
            }
        }
        return false ;
    }
}
