package org.laukvik.utils.beans;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class BeanTableCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private static final long serialVersionUID = 1L;
    JButton button;
    JColorChooser colorChooser;
    JDialog colorDialog;
    protected static final String COLOR = "color";
    protected static final String OBJECT_ACTION = "object";
    Object o;
    JTextField textField;
    JCheckBox checkBox = new JCheckBox();
    JComponent component = new JTextField();
    BeanDialog beanDialog = null;

    public BeanTableCellEditor() {
        button = new JButton();
        button.addActionListener(this);
        button.setBorderPainted(false);

        //Set up the dialog that the button brings up.
        colorChooser = new JColorChooser();
        colorDialog = JColorChooser.createDialog(
                button, "Pick a Color", true, //modal
                colorChooser, this, //OK button handler
                null
        );

    }

    /**
     * Opens up custom dialog boxes
     *
     */
    public void actionPerformed(ActionEvent e) {
        if (o instanceof Color) {
            if (COLOR.equals(e.getActionCommand())) {
				//The user has clicked the cell, so
                //bring up the dialog.
                button.setBackground((Color) o);
                colorChooser.setColor((Color) o);
                colorDialog.setVisible(true);

                fireEditingStopped(); //Make the renderer reappear.

            } else { //User pressed dialog's "OK" button.
                o = colorChooser.getColor();
            }
        } else if (o instanceof String) {
            o = ((JTextField) component).getText();
        } else {
            if (e.getActionCommand().equalsIgnoreCase(OBJECT_ACTION)) {
                /* Start editing */
                beanDialog.showDialog();
                fireEditingStopped();
            } else {
                /* Stop editing */

            }
        }
    }

    /**
     * Implement the one CellEditor method that AbstractCellEditor doesn't.
     *
     */
    public Object getCellEditorValue() {
        if (component instanceof JTextField) {
            return ((JTextField) component).getText();
        } else if (component instanceof JCheckBox) {
            return ((JCheckBox) component).isSelected();
        }
        return o;
    }

    /**
     *
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        o = value;
        if (value instanceof Color) {
            button.setActionCommand(COLOR);
            component = button;
            return component;
        } else if (value instanceof Boolean) {
            component = new JCheckBox("", (Boolean) value);
            return component;
        } else if (value instanceof String) {
            component = new JTextField(value.toString());
            return component;
        } else if (value instanceof Number) {
            component = new JTextField(value.toString());
            return component;
        } else {
            button.setActionCommand(OBJECT_ACTION);
            beanDialog = new BeanDialog(value);
            component = button;
            return component;
        }
    }
}
