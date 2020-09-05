package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {
    private BigInteger paidAmount ;
    private BigInteger discountedAmount ;
    private ArrayList<Product> purchasedProductsList ;
    private String sellerName ;
    private boolean deliveryStatus ;
    public BuyLog(String logId, Date date, BigInteger paidAmount, BigInteger discountedAmount, ArrayList<Product> purchasedProductsList , String sellerName){
        super(logId, date);
        this.paidAmount = paidAmount;
        this.discountedAmount = discountedAmount;
        this.purchasedProductsList = purchasedProductsList;
        this.sellerName = sellerName;
    }

    public void setShippingStatus(boolean deliveryStatus){
        this.deliveryStatus = deliveryStatus ;
    }

    public BigInteger getPaidAmount() {
        return paidAmount;
    }

    public BigInteger getDiscountedAmount() {
        return discountedAmount;
    }

    public ArrayList<Product> getPurchasedProductsList() {
        return purchasedProductsList;
    }

    public String getSellerName() {
        return sellerName;
    }

    public boolean isDeliveryStatus() {
        return deliveryStatus;
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "logId='" + super.getLogId() + '\'' +
                ", logDate=" + super.getLogDate() +
                "paidAmount=" + paidAmount +
                ", discountedAmount=" + discountedAmount +
                ", purchasedProductsList=" + purchasedProductsList +
                ", sellerName='" + sellerName + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                '}';
    }
}
