package priceWatcherModel;
import java.io.IOException;
import java.util.*;

public interface DataBaseInterface{

    public void addItems(List<Item> items)throws IOException;

    public List<Item> fetchItems();
}