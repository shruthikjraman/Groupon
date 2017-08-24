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
public class Listing {
    private static int nextListingID = 00001;
    private String category;
    private String listingID;
    private String title;
    private String description;
    private double price;
    private int min_no;
    private int minimum;
    private int max_no;
    private int maximum;
    private String status;
    private String sellerID;  
    
    
    public Listing(String c, String t, String d, double p, int min, int max, String sID){
        listingID = "" + nextListingID;
        nextListingID++;
        category = c;
        title = t;
        description = d;
        price = p;
        min_no = min;
        minimum = min;
        max_no = max;
        maximum = max;
        sellerID = sID;
        if(min_no==0){
            status = "Valid";
        } else{
            status = "Pending";
        }
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static int getNextListingID() {
        return nextListingID;
    }

    public static void setNextListingID(int nextListingID) {
        Listing.nextListingID = nextListingID;
    }

    public String getListingID() {
        return listingID;
    }

    public void setListingID(String listingID) {
        this.listingID = listingID;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMin_no() {
        return min_no;
    }

    public void setMin_no(int min_no) {
        this.min_no = min_no;
    }

    public int getMax_no() {
        return max_no;
    }

    public void setMax_no(int max_no) {
        this.max_no = max_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }
    
    
}
