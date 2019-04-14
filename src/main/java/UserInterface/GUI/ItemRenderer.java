package UserInterface.GUI;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import priceWatcherModel.Item;

/*
 * Custom renderer that is going to render a custom label for displaying the item
 * in a JList.
 */
class ItemRenderer extends JLabel implements ListCellRenderer<Item> {

    @Override
    public Component getListCellRendererComponent(
        JList<? extends Item> list, Item value, int index, boolean isSelected,
            boolean cellHasFocus) {

        //TODO we are going to get the image 
        return null;
    }
}