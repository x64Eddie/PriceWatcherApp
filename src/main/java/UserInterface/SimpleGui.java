package UserInterface;

import priceWatcherModel.Item;



import javafx.application.Application;
import javafx.stage.Stage;

public class SimpleGui extends Application implements UI {


    @Override
    public void draw() {
        launch();
    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Price Watcher");
        primaryStage.show();
    }
   

}