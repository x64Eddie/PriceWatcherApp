package priceWatcherConsole;

import java.net.URL;

import priceWatcherModel.Item;
import UserInterface.*; 



public class Main{
    public static void main(String[] args){

        UI currentUI = new SimpleGui();
        currentUI.draw();
        
        
        try{
            URL url = new URL("https://www.google.com");
            Item exampleItem = new Item("IMac", url);
            //currentUI.addItem(exampleItem);
        }catch(Exception e){

        }

        
        
    }
}