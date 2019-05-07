package priceWatcherModel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.*;

public class JsonDatabase implements DataBaseInterface{


    public void addItems(List<Item> items)throws IOException{
        if(!items.isEmpty()){
            JsonArray jsonitemsArray = new JsonArray();
            for(int i = 0; i < items.size(); i++){
                Item item = items.get(i);
                JsonObject jsonItem = new JsonObject();
                jsonItem.addProperty("Name", item.getName());
                jsonItem.addProperty("URL", item.getUrl().toString());
                jsonItem.addProperty("StartingPrice", item.getStartPrice());
                jsonItem.addProperty("CurrentPrice", item.getCurrentPrice());
                jsonitemsArray.add(jsonItem);
            }

            FileWriter file = new FileWriter("/Users/ihuds/Desktop/School/Spring 2019/CS3331HW/HW5/PriceWatcherApp/Src/res/Items.txt");
            file.write(jsonitemsArray.toString());
        }
        else{
            System.out.println("No Items have been added.");
        }

    }
    
    public List<Item> fetchItems(){
        List<Item> list = new ArrayList<>();
        return list;
    }
}