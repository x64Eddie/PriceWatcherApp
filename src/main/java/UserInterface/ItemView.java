package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import priceWatcherModel.Item;

public class ItemView extends JPanel{

    private Item item;

    /** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}
	
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/image/";
        
	/** View-page clicking listener. */
    private ClickListener listener;
    
    /** Create a new instance. */
    public ItemView(Item item) {
        this.item = item;
    	setPreferredSize(new Dimension(100, 160));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
            		listener.clicked();
            	}
            }
        });
    }
        
    /** Set the view-page click listener. */
    public void setClickListener(ClickListener listener) {
    	this.listener = listener;
    }
    
    /** Overridden here to display the details of the item. */
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        //Dimension dim = getSize();
        System.out.print("painted");
        //--
        //-- WRITE YOUR CODE HERE!
        //--
        int x = 20, y = 30;
        // g.drawImage(getImage("view.png"), x, y)
        g.drawString("Name:\t"+item.getName(), x, y);
        y += 20;
        g.drawString("\nURL:\t"+item.getUrl(), x, y);
        y += 20;
        g.drawString("\nPrice:\t"+item.getCurrentPrice(), x, y);
        System.out.print(item.getCurrentPrice());
        y += 20;
        g.drawString("\nChange:\t"+item.getPriceChage(), x, y);
        System.out.print(item.getPriceChage());
        y += 20;
        g.drawString("\nDate:\t"+item.getDate(), x, y);
        y += 20;
    }
    
    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
    	//--
    	//-- WRITE YOUR CODE HERE
    	//--
    	return new Rectangle(20, 20, 30, 20).contains(x,  y);
    }
        
    /** Return the image stored in the given file. */
    public Image getImage(String file) {
        try {
        	URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}