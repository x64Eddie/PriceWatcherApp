package priceWatcherConsole;

import java.net.URL;
import java.util.Scanner;

import priceWatcherModel.Item;
import UserInterface.*; 



public class Main{
    public static void main(String[] args){

        UI currentUI = new SimpleGui();
        currentUI.draw();
        
        
        try{
            URL url = new URL("https://www.google.com");
            Item exampleItem = new Item("IMac", url);
            currentUI.addItem(exampleItem);
        }catch(Exception e){

        }
        System.out.println("1 to exit and 2 to re-check");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if(input.equals("1")){
            System.out.print("Bye\n");
            System.exit(0);
        }
        else{
            main(args);
        }

        
        
    }
}