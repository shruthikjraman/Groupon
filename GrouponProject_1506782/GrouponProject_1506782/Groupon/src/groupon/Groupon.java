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
public class Groupon {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Buyer> allBuyerAccounts;
    public static ArrayList<Seller> allSellerAccounts;    
    public static ArrayList<Order> allOrders;

    public static void main(String[] args) {
        // TODO code application logic here
        allBuyerAccounts = new ArrayList<Buyer>();
        allSellerAccounts = new ArrayList<Seller>();
        allOrders = new ArrayList<Order>();
       
        System.out.println("Welcome to Groupon!");
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        //Display the menu
        while(!selection.equals("x")){
            //Display the menu
            System.out.println();
            System.out.println("Please make your selection: ");
            System.out.println("1: Buyer login");
            System.out.println("2: Seller login");
            System.out.println("x: Finish the simulation");
            
            selection = input.nextLine();
            System.out.println();
            if(selection.equals("1")){
                Buyer.MainPage();
            } else if(selection.equals("2")){
                Seller.MainPage();
            } else{
                System.out.println("Invalid selection!");
            }
        }
    }

}
