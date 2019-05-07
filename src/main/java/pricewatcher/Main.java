package pricewatcher;

import java.net.MalformedURLException;
import java.net.URL;


import pricewatcher.UserInterface.UI;
import pricewatcher.UserInterface.GUI.PriceWatcherJFrame;
import pricewatcher.networking.EbayPriceFinder;
import pricewatcher.networking.PriceFinder;
import pricewatcher.networking.PriceListener;
import pricewatcher.networking.RequestState;

public class Main {
    public static void main(String[] args) {

         UI currentUI = new PriceWatcherJFrame();
         currentUI.configureUI();

        URL url = null;
        try {
            url = new URL(
                    "https://www.ebay.com/itm/Alien-7-Scale-Action-Figures-Ultimate-1986-Blue-Alien-Warrior-NECA/273527522662?epid=8025428143&hash=item3faf82c566:g:q-YAAOSwatBb01Uy");
        } catch (MalformedURLException e) {
            new AssertionError(e);
        }
        PriceFinder finder = new EbayPriceFinder(url);
        finder.setListener(new PriceListener() {

            @Override
            public void requestChanged(RequestState state) {
                
            }

            @Override
            public void newPrice(double price) {
                
            }

        });
        new Thread(finder).start();
        
    }
}