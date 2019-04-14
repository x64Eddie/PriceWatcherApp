package UserInterface.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.NonNull;
import priceWatcherModel.Item;

/**
 * Utils that other classes may need.
 */
public class Utils {

    /**
     * Converts regular Items into the view representation of the Item
     * @param items - regular items that hold the information
     * @return - the ItemComponents that are ready to be displayed.
     */
    public static List<ItemComponent> convertToItemComp(@NonNull Item... items){
        List<ItemComponent> components = new ArrayList(items.length);
        for (Item item : Arrays.asList(items)) {
            ItemComponent component = new ItemComponent();
            component.setItem(item);
            components.add(component);
        }
        return components;
    }
}