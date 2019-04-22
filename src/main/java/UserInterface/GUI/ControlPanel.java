package UserInterface.GUI;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
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
    private Map<String, JButton> buttons;
    private ControlPanelListener controlPanelListener;
    private ActionListener actionListener = (e) -> controlPanelListener.buttonPressed(((JButton)e.getSource()).getText(), (JButton)e.getSource());
  
    // private MouseListener mouseListener = new MouseListener() {

    //     @Override
    //     public void mouseReleased(MouseEvent e) {

    //     }

    //     @Override
    //     public void mousePressed(MouseEvent e) {

    //     }

    //     @Override
    //     public void mouseExited(MouseEvent e) {

    //     }

    //     @Override
    //     public void mouseEntered(MouseEvent e) {
    //         AvailableButtons button = AvailableButtons.valueOf(((JButton) e.getSource()).getName());
    //         // increase the size of the button when the users hover ontop of the button.
    //         increaseSize(buttons.get(button));
    //     }

    //     @Override
    //     public void mouseClicked(MouseEvent e) {

    //     }
    // };

    public ControlPanel(ControlPanelListener eventListener, List<String> items) {
        buttons = new HashMap<>();

        setLayout(new GridLayout(2, 5));
        // We are going to create all the available buttons!
        this.controlPanelListener = eventListener;
        for(String btnName: items){
            JButton btn = new JButton(btnName);
            btn.addActionListener(actionListener);
            buttons.put(btnName, btn);
            add(btn);
        }
    }

    /**
     * Increases the size of the button when the mouse is hovering on top of the
     * button.
     */
    public void increaseSize(JButton button) {

    }
}

@FunctionalInterface
interface ControlPanelListener {
    void buttonPressed(String buttonPressed, JButton sourceBtn);
}