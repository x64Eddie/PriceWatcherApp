package pricewatcher.priceWatcherModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Takes care of all the adding and removing of the items
 */
public class ItemModel {
    private List<Item> items;

public ItemModel(){
    this.items = new ArrayList<>();
}

/**
 * Adds the items to the manager.
 * @param items - the Items that are going to be added.
 */
public void addItems(Item... items){
    this.items.addAll(Arrays.asList(items));
}

/**
 * Gets the items that are from a specific store, for example, Ebay or BestBuy.
 * we are going to get the store by looking at the URL then extract from what store
 * the item belongs to.
 * @param store - the store we are looking the items in.
 * @return items - The items that are of the Store
 */
public List<Item> getItems(String store){
    Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
    List<Item> matches = new ArrayList<Item>();
    for (Item item : items) {
        Matcher matcher = pattern.matcher(item.getUrl().toString());
        if (matcher.group(2) == store)matches.add(item);
    }
    return matches;//todo getting the items from stores.
}

/**
 * get the Items in the model
 * @return - the Items in the model.
 */
public List<Item> getItems (){
    return this.items;
}

/**
 * Updates the price of the items
 */
public void update(){
    
    for (Item item : this.items)item.update();
}

/**
 * Removes the item from the Item Manager
 * @param name - the name of the item that is going to be removed.
 */
public void removeItem(String name){

}

public void sortItemsByNameAcending(){

}
public void sortItemsByNameDecending(){

}

public void sortItemsByDateNewest(){

}

public void sortItemsByDateOldest(){

}

public void sortItemsByPriceChange(){

}

public void sortItemsByPriceLow(){

}

public void sortItemsByPriceHigh(){

}

    
}