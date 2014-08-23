package org.laukvik.utils.beans;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BeanTableRenderer extends JLabel implements TableCellRenderer {

    private static final long serialVersionUID = 1L;
    private Color odd = new Color(255, 255, 255);
    private Color even = new Color(240, 240, 240);

    public BeanTableRenderer() {
    }

    public BeanTableRenderer(Color odd, Color even) {
        this.odd = odd;
        this.even = even;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setOpaque(true);
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(row % 2 == 0 ? odd : even);
            setForeground(table.getForeground());
        }
        setText(value == null ? "" : value.toString());

        if (value instanceof Font) {
            Font font = (Font) value;
            setFont(font);
            setText(font.getName() + " " + font.getSize() + " " + font.getStyle());
            table.setRowHeight(row, font.getSize() + 4);
        } else {
            setFont(table.getFont());
        }
        if (value instanceof Color) {
            setBackground((Color) value);
            setText("");
        }
        if (value instanceof Icon) {
            Icon icon = (Icon) value;
            setIcon(icon);
            table.setRowHeight(row, icon.getIconHeight());
            setText("");
        } else {
            setIcon(null);
        }
        if (value instanceof URL) {
            URL url = (URL) value;
            setText(url.toExternalForm());
        }
        return this;
    }

}
