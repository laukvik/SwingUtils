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
package org.laukvik.utils.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BeanTableModel implements TableModel {

    private Object object;
    private BeanInfo bi;
    private PropertyDescriptor[] pds;

    public BeanTableModel(Object object) {
        try {
            this.object = object;
            this.bi = Introspector.getBeanInfo(object.getClass());
            this.pds = bi.getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public Object getObject() {
        return this.object;
    }

    public void addTableModelListener(TableModelListener l) {
    }

    public void removeTableModelListener(TableModelListener l) {
    }

    public Class<?> getColumnClass(int column) {
        return String.class;
    }

    public int getColumnCount() {
        return 2;
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Value";
            case 2:
                return "Type";
            case 3:
                return "Expert";
            case 4:
                return "Hidden";
            case 5:
                return "Preferred";
            default:
                return null;
        }
    }

    public int getRowCount() {
        return pds.length;
    }

    public Object getValueAt(int row, int column) {
        PropertyDescriptor pd = pds[ row];

        switch (column) {
            case 0:
                return pd.getName();
            case 1:

                try {
                    Method m = pd.getReadMethod();
//					System.out.println( m.getName() );
                    return m.invoke(object, new Object[0]);
                } catch (Exception e) {
//					e.printStackTrace();
                    return null;
                }
            case 2:
                return pd.getPropertyType();
            case 3:
                return pd.isExpert();
            case 4:
                return pd.isHidden();
            case 5:
                return pd.isPreferred();
            default:
                return null;
        }
    }

    public boolean isCellEditable(int row, int column) {
//		System.out.println( column + "x" + row );
        if (column == 1) {
            PropertyDescriptor pd = pds[ row];
            System.out.println(column + "x" + row + " " + pd.getWriteMethod());
            return pd.getWriteMethod() != null;
        }
        return false;
    }

    public void setValueAt(Object value, int row, int column) {
        PropertyDescriptor pd = pds[ row];
        if (column == 1) {
            try {
                Method m = pd.getWriteMethod();
                Object[] values = {value};
                m.invoke(object, values);
            } catch (Exception e) {

            }
        }
    }

}
