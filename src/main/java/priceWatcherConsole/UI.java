package priceWatcherConsole;

import priceWatcherModel.Item;

/**
 * This is going to define what the UI is going to display the information
 * of the items.
 */
public interface UI{
    //Drawing the UI that we are going to use to display the information
    public void draw();

    //This method is going display the item
    public void addItem(Item item);

}