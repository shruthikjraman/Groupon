/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author ramans4447
 */
public class Buyer extends User {

    private HashSet<Order> orderHistory;

    public Buyer(String id, String pwd) {
        super(id, pwd);
        orderHistory = new HashSet<Order>();
    }

    public static void MainPage() {
        Scanner input = new Scanner(System.in);
        String selection = "";
        System.out.println("Welcome to Buyer Portal!");

        while (!selection.equals("x")) {
            //Display menu
            System.out.println();
            System.out.println("Buyer Login");
            System.out.println("1: Sign up as a buyer");
            System.out.println("2: Login to your buyer account");
            System.out.println("x: Leave the buyer portal");
            System.out.println();

            selection = input.next();

            if (selection.equals("1")) {
                register();
            } else if (selection.equals("2")) {
                login();
            } else {
                ;
            }
        }
    }

    public static void register() {
        Scanner input = new Scanner(System.in);
        String loginID, password;
        System.out.println("Login ID: ");
        loginID = input.next();
        System.out.println("Password: ");
        password = input.next();

        boolean idUsed = false;
        for (Buyer b : Groupon.allBuyerAccounts) {
            if (b.getLoginID().equals(loginID)) {
                idUsed = true;
                break;
            }
        }

        if (idUsed == true) {
            System.out.println("***The buyer login ID is not available!***");
        } else {
            Buyer newBuyer = new Buyer(loginID, password);
            Groupon.allBuyerAccounts.add(newBuyer);
            System.out.println("***The registration is successful!***");
        }
    }

    public static void login() {
        Scanner input = new Scanner(System.in);
        String id, password;
        boolean idFound = false;
        System.out.println("Please enter your login ID: ");
        id = input.next();
        System.out.println("Please enter your password: ");
        password = input.next();

        for (Buyer b : Groupon.allBuyerAccounts) {
            if (id.equals(b.getLoginID())) {
                idFound = true;
                if (password.equals(b.getPassword())) {
                    b.welcome(id);
                    break;
                } else {
                    System.out.println("***Incorrect password!***");
                }
            }
        }

        if (!idFound) {
            System.out.println("***The login ID is not found in the system!***");
        }
    }

    public void welcome(String id) {

        System.out.println("Welcome " + id + ",");
        Scanner input = new Scanner(System.in);
        String selection = "";
        while (!selection.equals("4")) {
            System.out.println("Please select your operation: ");
            System.out.println("1: Browse items to make a purchase");
            System.out.println("2: Search items with key words");
            System.out.println("3: Check order history");
            System.out.println("4: Sign out");
            System.out.println();

            selection = input.next();

            if (isInteger(selection)) {
                int intSelection = Integer.parseInt(selection);
                switch (intSelection) {
                    case 1:
                        browseItems(id);
                        break;
                    case 2:
                        searchItems(id);
                        break;
                    case 3:
                        orderHistory(id);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid selection. Please select an option from 1-5.");
                        break;
                }
            }
        }
    }

