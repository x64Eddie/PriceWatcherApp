package UserInterface.GUI;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Represents all the controls that are located in the top of the JFrame, these
 * controls are going to change the apperance and even add Items to the ItemView
 * where the user is able to interact with them
 */
class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Map<AvailableButtons,JButton> buttons;
    private ControlPanelListener controlPanelListener;
    private ActionListener actionListener =(e) ->{
        if(e.getSource() instanceof JButton){
        AvailableButtons button = AvailableButtons.valueOf(((JButton)e.getSource()).getName());
        controlPanelListener.buttonPressed(button, buttons.get(button));
        }
    };
    private MouseListener mouseListener = new MouseListener(){
    
        @Override
        public void mouseReleased(MouseEvent e) {
            
        }
    
        @Override
        public void mousePressed(MouseEvent e) {
            
        }
    
        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    
        @Override
        public void mouseEntered(MouseEvent e) {
            AvailableButtons button = AvailableButtons.valueOf(((JButton)e.getSource()).getName());
            //increase the size of the button when the users hover ontop of the button.
            increaseSize(buttons.get(button));
        }
    
        @Override
        public void mouseClicked(MouseEvent e) {
            
        }
    };

    public ControlPanel(ControlPanelListener eventListener) {
        buttons = new HashMap<>();
        //We are going to create all the available buttons!
        for (AvailableButtons button : AvailableButtons.values()) {
            JButton temp = new JButton(button.text);
            temp.addActionListener(actionListener);
            temp.addMouseListener(mouseListener);
            buttons.put(button, temp);
        }
        this.controlPanelListener = eventListener;

    }

    /**
     * Increases the size of the button when the mouse is hovering on top of the
     * button.
     */
    public void increaseSize(JButton button) {

    }
}

/**
 * The button event that could be trigger from the user pressing one
 */
enum AvailableButtons {
UPDATE_ALL("UpdateAll"), ADDITEM("AddItem"), LAST_ITEM("LastItem"), SEARCH("Search"),
 FIRST_ITEM("FirstItem"), UPDATE_ITEM("UpdateItem"),
WEB_VIEW("WebView"), EDIT("Edit"), REMOVE("Remove"), INFO("Info");
    final String text;
    AvailableButtons(final String text){
        this.text = text;
    }
    @Override
    public String toString(){
        return text;
    }
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
}

@FunctionalInterface
interface ControlPanelListener{
    void buttonPressed(AvailableButtons buttonPressed, JButton sourceBtn);
}