/*
 * Copyright (C) 2014 Morten Laukvik
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
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
