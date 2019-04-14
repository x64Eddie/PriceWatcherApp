package UserInterface.GUI;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import UserInterface.UI;
import priceWatcherModel.Item;

public class PriceWatcherJFrame extends JFrame implements UI {

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(400, 300);

    /** Special panel to display the watched item. */
    private ItemView itemView;

    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

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
    private void viewPageClicked() {
        // try {
        //     java.awt.Desktop.getDesktop()
        //     .browse(java.net.URI.create(itemView.getItem().getUrl().toString()));
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // showMessage("View clicked!");
    }
      
    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	JButton refreshButton = new JButton("Refresh");
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

    }

    @Override
    public void draw() {

    }

    @Override
    public void addItem(Item item) {

    }

    // @Override
    // public void draw() {
    //     //new SimpleGui();
    // }

    // @Override
    // public void addItem(Item item) {

    // }

    // @Override
    // public void configureUI() {
    //     setLayout(new BorderLayout());
    //     JPanel control = makeControlPanel();
    //     control.setBorder(BorderFactory.createEmptyBorder(10, 16, 0, 16));
    //     add(control, BorderLayout.NORTH);
    //     JPanel board = new JPanel();
    //     board.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 16, 0, 16),
    //             BorderFactory.createLineBorder(Color.GRAY)));
    //     board.setLayout(new GridLayout(1, 1));
    //     try {
    //         itemView = new ItemView(new Item("Sony Tv", new URL(
    //                 "https://www.bestbuy.com/site/samsung-65-class-led-nu8000-series-2160p-smart-4k-uhd-tv-with-hdr/6199828.p?skuId=6199828")));
    //     } catch (MalformedURLException e) {
    //         e.printStackTrace();
    //     }
    //     itemView.setClickListener(this::viewPageClicked);
    //     board.add(itemView);
    //     add(board, BorderLayout.CENTER);
    //     msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
    //     add(msgBar, BorderLayout.SOUTH);
    // }
   

}