package UserInterface;

import priceWatcherModel.Item;

import java.awt.Color;
import javax.swing.*;

public class SimpleGui extends JFrame implements UI {
    
    private static final long serialVersionUID = 1L;
    private JPanel panel;

    @Override
    public void draw() {
       // panel = new JPanel(new GridLayout(1, 4));
       // this.add(panel);
        this.setSize(400, 500);
        this.setVisible(true);
    }

    @Override
    public void addItem(Item item) {
        ItemCell itemCell = new ItemCell(item);
        itemCell.setBackground(Color.BLACK);
        itemCell.setBounds(0, 0, 300, 100);
        this.add(itemCell);
        SwingUtilities.updateComponentTreeUI(this);

    }

}