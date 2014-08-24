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
