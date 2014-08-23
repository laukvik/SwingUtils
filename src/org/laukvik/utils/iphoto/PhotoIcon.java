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
