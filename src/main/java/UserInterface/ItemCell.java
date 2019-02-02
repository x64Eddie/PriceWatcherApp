package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
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
        super();
        setForeground(Color.BLACK);
        setVisible(true);
        // itemName = new JLabel();
        // itemName.setText(item.getName());
        // itemPrice = new JLabel();
        // itemPrice.setText(String.valueOf(item.getCurrentPrice()));
        // itemChanged = new JLabel();
        // itemChanged.setText(String.valueOf(item.getPriceChage()));
        // itemURL = new JButton();
        // itemURL.setText((itemURL).toString());
        // itemDate = new JLabel();
        // itemDate.setText(String.valueOf(itemDate));
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
      graphics.setColor(Color.red);
     // graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

}