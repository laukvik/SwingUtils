package org.laukvik.utils.beans;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class BeanViewer extends JFrame {

    private static final long serialVersionUID = 1L;
    private BeanTable table;

    public BeanViewer() {
        table = new BeanTable();
        add(new JScrollPane(table));
        setSize(400, 600);
        setVisible(true);
        JButton b = new JButton();
        setBean(b);
    }

    public void setBean(Object object) {
        table.setBean(object);
    }

    public static void main(String[] args) {
        new BeanViewer();
    }

}
