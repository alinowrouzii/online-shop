package comperator;

import model.Product;

import java.util.Comparator;

public class ProductRatingComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getAverageRating() - o2.getAverageRating());
    }
}
