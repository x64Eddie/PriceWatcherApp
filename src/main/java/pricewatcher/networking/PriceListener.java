package pricewatcher.networking;

public interface PriceListener{
    /**
     * Gives the listener the state of the price listener.
     * @param state - the state of the request of the price finder.
     */
    void requestChanged(RequestState state);

    /**
     * Its going to return when the network is ready the price of the item
     * @param price
     */
    void newPrice(double price);
}