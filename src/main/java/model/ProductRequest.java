package model;

public class ProductRequest extends Request {
    private Product newProduct;
    public ProductRequest(Product product){
        super();
        newProduct=product;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    @Override
    public String getDetails() {
        return "Product Request :\n"+"requestId:"+ newProduct.getProductId()+"\nproduct detail: "+ newProduct.toString() ;
    }

    @Override
    public String getRequestId() {
        return newProduct.getProductId();
    }
}
