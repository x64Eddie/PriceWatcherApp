package UserInterface.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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
import javax.swing.SwingUtilities;

import UserInterface.UI;
import priceWatcherModel.Item;
import priceWatcherModel.ItemManager;

public class PriceWatcherJFrame extends JFrame implements UI {

    private static final long serialVersionUID = 1L;

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(500, 700);

    /** Special panel to display the watched item. */
    private ItemPanel itemView;

    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    // -----------------------------------------
    private DefaultListModel<Item> listModel;
    private JList<Item> jList;

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

    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        panel.add(refreshButton);

        JButton add = new JButton("Add");
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        panel.add(refreshButton);

        return panel;
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
        JPanel control = new ControlPanel(new ControlPanelListener() {

            @Override
            public void buttonPressed(String buttonPressed, JButton sourceBtn) {
                switch (buttonPressed) {
                case "UpdateAll":
                case "AddItem":
                case "LastItem":
                case "Search":
                case "FirstItem":
                case "UpdateItem":
                case "WebView":
                case "Edit":
                case "Remove":
                case "Info":
                }
            }
        }, Arrays.asList("UpdateAll","AddItem","LastItem","Search","FirstItem","UpdateItem","WebView","Edit","Remove","Info" ));
        control.setBorder(BorderFactory.createEmptyBorder(10, 16, 0, 16));
        add(control, BorderLayout.NORTH);
        ItemPanel itemPanel = new ItemPanel();
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

}