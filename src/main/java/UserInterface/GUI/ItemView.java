package UserInterface.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import priceWatcherModel.Item;

public class ItemView extends JPanel{

    private static final long serialVersionUID = 1L;

    private Item item;

    /** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}
	
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/main/java/res/";
        
	/** View-page clicking listener. */
    private ClickListener listener;

    private DecimalFormat formater = new DecimalFormat("#.00");
    private DecimalFormat percentFormat = new DecimalFormat("#");
    
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
        item.update();
        int x = 20, y = 30;
        // g.drawImage(getImage("view.png"), x, y)
        g.drawString("Name:\t"+item.getName(), x, y);
        y += 20;
        g.drawString("\nURL:\t"+item.getUrl(), x, y);
        y += 20;
        g.drawString("\nPrice:\t$ "+formater.format(item.getCurrentPrice()), x, y);
        y += 20;
        
        g.drawString("\nChange:\t%",x,y);
        if(item.isNegative())g.setColor(Color.RED);
        else g.setColor(Color.GREEN);
        g.drawString(percentFormat.format(item.getPriceChage()), x+70, y);
        g.setColor(Color.BLACK);
        g.drawString(" (starting Price: $"+formater.format(item.getStartPrice())+")", x+100, y);
        y += 20;
        g.drawString("\nDate:\t"+item.getDate(), x, y);
        y += 20;
    }
    
    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
        Dimension d = getSize();
    	return (x < d.width && y < d.height);
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

    public Item getItem(){
        return item;
    }

}