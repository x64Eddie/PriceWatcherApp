package priceWatcherModel;

import java.net.URL;
import java.util.Currency;
import java.util.Date;
import java.util.Random;

import org.apache.commons.math3.util.Decimal64;

/**
 * Represents the Item that the user is going to add to the UI
 */
public class Item {
    
    private String name;
    private URL url;
    private double currentPrice = 1200.00, startPrice = 1200.00;
    private Date date = new Date();
    private Random rand = new Random(3000);

    public Item(String name, URL url){
        this.name = name;
        this.url = url;
        this.currentPrice = rand.nextInt(1000);
        
    }
    Item(URL url){
        this.url = url;
    }

    //This is going to simulate the ITEM getting it from an external resource
    public Item(String name, URL url, double currentPrice, double startingPrice){
        this.name = name;
        this.url = url;
        this.currentPrice = currentPrice;
        this.startPrice = startingPrice;
    }

    public void update(){
        this.currentPrice =  Math.random() * 3000 + 100;
    }

    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }
    /**
     * @return the currentPrice
     */
    public double getCurrentPrice() {
        return currentPrice;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the startPrice
     */
    public double getStartPrice() {
        return startPrice;
    }
    /**
     * @return the date
     */
    public int getDate() {
        return date.getDate();
    }

    public double getPriceChage(){
        double changed = startPrice - currentPrice;
        double div = ((startPrice + currentPrice)/2);
        return Math.abs((changed/div)*100);
    }

    public boolean isNegative(){
        return !((startPrice - currentPrice)< 0);
    }
}