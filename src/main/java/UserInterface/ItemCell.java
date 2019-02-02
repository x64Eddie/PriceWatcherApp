package UserInterface;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import priceWatcherModel.Item;

/**
 * This cells is going to represent the UI of the Item
 */
public class ItemCell extends JComponent{

    private static final long serialVersionUID = 1L;
    private JLabel itemName, itemPrice, itemChanged, itemDate;
    private JButton itemURL;
    
    public ItemCell(Item item){
        itemName = new JLabel();
        itemName.setText(item.getName());
        itemPrice = new JLabel();
        itemPrice.setText(String.valueOf(item.getCurrentPrice()));
        itemChanged = new JLabel();
        itemChanged.setText(String.valueOf(item.getPriceChage()));
        itemURL = new JButton();
        itemURL.setText((itemURL).toString());
        itemDate = new JLabel();
        itemDate.setText(String.valueOf(itemDate));
    }
}