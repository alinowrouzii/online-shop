package model;

public class SellerRequest extends Request {
    private Seller newSeller;
    public SellerRequest(Seller newSeller){
        super();
        this.newSeller=newSeller;
    }

    public Seller getNewSeller() {
        return newSeller;
    }

    @Override
    public String getDetails() {
        return "Seller Request :\n"+"requestId:"+ newSeller.getId()+"\nproduct detail: "+ newSeller.toString() ;
    }

    @Override
    public String getRequestId() {
        return newSeller.getId();
    }
}
