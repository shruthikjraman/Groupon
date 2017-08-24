/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupon;

/**
 *
 * @author ramans4447
 */
public class Order {

    private static int nextOrderID = 10000;

    private String orderID;
    private String orderStatus;
    private String buyerID;
    private String listingID;
    private String orderStatement;

    public Order(String b, String l) {
        buyerID = b;
        orderID = "" + nextOrderID;
        nextOrderID++;        
        listingID = l;
        for (Listing li : Seller.getAllListings()) {
            if (li.getListingID().equalsIgnoreCase(l)) {
                orderStatus = li.getStatus();
            }
        }
        orderStatement = DateTime.DateandTime() + ": Order Number " + orderID + " is in " + orderStatus + " status.";
    }

    public static int getNextOrderID() {
        return nextOrderID;
    }

    public static void setNextOrderID(int nextOrderID) {
        Order.nextOrderID = nextOrderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getListingID() {
        return listingID;
    }

    public void setListingID(String listingID) {
        this.listingID = listingID;
    }

    public String getOrderStatement() {
        return orderStatement;
    }

    public void setOrderStatement(String orderStatement) {
        this.orderStatement = orderStatement;
    }

}
