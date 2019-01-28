package priceWatcherConsole;

import priceWatcherModel.Item;

public interface UI{
    //Drawing the UI that we are going to use to display the information
    void draw();

    //This method is going display the item
    void addItem(Item item);

}