package org.laukvik.utils.iphoto;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class AlbumTreeRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = -167539240567732372L;
    public final static Icon collection = getIcon("collection.png");
    public final static Icon albumOpen = getIcon("album.png");
    public final static Icon albumClosed = getIcon("album.png");
    public final static Icon roll = getIcon("roll.png");
    public final static Icon library = getIcon("library.png");
    public final static Icon keywords = getIcon("keywords.png");

    public AlbumTreeRenderer() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public static Icon getIcon(String filename) {
        return new javax.swing.ImageIcon(AlbumTreeRenderer.class.getResource("images/" + filename));
    }

    public Color getBackgroundSelectionColor() {
        return HILITE;
    }

    public Color getTextSelectionColor() {
        return Color.WHITE;
    }

    public Color getBorderSelectionColor() {
        return HILITE;
    }

    public static final Color HILITE = new Color(61, 128, 223);

    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (leaf) {
            if (expanded) {
                setIcon(albumOpen);
            } else {
                setIcon(albumClosed);
            }
        } else {
            setIcon(collection);
        }
        return this;
    }

}
