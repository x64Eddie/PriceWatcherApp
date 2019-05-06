package pricewatcher.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.annotation.Nonnull;

/*
*The Price Finder is going to define the methods that is going to use to get the price,
from the Web. We could use many ways of getting the price of an Item.
**/
public abstract class PriceFinder implements Runnable{

    protected PriceListener listener;
    private URL url;
    

    PriceFinder(URL url){
        this.url = url;
    }

    @Override
    public void run() {
        try{
            getPrice(this.url);
        }catch(IOException exception){
            listener.requestChanged(RequestState.ERROR);
        }
    }

    /**
     * Getting the Price from the URL that is specify.
     * @param url - the URL where we are going to fetch the data from.
     * @throws IOException
     */
    public void getPrice(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String encoding = con.getContentEncoding();
        if(encoding == null){
            encoding = "utf-8";
        }
        InputStreamReader reader = null;
        if("gzip".equals(encoding)){
            reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
        }else{
            reader = new InputStreamReader(con.getInputStream(), encoding);
        }
        BufferedReader in = new BufferedReader(reader);
        createItem(in);

        if(con != null){
            con.disconnect();
        }
    }

    /**
     * Going to filter the HTTP response and get an Item from it.
     * @param data - the data that was scraped from the URL
     */
    abstract void createItem(Reader data);

    /**
     * Setting the listener
     * @param listener - the Listener that is notifies the Listener
     */
    public void setListener(@Nonnull PriceListener listener){
        this.listener = listener;
    }
}