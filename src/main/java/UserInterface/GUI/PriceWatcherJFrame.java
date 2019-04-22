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

    private void createItemButtonClicked(ActionEvent event){

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
                    JDialog addDialog = new JDialog();
                    addDialog.setSize(new Dimension(100,400));
                    addDialog.setTitle("Add Item");
                    addDialog.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();

                    JLabel nameLabel = new JLabel("Name: ");
                    JLabel urlLabel = new JLabel("URL: ");
                    JLabel priceLabel = new JLabel("Price; ");

                    JTextField nameText = new JTextField(25);
                    JTextField urlText = new JTextField(25);
                    JTextField priceText = new JTextField(25);

                    JButton createBtn = new JButton("Create");
                    JButton cancelBtn = new JButton("Cancel");

                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    addDialog.add(nameLabel);

                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    addDialog.add(nameText);

                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    addDialog.add(urlLabel);

                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    addDialog.add(urlText);

                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    addDialog.add(priceLabel);

                    gbc.gridx = 1;
                    gbc.gridy = 2;
                    addDialog.add(priceText);

                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    gbc.gridwidth = 2;
                    addDialog.add(createBtn);

                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    gbc.gridwidth = 2;
                    addDialog.add(cancelBtn);

                    createBtn.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String itemName = nameText.getText();
                            String itemUrlString = urlText.getText();
                            double itemPrice = Double.parseDouble(priceText.getText());

                            try{
                                URL itemUrl = new URL(itemUrlString);
                                Item itemToAdd = new Item(itemName, itemUrl);
                                
                            }
                            catch(MalformedURLException exception){
                                exception.printStackTrace();
                            }
                        }
                    });
                    addDialog.show();
                    break;
                case "AddItem":
                    sourceBtn.setText("Pressed");
                    sourceBtn.repaint();
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