    private boolean isInteger(String a) {
        try {
            int i = Integer.parseInt(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void browseItems(String id) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        String selection = "";
        while (!selection.equals("3")) {
            System.out.println();
            System.out.println("Browse items: ");
            System.out.println("1: Products");
            System.out.println("2: Services");
            System.out.println("3: Go back");
            System.out.println();
            selection = input.next();
            if (isInteger(selection)) {
                int intSelection = Integer.parseInt(selection);
                switch (intSelection) {
                    case 1:
                        listProducts(id, "Product");
                        break;
                    case 2:
                        listServices(id, "Service");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void listProducts(String bID, String category) {
        System.out.println();
        System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n", "Listing ID", "Category", "Title", "Description", "Price", "Available Qty", "Status", "Seller ID");

        for (Listing prod : Seller.getAllListings()) {
            if (prod.getCategory().equalsIgnoreCase(category)) {
                System.out.print(prod.getListingID() + "\t");
                System.out.print(prod.getCategory() + "\t");
                System.out.print(prod.getTitle() + "\t");
                System.out.print(prod.getDescription() + "\t");
                System.out.print(prod.getPrice() + "\t");
                System.out.print(prod.getMax_no() + "\t");
                System.out.print(prod.getStatus() + "\t");
                System.out.println(prod.getSellerID() + "\t");
                System.out.println();
            }
        }
        buyOptions(bID, category);
    }

    public void listServices(String bID, String category) {
        System.out.println();
        System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n", "Listing ID", "Category", "Title", "Description", "Price", "Available Qty", "Status", "Seller ID");

        for (Listing serv : Seller.getAllListings()) {
            if (serv.getCategory().equalsIgnoreCase(category)) {
                System.out.print(serv.getListingID() + "\t");
                System.out.print(serv.getCategory() + "\t");
                System.out.print(serv.getTitle() + "\t");
                System.out.print(serv.getDescription() + "\t");
                System.out.print(serv.getPrice() + "\t");
                System.out.print(serv.getMax_no() + "\t");
                System.out.print(serv.getStatus() + "\t");
                System.out.println(serv.getSellerID() + "\t");
                System.out.println();
            }
        }
        buyOptions(bID, category);
    }

    private void buyOptions(String bID, String category) {
        Scanner input = new Scanner(System.in);
        String selection = "";
        while (!selection.equals("2")) {
            System.out.println("Please select an operation: ");
            System.out.println("1: Purchase a " + category.toLowerCase());
            System.out.println("2: Go back");
            selection = input.next();
            if (isInteger(selection)) {
                int intSelection = Integer.parseInt(selection);
                switch (intSelection) {
                    case 1:
                        System.out.println("Enter the Listing ID of " + category.toLowerCase() + ": ");
                        String lID = input.next();
                        buyIt(bID, lID, category);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid selection");
                        break;
                }
            }
        }
    }

    public void searchItems(String bID) {
        boolean found = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the keyword to search");
        String keyword = input.next().toLowerCase();
        System.out.println();
        System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n", "Listing ID", "Category", "Title", "Description", "Price", "Available Qty", "Status", "Seller ID");
        for (Listing l : Seller.getAllListings()) {
            String title = l.getTitle().toLowerCase();
            String description = l.getDescription().toLowerCase();
            String lID = l.getListingID().toLowerCase();
            String sID = l.getSellerID().toLowerCase();
            String category = l.getCategory().toLowerCase();
            if (title.contains(keyword) || description.contains(keyword) || lID.contains(keyword) || sID.contains(keyword) || category.contains(keyword)) {
                System.out.print(l.getListingID() + "\t");
                System.out.print(l.getCategory() + "\t");
                System.out.print(l.getTitle() + "\t");
                System.out.print(l.getDescription() + "\t");
                System.out.print(l.getPrice() + "\t");
                System.out.print(l.getMax_no() + "\t");
                System.out.print(l.getStatus() + "\t");
                System.out.println(l.getSellerID() + "\t");
                System.out.println();
            }
            found = true;
        }
        if (!found) {
            System.out.println("Sorry, the item doesn't exist!");
        } else {
            String selection = "";
            while (!selection.equals("2")) {
                System.out.println("Please select an operation: ");
                System.out.println("1: Purchase an item");
                System.out.println("2: Go back");
                selection = input.next();
                if (isInteger(selection)) {
                    int intSelection = Integer.parseInt(selection);
                    switch (intSelection) {
                        case 1:
                            String lID = "";
                            System.out.println("Category: ");
                            String category = input.next();
                            if (category.equalsIgnoreCase("product") || category.equalsIgnoreCase("service")) {
                                System.out.println("Enter the Listing ID of the " + category + ": ");
                                lID = input.next();
                                buyIt(bID, lID, category);
                            } else {
                                System.out.println("Invalid category!");
                            }
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Invalid selection");
                            break;
                    }
                }
            }
        }
    }

    public void buyIt(String bID, String lID, String category) {
        int k = 0;
        boolean alreadypurchased = false;
        for (Listing item : Seller.getAllListings()) {
            if (lID.equals(item.getListingID()) && category.toLowerCase().equals(item.getCategory().toLowerCase())) {
                if (item.getMax_no() > 0) {
                    for (Order a : Groupon.allOrders) {
                        if (bID.equals(a.getBuyerID()) && lID.equals(a.getListingID())) {
                            alreadypurchased = true;
                            break;
                        }
                    }
                    if (!alreadypurchased) {
                        item.setMax_no(item.getMax_no() - 1);
                        item.setMin_no(item.getMin_no() - 1);
                        if (item.getMin_no() == 0) {
                            item.setStatus("Valid");
                            validStatus(item.getListingID());
                        }
                        Order o = new Order(bID, item.getListingID());
                        Groupon.allOrders.add(o);
                        System.out.println("Order has been placed successfully!");
                        System.out.println("Your order details are: ");
                        System.out.println("Order Number: " + o.getOrderID());
                        System.out.println("Listing ID: " + o.getListingID());
                        System.out.println(o.getOrderStatement());
                    } else {
                        System.out.println("You can purchase a " + category + " only once!");
                        k = 0;
                        break;
                    }
                } else {
                    System.out.printf("\n%s %s is not available at this time!\n", item.getTitle(), item.getCategory());
                }
                k = 0;
                break;
            } else {
                k++;
            }
        }
        if (k != 0) {
            System.out.println("Invalid Listing ID");
        }
    }

    public void validStatus(String lID) {
        for (Order o : Groupon.allOrders) {
            if (lID.equals(o.getListingID())) {
                o.setOrderStatus("Valid");
                String statement = DateTime.DateandTime() + ": Order Number " + o.getOrderID() + " is in " + o.getOrderStatus() + " status.";
                o.setOrderStatement(statement);
            }
        }
    }

    public void orderHistory(String id) {
        boolean found = false;
        System.out.println();
        System.out.printf("%s's Order History\n", id);
        System.out.println("Order ID\tListing ID\tOrder Status\n");
        for (Order o : Groupon.allOrders) {
            if (id.equals(o.getBuyerID())) {
                found = true;
                orderHistory.add(o);
            }
        }
        for (Order hist : orderHistory) {
            System.out.print(hist.getOrderID() + "\t");
            System.out.print(hist.getListingID() + "\t");
            System.out.print(hist.getOrderStatement() + "\t");
            System.out.println();
        }
        if (found == false) {
            System.out.println("You have not purchased anything!");
        }
    }
}
