package pricewatcher.priceWatcherModel;

import java.util.List;

interface DataWriter {
    void read();
}
interface DataWriterListener{
    void doneReading(List<Item> items);
    void doneWriting();
}