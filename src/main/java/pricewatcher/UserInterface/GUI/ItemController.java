package pricewatcher.UserInterface.GUI;

import pricewatcher.networking.PriceFinder;
import pricewatcher.networking.PriceListener;
import pricewatcher.networking.RequestState;
import pricewatcher.priceWatcherModel.Item;
import pricewatcher.priceWatcherModel.ItemModel;

/**
 * Represetsn the Controller that is going to get the Items from the Item Model
 * and display them in the Item Panel.
 */
class ItemController {

    private ItemModel itemModel;
    private ItemPanel itemPanel;
    private PriceFinder priceFinder;


    ItemController(ItemModel itemModel, ItemPanel itemPanel) {
        this.itemModel = itemModel;
        this.itemPanel = itemPanel;

        for (Item item : itemModel.getItems())
            itemPanel.addItem(item);
    }

    /**
     * Going to add the item into the ItemPanel
     */
    public void addItem(Item item) {
        // we are going to set the price of the item here from the web
        if (item.getUrl() != null) {
            // getting the corresponding priceFinder
            setPrice(item);
        }
    }

    public void updateSelectedItem() {
        setPrice(itemPanel.getCurrentItem(),
         itemPanel.getSelectedIndex());
    }

    /**
     * Update all the price for the Items
     */
    public void updateAllItems(){
        for(int i = 0; i < itemPanel.itemSize(); i ++)
            setPrice(itemPanel.getItemAt(i), i);
    }

    public void updateItem(Item item){
        setPrice(item);
    }

    public void removeSelecItem(){
        itemPanel.removeSelecItem();
    }

    public Item getCurrentItem(){
        return itemPanel.getCurrentItem();
    }

    /**
     * Setting the price of the new Item
     * @param item - the Item we are checking the price for
     */
    private void setPrice(Item item){
        setPrice(item, -1);  
    }

    /**
     * Setting the price of a specifc Item in the list
     * @param item - the Item we are checking the price for
     * @param index - the index where the Item is located.
     */
    private void setPrice(Item item, int index){
        if(item == null)return;
        priceFinder = PriceFinder.createFinder(item.getUrl());
        if(priceFinder != null){
            priceFinder.setListener(new PriceListener(){

                @Override
                public void requestChanged(RequestState state) {
                    switch(state){
                        case ERROR:
                        case DONE:
                        case POP_ITEM:
                        case IN_REQUEST:
                        case IN_REQUEST_DONE:
                    }
                }
    
                @Override
                public void newPrice(double price) {
                    item.setPrice(price);
                    if(index <= 0){
                        //we are going to add the new Item
                        itemModel.addItems(item);
                        itemPanel.addItem(item);
                    }else{
                        //we are going to update the new Item at the position
                        itemPanel.updateItem(item, index);
                        itemModel.updateItem(item, index);
                    }
                }
    
            });
            new Thread(priceFinder).start();
        }
        
    }



}