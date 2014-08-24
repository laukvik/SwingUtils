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
package org.laukvik.utils.iphoto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PhotoIcon extends JLabel {

    private static final long serialVersionUID = -5992466907088133209L;
    public final static Dimension THUMBNAIL_SIZE = new Dimension(240, 160);
    public final static Dimension LABEL_SIZE = new Dimension(THUMBNAIL_SIZE.width, THUMBNAIL_SIZE.height + 20);
    Photo photo;

    public PhotoIcon(Photo photo) {
        super();
        this.photo = photo;
        setBackground(Color.RED);
        ImageIcon i = new ImageIcon(photo.getPath());
        setIcon(i);
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        setText(photo.getName());
        setToolTipText(photo.getDescription());
        setSize(LABEL_SIZE);
        setPreferredSize(LABEL_SIZE);
        setMinimumSize(LABEL_SIZE);
        setMaximumSize(LABEL_SIZE);
        Font font = getFont();
        setFont(new Font(font.getName(), Font.PLAIN, font.getSize() - 2));
        setVisible(true);
    }

}
