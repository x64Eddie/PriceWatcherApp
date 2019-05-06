package UserInterface.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

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
        this.itemManager = itemManager;

        try {
            itemManager.addItems(new Item("MSP430", new URL(
                    "https://www.ebay.com/itm/Texas-Instruments-MSP-EXP430G2-MSP430-Value-Line-LaunchPad-Development-Tool/202616780182?_trkparms=aid%3D222007%26algo%3DSIM.MBE%26ao%3D2%26asc%3D20160323102634%26meid%3D6fcd86cbce8e461a95499abce6a5a109%26pid%3D100623%26rk%3D5%26rkt%3D6%26sd%3D171074970306%26itm%3D202616780182&_trksid=p2047675.c100623.m-1"),
                    300, 200),
                    new Item("Sony Tv", new URL(
                            "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                            300, 200),
                    new Item("Sony Tv", new URL(
                            "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                            300, 200),
                    new Item("Sony Tv", new URL(
                            "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                            300, 200),
                    new Item("Sony Tv", new URL(
                            "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                            300, 200));
            listModel = new DefaultListModel<>();
            for (Item item : itemManager.getItems())
                listModel.addElement(item);
            jList = new JList<>(listModel);
            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jList.setCellRenderer(new ItemRenderer());

            scrollPanel = new JScrollPane(jList);
            add(scrollPanel, BorderLayout.CENTER);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}