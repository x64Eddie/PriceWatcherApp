package pricewatcher.UserInterface.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import pricewatcher.UserInterface.UI;
import pricewatcher.networking.PriceFinder;
import pricewatcher.networking.PriceListener;
import pricewatcher.networking.RequestState;
import pricewatcher.priceWatcherModel.Item;
import pricewatcher.priceWatcherModel.ItemModel;

/**
 * JFrame that holds the PriceWatcher application
 */
public class PriceWatcherJFrame extends JFrame implements UI {

    private static final long serialVersionUID = 1L;

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(500, 700);

    /** Special panel to display the watched item. */
    private ItemPanel itemView;

    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    private ItemModel itemModel;
    private ItemPanel itemPanel;
    private ItemController controller;

    /** Create a new dialog. */
    public PriceWatcherJFrame() {
        this(DEFAULT_SIZE);
    }

    /**
     * Creates the JFrame for the PriceWatcher
     * @param dim - the dimension of the JFrame
     */
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
        try {
            // Example Item.
            Item item = new Item("Sony Tv", new URL(
                    "https://www.ebay.com/itm/Alien-7-Scale-Action-Figures-Ultimate-1986-Blue-Alien-Warrior-NECA/273527522662?_trkparms=aid%3D111001%26algo%3DREC.SEED%26ao%3D1%26asc%3D20180816085401%26meid%3Deb7ec4f5c4c344e5be278bf34472dd30%26pid%3D100970%26rk%3D1%26rkt%3D2%26mehot%3Dpp%26sd%3D273527522662%26itm%3D273527522662&_trksid=p2481888.c100970.m5481&_trkparms=pageci%3Af359db71-7069-11e9-80fa-74dbd18022fe%7Cparentrq%3A8ff89f5516a0ab670995e933ffe880ac%7Ciid%3A1"),
                    300, 200);
            PriceFinder priceFinder = PriceFinder.createFinder(item.getUrl());
            priceFinder.setListener(new PriceListener() {

                @Override
                public void requestChanged(RequestState state) {
                    switch(state){
                        case ERROR:
                        case DONE:
                        case POP_ITEM:
                        case IN_REQUEST:
                        case IN_REQUEST_DONE:
                    }
                }

                @Override
                public void newPrice(double price) {
                    item.setPrice(price);
                    itemModel.addItems(item);
                    itemPanel.addItem(item);
                }

            });
            priceFinder.run();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

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
                    itemPanel.updateAllItems();
                    break;
                case "AddItem":
                    JDialog addItemDialog = new JDialog();
                    addItemDialog.setResizable(false);
                    addItemDialog.setSize(new Dimension(200, 200));
                    addItemDialog.setTitle("Add Item");
                    GridLayout layout = new GridLayout(4, 2);
                    addItemDialog.setLayout(layout);

                    String labels[] = { "Name: ", "URL: ", "Price: " };
                    JTextField textFields[] = { new JTextField(10), new JTextField(10), new JTextField(10) };
                    for (int i = 0; i < labels.length; i++) {
                        JLabel label = new JLabel(labels[i]);
                        addItemDialog.add(label);
                        addItemDialog.add(textFields[i]);
                    }
                    JButton accept = new JButton("Accept"), cancel = new JButton("Cancel");
                    addItemDialog.add(cancel);
                    addItemDialog.add(accept);
                    cancel.addActionListener((e) -> addItemDialog.dispose());
                    accept.addActionListener((e) -> {
                        try {
                            Item item = new Item(textFields[0].getText(), new URL(textFields[1].getText()));
                            itemPanel.addItem(item);
                            addItemDialog.dispose();
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        }
                    });
                    addItemDialog.setVisible(true);
                    break;
                case "LastItem":
                    itemView.repaint();
                    showMessage("Refresh clicked!");
                    break;
                case "Search":
                case "FirstItem":
                case "UpdateItem":
                    itemPanel.updateItem();
                    break;
                case "WebView":
                    try {
                        java.awt.Desktop.getDesktop()
                                .browse(java.net.URI.create(itemPanel.getCurrentItem().getUrl().toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Edit":
                    Item citem = itemPanel.getCurrentItem();
                    JDialog editDialog = new JDialog();
                    editDialog.setResizable(false);
                    editDialog.setSize(new Dimension(200, 200));
                    editDialog.setTitle("Add Item");
                    GridLayout elayout = new GridLayout(4, 2);
                    editDialog.setLayout(elayout);

                    String eLabels[] = { "Name: ", "URL: ", "Price: " };
                    JTextField etextFields[] = { new JTextField(citem.getName(), 10),
                            new JTextField(citem.getUrl().toString(), 10),
                            new JTextField(String.valueOf(citem.getCurrentPrice()), 10) };
                    for (int i = 0; i < eLabels.length; i++) {
                        JLabel label = new JLabel(eLabels[i]);
                        editDialog.add(label);
                        editDialog.add(etextFields[i]);
                    }
                    JButton eaccept = new JButton("Accept"), ecancel = new JButton("Cancel");
                    editDialog.add(ecancel);
                    editDialog.add(eaccept);
                    ecancel.addActionListener((e) -> editDialog.dispose());
                    eaccept.addActionListener((e) -> {
                        itemPanel.removeSelecItem();
                        try {
                            Item item = new Item(etextFields[0].getText(), new URL(etextFields[1].getText()));
                            itemPanel.addItem(item);
                            editDialog.dispose();
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        }
                    });
                    editDialog.setVisible(true);
                    break;
                case "Remove":
                    itemPanel.removeSelecItem();
                    break;
                case "Info":
                    JOptionPane.showMessageDialog(null,
                            "Price Watcher © 2019\nBy; Eddie Herrera, Ian Hudson, and Aaron Mendez", "PriceWatcherInfo",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;

                }
            }
        }, Arrays.asList("UpdateAll", "AddItem", "LastItem", "Search", "FirstItem", "UpdateItem", "WebView", "Edit",
                "Remove", "Info"));
    }

}