package UserInterface.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import priceWatcherModel.Item;
import priceWatcherModel.*;

/**
 * Represents the Panel where the Items are going to be displayed.
 */
public class ItemPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JScrollPane scrollPanel;
    private DefaultListModel<Item> listModel;
    private JList<Item> jList;
    private ItemManager itemManager;

    public ItemPanel() {
        this(new ItemManager());
    }

    public ItemPanel(ItemManager itemManager) {
        super(new BorderLayout());
        setBackground(Color.WHITE);
        this.itemManager = itemManager;

        try {
            itemManager.addItems(new Item("Sony Tv", new URL(
                    "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                    300, 200),
                    new Item("Sony Tv", new URL(
                    "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                    300, 200)
                    ,
                    new Item("Sony Tv", new URL(
                    "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                    300, 200)
                    ,
                    new Item("Sony Tv", new URL(
                    "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                    300, 200)
                    ,
                    new Item("Sony Tv", new URL(
                    "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                    300, 200));
            listModel = new DefaultListModel<>();
            for (Item item : itemManager.getItems())
                listModel.addElement(item);
            jList = new JList<>(listModel);
            jList.setCellRenderer(new ItemRenderer());
            
            scrollPanel = new JScrollPane(jList);
            add(scrollPanel, BorderLayout.CENTER);
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}