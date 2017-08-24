/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ramans4447
 */
public class Seller extends User {

    private static ArrayList<Listing> allListings;

    public Seller(String id, String pwd) {
        super(id, pwd);
        allListings = new ArrayList<Listing>();
    }

    public static void MainPage() {
        Scanner input = new Scanner(System.in);
        String selection = "";
        System.out.println("Welcome to Seller Portal!");

        while (!selection.equals("x")) {
            //Display menu
            System.out.println();
            System.out.println("Seller Login");
            System.out.println("1: Sign up as a seller");
            System.out.println("2: Login to your seller account");
            System.out.println("x: Leave the seller portal");
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
        for (Seller s : Groupon.allSellerAccounts) {
            if (s.getLoginID().equals(loginID)) {
                idUsed = true;
                break;
            }
        }

        if (idUsed == true) {
            System.out.println("***The seller login ID is not available!***");
        } else {
            Seller newSeller = new Seller(loginID, password);
            Groupon.allSellerAccounts.add(newSeller);
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

        for (Seller s : Groupon.allSellerAccounts) {
            if (id.equals(s.getLoginID())) {
                idFound = true;
                if (password.equals(s.getPassword())) {
                    s.welcome(id);
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
        while (!selection.equals("3")) {
            System.out.println("Please select your operation: ");
            System.out.println("1: List products or services");
            System.out.println("2: View listed items");
            System.out.println("3: Sign out");
            System.out.println();

            selection = input.next();

            if (isInteger(selection)) {
                int intSelection = Integer.parseInt(selection);
                switch (intSelection) {
                    case 1:
                        createListing(id);
                        break;
                    case 2:
                        viewListing(id);
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid selection. Please select an option from 1-3.");
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

    public void createListing(String id) {
        String c = "";
        System.out.println("Create Listing");
        Scanner input = new Scanner(System.in);
        do {
            double priceDouble = 0.0;
            int min_no = 0, max_no = 0;
            System.out.println("Enter the category of item (Product/Service): ");
            String category = input.next();
            if (isProdOrServ(category)) {
                System.out.println("Title: ");
                String title = input.next();
                System.out.println("Description: ");
                String description = input.next();
                System.out.println("Price in USD: ");
                String price = input.next();
                System.out.println("Minimum number of sales for the item to turn alive: ");
                String min = input.next();
                min_no = Integer.parseInt(min);
                System.out.println("Available number of items for sale: ");
                String max = input.next();
                max_no = Integer.parseInt(max);
                priceDouble = Double.parseDouble(price);
                Listing l = new Listing(category, title, description, priceDouble, min_no, max_no, id);
                allListings.add(l);
                System.out.println();
                System.out.printf("%s added successfully!\n", category.toUpperCase());
            } else {
                System.out.println("Invalid entry");
            }
            System.out.println("Would you like to list another item? [Y/N]");
            c = input.next();
        } while (c.equalsIgnoreCase("y"));
    }

    private boolean isProdOrServ(String a) {
        if (a.equalsIgnoreCase("Product") || a.equalsIgnoreCase("Service")) {
            return true;
        } else {
            return false;
        }
    }

    public void viewListing(String id) {
        System.out.println();
        System.out.printf("%s's listing", id);
        System.out.println();
        System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\t%10s\n", "Listing ID", "Category", "Title", "Description", "Price", "Min No To Turn Alive", "Maximum Qty Listed", "Status", "Qty Sold");
        for (Listing l : allListings) {
            if (id.equals(l.getSellerID())) {
                //System.out.printf("%20s\t%10s\t%10s\t%10s\t%10.2f\t%10d\t%10d\n" + l.getListingID() + l.getCategory() + l.getTitle() + l.getDescription() + l.getPrice() + l.getMin_no() + l.getMax_no());
                System.out.print(l.getListingID() + "\t");
                System.out.print(l.getCategory() + "\t");
                System.out.print(l.getTitle() + "\t");
                System.out.print(l.getDescription() + "\t");
                System.out.print(l.getPrice() + "\t");
                System.out.print(l.getMinimum() + "\t");
                System.out.print(l.getMaximum() + "\t");
                System.out.print(l.getStatus() + "\t");
                //How many sold currently
                System.out.println((l.getMaximum() - l.getMax_no()) + "\t");
                System.out.println();
            }
        }

        System.out.println("Please select an operation: ");
        System.out.println("1: View related orders for an item");
        System.out.println("2: Go Back");
        Scanner input = new Scanner(System.in);
        String selection = input.next();
        if (isInteger(selection)) {
            int intSelection = Integer.parseInt(selection);
            switch (intSelection) {
                case 1:
                    relatedOrders();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid entry");
                    break;
            }
        }

    }

    public void relatedOrders() {
        Scanner input = new Scanner(System.in);
        String c = "";
        do {
            boolean found = false, noOrders = true;
            System.out.println("Enter Listing ID of the item: ");
            String lID = input.next();
            for (Listing l : allListings) {
                if (lID.equals(l.getListingID())) {
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Invalid Listing ID");
            } else {
                System.out.println("Orders related to Listing ID: " + lID);
                System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\n", "Listing ID", "Order ID", "Buyer ID", "Order Status", "Order Statement");
                for (Order o : Groupon.allOrders) {
                    if (lID.equals(o.getListingID())) {
                        noOrders = false;
                        System.out.print(o.getListingID() + "\t");
                        System.out.print(o.getOrderID() + "\t");
                        System.out.print(o.getBuyerID() + "\t");
                        System.out.print(o.getOrderStatus() + "\t");
                        System.out.print(o.getOrderStatement() + "\t");
                        System.out.println();
                    }
                }
                if (noOrders) {
                    System.out.println();
                    System.out.println("No orders have been placed yet for this item.");
                }
            }
            System.out.println("Would you like to view related orders for another item? [Y/N]");
            c = input.next();
        } while (c.equalsIgnoreCase("y"));
    }

    public static ArrayList<Listing> getAllListings() {
        return allListings;
    }

    public void setAllListings(ArrayList<Listing> allListings) {
        this.allListings = allListings;
    }

}
