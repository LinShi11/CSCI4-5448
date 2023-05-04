package GameUIHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameUIHelper {

    /**
     * This function is a helper function for text field
     * @param textField: the text field that we are modifying
     * @param font: the font to use
     * @param panel: the panel that the text field should be added to
     */
    public void setTextFieldHelper(JTextField textField, Font font, JPanel panel){
        textField.setBackground(Color.black);
        textField.setForeground(Color.white);
        textField.setFont(font);
        panel.add(textField);
    }

    /**
     * This function is a helper function for Jpanel
     * @param panel: the panel that we wish to modify
     * @param con: the container that we are adding the panel to
     */
    public void setPanelHelper(JPanel panel, Container con){
        panel.setBackground(Color.black);
        panel.setForeground(Color.white);
        con.add(panel);
    }

    /**
     * This is a helper function for the JLabel
     * @param label: the label to change
     * @param font: the font to use
     */
    public void setLabelHelper(JLabel label, Font font){
        label.setBackground(Color.black);
        label.setForeground(Color.white);
        label.setFont(font);
    }

    /**
     * This is a helper function for the JButtons
     * @param button: the JButton to modify
     * @param font: the font to use
     * @param actionListener: the Action listener that are used
     * @param panel: the JPanel to add
     * @param command: the command for the button
     */
    public void setButtonHelper(JButton button, Font font, ActionListener actionListener, JPanel panel, String command){
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setFont(font);
        if(command != null) {
            button.setActionCommand(command);
        }
        if(actionListener != null) {
            button.addActionListener(actionListener);
        }
        panel.add(button);
    }

    /**
     * This is the helper function for textarea
     * @param textArea: the textarea to modify
     * @param font: the font to use
     * @param panel: the panel to add to
     */
    public void setTextAreaHelper(JTextArea textArea, Font font, JPanel panel){
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setFont(font);
        panel.add(textArea);
    }
}
