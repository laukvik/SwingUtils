package org.laukvik.utils.iphoto;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class PhotoTableModel implements TableModel {

    private static final long serialVersionUID = -3023063609335267709L;
    private Album album = new Album();

    public PhotoTableModel() {
    }

    public void addTableModelListener(TableModelListener l) {
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ImageIcon.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                return String.class;
        }
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Thumbnail";
            case 1:
                return "Name";
            case 2:
                return "Description";
            default:
                return null;
        }
    }

    public int getRowCount() {
        return album.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Photo p = album.list(rowIndex);
        switch (columnIndex) {
            case 0:
                return new ImageIcon(p.getPath());
            case 1:
                return p.getName();
            case 2:
                return p.getDescription();
            default:
                return null;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void removeTableModelListener(TableModelListener l) {
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album, JTable table) {
        this.album = album;
        table.tableChanged(new TableModelEvent(this));
    }

}
