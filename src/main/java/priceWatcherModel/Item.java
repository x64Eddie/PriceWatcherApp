import java.net.URL;
import java.util.Date;

class Item {
    
    private String name;
    private URL url;
    private double currentPrice, startPrice;
    private Date date;

    Item(String name, URL url){
        this.name = name;
        this.url = url;
    }
    Item(URL url){
        this.url = url;
    }

    //This is going to simulate the ITEM getting it from an external resource
    Item(String name, URL url, String currentPrice, String startingPrice){
        this.name = name;
        this.url = url;
        this.currentPrice = currentPrice;
        this.startPrice = startingPrice;
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
    public Date getDate() {
        return date;
    }

    public double getPriceChage(){
        return Math.abs(this.currentPrice - this.startPrice);
    }
}