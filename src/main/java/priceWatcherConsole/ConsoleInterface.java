package priceWatcherConsole;

import priceWatcherModel.Item;

public class ConsoleInterface implements UI {

    @Override
    public void draw() {
        System.out.println("Welcome to price watcher!\n\n");
    }

    @Override
    public void addItem(Item item) {
        System.out.println("Name:\t"+item.getName()+"\nURL:\t"+item.getUrl()
        +"\nPrice:\t"+item.getCurrentPrice()+"\nChange:\t"+item.getPriceChage()+
        "\nDate:\t"+item.getDate());

    }

}