package pricewatcher.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Formatting the Price of the Bestbuy page.
 */
public class BestBuyPriceFinder extends PriceFinder {

    public BestBuyPriceFinder(URL url) {
        super(url);
    }

    @Override
    void createItem(Reader data) {
        try {
            String line;
            while ((line = ((BufferedReader) data).readLine()) != null) {
                if(line.contains("data-customer-price")){
                    Pattern p =  Pattern.compile("price=\"\\$?\\d+\\.\\d{2}");
                    Matcher m = p.matcher(line);
                    if(m.find()){
                        double price = Double.parseDouble(m.group().replace("[^\\d.]", ""));
                        super.listener.newPrice(price);
                    }
                }
            } super.listener.newPrice(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}