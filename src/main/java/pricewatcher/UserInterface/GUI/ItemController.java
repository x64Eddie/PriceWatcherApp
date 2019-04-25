package pricewatcher.UserInterface.GUI;

import pricewatcher.priceWatcherModel.Item;
import pricewatcher.priceWatcherModel.ItemModel;

/**
 * Represetsn the Controller that is going to get the Items from the Item Model
 * and display them in the Item Panel.
 */
class ItemController{

    private ItemModel itemModel;
    private ItemPanel itemPanel;

    ItemController(ItemModel itemModel, ItemPanel itemPanel){
        this.itemModel = itemModel;
        this.itemPanel = itemPanel;

        for (Item item : itemModel.getItems())
            itemPanel.addItem(item);
    }

    /**
     * Going to add the item into the ItemPanel
     */
    public void addItem(Item item){
        itemModel.addItems(item);
        itemPanel.addItem(item);
    }
    
}