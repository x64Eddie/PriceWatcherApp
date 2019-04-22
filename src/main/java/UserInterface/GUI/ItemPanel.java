package UserInterface.GUI;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


import priceWatcherModel.Item;


/**
 * Represents the Panel where the Items are going to be displayed.
 */
public class ItemPanel extends JPanel implements SelectionListener {

    private static final long serialVersionUID = 1L;
    private JScrollPane scrollPanel;
    private DefaultListModel<Item> listModel;
    private JList<Item> jList;
    private ItemComponent selectedComponent;

    public ItemPanel() {
        super(new BorderLayout());

        listModel = new DefaultListModel<>();

        jList = new JList<>(listModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ItemRenderer itemRenderer = new ItemRenderer();
        jList.setCellRenderer(itemRenderer);

        scrollPanel = new JScrollPane(jList);
        add(scrollPanel, BorderLayout.CENTER);

    }

    public Item getSelecComp(){
        return jList.getSelectedValue();
    }

    public void removeSelecItem(){
        int selected = jList.getSelectedIndex();
        if(selected != -1)listModel.remove(selected);
    }

    /**
     * Adding the elemnt to the Jlist
     * 
     * @param item - adding this item to the lsit
     */
    public void addItem(Item item) {
        listModel.add(0, item);
    }

    @Override
    public void selectionMade(ItemComponent itemComponent) {
        selectedComponent = itemComponent;
    }

}
/**
 * Notifies when an item is selected in the list.
 */
@FunctionalInterface
interface SelectionListener {
    void selectionMade(ItemComponent itemComponent);
}