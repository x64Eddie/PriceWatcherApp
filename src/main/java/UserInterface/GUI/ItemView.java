package UserInterface.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import priceWatcherModel.Item;
import priceWatcherModel.*;

/**
 * Represents the Panel where the Items are going to be displayed.
 */
public class ItemView extends JPanel{

    private static final long serialVersionUID = 1L;
    private JScrollPane scrollPanel;
    private DefaultListModel<Item> listModel;
    private JList<Item> jList;
    
    public ItemView(ItemManager itemManager) {

        setPreferredSize(new Dimension(100, 160));
        setBackground(Color.WHITE);

        scrollPanel = new JScrollPane();
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPanel);

        listModel = new DefaultListModel<>();
        for(Item item: itemManager.getItems())listModel.addElement(item);
        jList = new JList<>(listModel);
        jList.setCellRenderer(new ItemRenderer());

        scrollPanel.add(jList);
    }
        
    
    /** Overridden here to display the details of the item. */
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 

    }
}