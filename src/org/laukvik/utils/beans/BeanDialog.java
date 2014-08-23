package org.laukvik.utils.beans;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

public class BeanDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public BeanDialog(Object object) {
        if (object == null) {
            object = new Object();
        }
        add(new JScrollPane(new BeanTable(object)));
        setModal(true);
        setSize(300, 400);
    }

    public void showDialog() {
        setVisible(true);
    }

}
