package org.laukvik.utils.zip;

import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ZipTableModel implements TableModel {

    private final ZipFile zip;
    private final ZipEntry[] es;

    @SuppressWarnings("unchecked")
    public ZipTableModel(ZipFile zip) {
        this.zip = zip;
        this.es = new ZipEntry[zip.size()];
        // Enumerate each entry
        int x = 0;
        for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
            // Get the entry name
            ZipEntry e = ((ZipEntry) entries.nextElement());
            es[ x] = e;
            x++;
        }
    }

    public void addTableModelListener(TableModelListener l) {
    }

    public void removeTableModelListener(TableModelListener l) {
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 2;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "File";
            case 1:
                return "Size";
            default:
                return "";
        }
    }

    public int getRowCount() {
        return zip.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return es[ rowIndex].getName();
            case 1:
                return es[ rowIndex].getSize();
            default:
                return "";
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

}
