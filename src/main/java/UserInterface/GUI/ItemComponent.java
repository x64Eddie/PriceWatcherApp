package UserInterface.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JComponent;

import io.reactivex.annotations.NonNull;
import priceWatcherModel.Item;

/**
 * Represents the View representation of the item, this is going to be the UI of
 * the Item
 */
class ItemComponent extends JComponent {

    private static final long serialVersionUID = 1L;
    private final static String IMAGE_DIR = "/main/java/res/";
    private DecimalFormat formater;
    private DecimalFormat percentFormat;
    private Item item;

    /**
     * Setting the item in order for the component to extract the information from
     * the item.
     * 
     * @param item - the item that has the information that its going to display.
     */
    public void setItem(@NonNull Item item) {
        this.item = item;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // we are going to paint the custom Item
        if (item != null) {
            int x = 20, y = 30;
            g.drawString("Name:\t" + item.getName(), x, y);
            y += 20;
            g.drawString("\nURL:\t" + item.getUrl(), x, y);
            y += 20;
            g.drawString("\nPrice:\t$ " + formater.format(item.getCurrentPrice()), x, y);
            y += 20;

            g.drawString("\nChange:\t%", x, y);
            if (item.isNegative())
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);
            g.drawString(percentFormat.format(item.getPriceChage()), x + 70, y);
            g.setColor(Color.BLACK);
            g.drawString(" (starting Price: $" + formater.format(item.getStartPrice()) + ")", x + 100, y);
            y += 20;
            g.drawString("\nDate:\t" + item.getDate(), x, y);
            y += 20;
        }
    }
}