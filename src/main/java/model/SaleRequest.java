package model;

public class SaleRequest extends Request {
    private Sale newSale;
    private Seller seller;
    public SaleRequest(Sale sale,Seller seller){
        super();
        newSale=sale;
        this.seller=seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public Sale getNewSale() {
        return newSale;
    }

    @Override
    public String getDetails() {
        return null;
    }

    @Override
    public String getRequestId() {
        return newSale.getId();
    }
}
