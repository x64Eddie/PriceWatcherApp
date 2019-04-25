package pricewatcher.UserInterface;

import pricewatcher.priceWatcherModel.Item;

/**
 * This is going to define what the UI is going to display the information
 * of the items.
 */
public interface UI{

    /**
     * Going to be call once the UI is initialize for the purpose of initializing any
     * variables that need to be intialize before draw and addItems begin to be call.
     */
    public void configureUI();

    //Drawing the UI that we are going to use to display the information
    public void draw();

    //This method is going display the item
    public void addItem(Item item);

}