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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class BeanViewer extends JFrame {

    private static final long serialVersionUID = 1L;
    private final BeanTable table;

    public BeanViewer(Object object) {
        table = new BeanTable();
        add(new JScrollPane(table));
        setSize(400, 600);
        setVisible(true);
        table.setBean(object);
    }

    public void setBean(Object object) {
        table.setBean(object);
    }

    public static void main(String[] args) {
        new BeanViewer(new JButton());
    }

}
