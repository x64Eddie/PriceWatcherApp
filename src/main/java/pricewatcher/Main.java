package pricewatcher;

import pricewatcher.UserInterface.UI;
import pricewatcher.UserInterface.GUI.PriceWatcherJFrame;

public class Main {
    public static void main(String[] args){

        UI currentUI = new PriceWatcherJFrame();
        currentUI.configureUI();
        // currentUI.draw();
    }
}