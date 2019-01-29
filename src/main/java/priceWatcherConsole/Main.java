package priceWatcherConsole;

import java.net.URL;

import priceWatcherModel.Item;



public class Main{
    public static void main(String[]args){
        
        UI currentUI = new ConsoleInterface();
        
        
        //TODO change the input of the program to another class that takes care of this
        //Scanner scan = new Scanner(System.in);
        try{
            URL url = new URL("https://www.google.com");
            Item exampleItem = new Item("IMac", url);
        }catch(Exception e){

        }
        
    }
}