package pricewatcher.priceWatcherModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import io.reactivex.annotations.NonNull;

class JsonFileWriter implements DataWriter {

    private DataWriterListener listener;
    private Gson gson;
    private String filePath;

    JsonFileWriter(@NonNull String path) {
        this.filePath = path;
        gson = new Gson();
    }

    @Override
    public void read() {
        FileReader reader;
        try{
            reader = new FileReader(filePath);
            JsonArray jsonArray = gson.fromJson(reader, Item.class);
        }catch(){

        }
    }

    @Override
    public void write(Item... items) {
        FileWriter writer;
        try {
            writer = new FileWriter(filePath);
            gson.toJson(items, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void setListener(DataWriterListener listener) {
        this.listener = listener;
    }

}