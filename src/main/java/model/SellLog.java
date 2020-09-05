package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class SellLog extends Log {
    private BigInteger receivedAmount ;
    private BigInteger discountedAmount ;
    private ArrayList<Product> selledProductsList ;
    private String buyerName ;
    private boolean shippingStatus ;

    public SellLog(String logId, Date date, BigInteger receivedAmount, BigInteger discountedAmount, ArrayList<Product> selledProductsList , String buyerName){
        super(logId,date);
        this.receivedAmount = receivedAmount;
        this.discountedAmount = discountedAmount;
        this.selledProductsList = selledProductsList;
        this.buyerName = buyerName;
    }

    public void setShippingStatus(boolean shippingStatus){
        this.shippingStatus = shippingStatus ;
    }

    public BigInteger getReceivedAmount() {
        return receivedAmount;
    }

    public BigInteger getDiscountedAmount() {
        return discountedAmount;
    }

    public ArrayList<Product> getSelledProductsList() {
        return selledProductsList;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public boolean isShippingStatus() {
        return shippingStatus;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "logId='" + super.getLogId() + '\'' +
                ", logDate=" + super.getLogDate() +
                "receivedAmount=" + receivedAmount +
                ", discountedAmount=" + discountedAmount +
                ", selledProductsList=" + selledProductsList +
                ", buyerName='" + buyerName + '\'' +
                ", shippingStatus=" + shippingStatus +
                '}';
    }
}
