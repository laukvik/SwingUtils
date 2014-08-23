package org.laukvik.utils.beans;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

public class BeanPanel extends JComponent {

    private static final long serialVersionUID = 1L;

    public BeanPanel(Object object) {
        setLayout(new BorderLayout());
        add(new JScrollPane(new BeanTable(object)));
    }

}
