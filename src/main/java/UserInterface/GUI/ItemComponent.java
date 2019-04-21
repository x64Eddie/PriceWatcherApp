package UserInterface.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import io.reactivex.annotations.NonNull;
import priceWatcherModel.Item;

/**
 * Represents the View representation of the item, this is going to be the UI of
 * the Item
 */
class ItemComponent extends JPanel {

    private static final long serialVersionUID = 1L;
    private final static String IMAGE_DIR = ".//src//res//web.png";
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

    public void paint(Graphics g) {
        super.paint(g);
        // paintComponent(g);
        int x = 10, y =15;
        BufferedImage image;
        try {                
             image = ImageIO.read(new File(IMAGE_DIR).getAbsoluteFile());
             g.drawImage(image, 0, 0,20,20, this); 
            
         } catch (IOException ex) {
              // handle exception...
              ex.printStackTrace();
         }
        x = 20; y = 30;
        item.update();
        g.drawString(" Name:\t\t\t\t" + item.getName(), x, y);
        y += 20;

        g.drawString("\nURL:\t\t\t\t" + item.getUrl(), x, y);
        y += 20;
        g.drawString("\nPrice:\t\t\t\t$ " + formater.format(item.getCurrentPrice()), x, y);
        y += 20;

        g.drawString("\nChange:\t\t\t\t%", x, y);
        if (item.isNegative())
            g.setColor(Color.RED);
        else
            g.setColor(Color.GREEN);
        g.drawString(percentFormat.format(item.getPriceChage()), x + 70, y);
        g.setColor(Color.BLACK);
        g.drawString(" (starting Price: $" + formater.format(item.getStartPrice()) + ")", x + 100, y);
        y += 20;
        g.drawString("\nDate:\t\t\t\t" + item.getDate(), x, y);
        y += 20;
        paintChildren(g);
    }
}