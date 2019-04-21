package UserInterface.GUI;

import java.awt.Color;
import java.awt.Dimension;
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

    public ItemComponent() {
        this.formater = new DecimalFormat("#.00");
        this.percentFormat = new DecimalFormat("#");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 200);
    }

    public void setItem(@NonNull Item item) {
        this.item = item;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 20, y = 30;
        item.update();
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

    public void paint(Graphics g) {
        super.paint(g);
        paintComponent(g);
        paintChildren(g);
    }
}