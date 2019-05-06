package UserInterface.GUI;

import java.awt.Color;
import java.awt.Component;


import javax.swing.JList;
import javax.swing.ListCellRenderer;

import priceWatcherModel.Item;

/*
 * Custom renderer that is going to render a custom label for displaying the item
 * in a JList.
 * 
 * 
 */
class ItemRenderer extends ItemComponent implements ListCellRenderer<Item> {

    private static final long serialVersionUID = 6976866887638917348L;
    private SelectionListener selectionListener;

    @Override
    public Component getListCellRendererComponent(
        JList<? extends Item> list, Item item, int index, boolean isSelected,
            boolean cellHasFocus) {
        setItem(item);
        if (isSelected){
            if(selectionListener != null)selectionListener.selectionMade(this);
            setBackground(new Color(0xFFFFCC));item.update();

        }
        else setBackground(list.getBackground());
        
        return this;
    }

    public void addListener(SelectionListener selectionListener){
        this.selectionListener = selectionListener;
    }

}