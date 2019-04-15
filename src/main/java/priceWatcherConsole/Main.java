package priceWatcherConsole;

import java.net.URL;

import priceWatcherModel.Item;
import UserInterface.*;
import UserInterface.GUI.PriceWatcherJFrame; 



public class Main{
    public static void main(String[] args){

        UI currentUI = new PriceWatcherJFrame();
        currentUI.configureUI();
        // currentUI.draw();
    }
}