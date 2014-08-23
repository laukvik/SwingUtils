package org.laukvik.utils.beans;

import javax.swing.JTable;

public class BeanTable extends JTable {

    private static final long serialVersionUID = 1L;
    BeanTableModel model;
    BeanTableCellEditor editor;

    public BeanTable() {
        super();
        setDefaultRenderer(Object.class, new BeanTableRenderer());
    }

    public BeanTable(Object object) {
        super();
        setDefaultRenderer(Object.class, new BeanTableRenderer());
        setBean(object);
    }

    public void setBean(Object object) {
        model = new BeanTableModel(object);
        setModel(model);
        editor = new BeanTableCellEditor();
        getColumnModel().getColumn(1).setCellEditor(editor);
    }

    public Object getBean() {
        return model.getObject();
    }

}
