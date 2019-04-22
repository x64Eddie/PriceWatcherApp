package UserInterface.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import UserInterface.UI;
import priceWatcherModel.Item;
import priceWatcherModel.ItemModel;
import UserInterface.Utils.*;

public class PriceWatcherJFrame extends JFrame implements UI {

    private static final long serialVersionUID = 1L;

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(500, 700);

    /** Special panel to display the watched item. */
    private ItemPanel itemView;

    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    private ItemController itemController;
    ItemModel itemModel;
    ItemPanel itemPanel;
    ItemController controller;

    /** Create a new dialog. */
    public PriceWatcherJFrame() {
        this(DEFAULT_SIZE);
    }

    /** Create a new dialog of the given screen dimension. */
    public PriceWatcherJFrame(Dimension dim) {
        super("Price Watcher");
        setSize(dim);

        // setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        showMessage("Welcome!");

        itemModel = new ItemModel();
        itemPanel = new ItemPanel();
        controller = new ItemController(itemModel, itemPanel);
    }

    /**
     * Callback to be invoked when the refresh button is clicked. Find the current
     * price of the watched item and display it along with a percentage price
     * change.
     */
    private void refreshButtonClicked(ActionEvent event) {
        itemView.repaint();
        showMessage("Refresh clicked!");
    }

    private void createItemButtonClicked(ActionEvent event) {

    }

    /**
     * Callback to be invoked when the view-page icon is clicked. Launch a (default)
     * web browser by supplying the URL of the item.
     */
    // private void viewPageClicked() {
    // try {
    // java.awt.Desktop.getDesktop()
    // .browse(java.net.URI.create(itemView.getItem().getUrl().toString()));
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // showMessage("View clicked!");
    // }

    /** Show briefly the given string in the message bar. */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
            try {
                Thread.sleep(3 * 1000); // 3 seconds
            } catch (InterruptedException e) {
            }
            if (msg.equals(msgBar.getText())) {
                SwingUtilities.invokeLater(() -> msgBar.setText(" "));
            }
        }).start();
    }

    @Override
    public void configureUI() {
        setLayout(new BorderLayout());
        JPanel control = createControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10, 16, 0, 16));
        add(control, BorderLayout.NORTH);

        try {
            itemModel.addItems(new Item("Sony Tv", new URL(
                    "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828"),
                    300, 200));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 0, 16));
        add(itemPanel);

        msgBar.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 0));
        add(msgBar, BorderLayout.SOUTH);
    }

    @Override
    public void draw() {

    }

    @Override
    public void addItem(Item item) {

    }

    /**
     * Creating the control panel that holds all the buttons to interact with the
     * app.s
     * 
     * @return - the control panel
     */
    JPanel createControlPanel() {
        return new ControlPanel(new ControlPanelListener() {
            @Override
            public void buttonPressed(String buttonPressed, JButton sourceBtn) {
                switch (buttonPressed) {

                case "UpdateAll":

                    break;
                case "AddItem":
                    JDialog addDialog = new JDialog();
                    addDialog.setResizable(false);
                    addDialog.setSize(new Dimension(200, 200));
                    addDialog.setTitle("Add Item");
                    GridLayout layout = new GridLayout(4, 2);
                    addDialog.setLayout(layout);

                    String labels[] = { "Name: ", "URL: ", "Price: " };
                    JTextField textFields[] = {new JTextField(10),new JTextField(10),new JTextField(10)};
                    for (int i = 0; i < labels.length; i++) {
                        JLabel label = new JLabel(labels[i]);
                        addDialog.add(label);
                        addDialog.add(textFields[i]);
                    }
                    JButton accept = new JButton("Accept"), cancel = new JButton("Cancel");
                    addDialog.add(cancel);
                    addDialog.add(accept);
                    cancel.addActionListener((e)->addDialog.dispose());
                    accept.addActionListener((e)->{
                        try {
                            Item item = new Item(textFields[0].getText(), new URL(textFields[1].getText()));
                            controller.addItem(item);
                            addDialog.dispose();
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        }
                    });
                    addDialog.setVisible(true);
                    ;
                    break;
                case "LastItem":
                    itemView.repaint();
                    showMessage("Refresh clicked!");
                    break;
                case "Search":
                case "FirstItem":
                case "UpdateItem":
                case "WebView":
                case "Edit":
                case "Remove":
                    itemPanel.removeSelecItem();
                    break;
                case "Info":

                }
            }
        }, Arrays.asList("UpdateAll", "AddItem", "LastItem", "Search", "FirstItem", "UpdateItem", "WebView", "Edit",
                "Remove", "Info"));
    }

}