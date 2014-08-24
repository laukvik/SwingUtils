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

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Morten
 */
public class Browser extends JFrame implements TreeSelectionListener {

    private static final long serialVersionUID = 3076260520100915475L;
    private Library library;
    private final PhotoTableModel model;
    private final JTable table;
    private final JTree tree;
    private final DefaultMutableTreeNode root;
    private final PhotoPanel images;

    public Browser() {
        root = new DefaultMutableTreeNode("iPhoto Browser");
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.addTreeSelectionListener(this);
        model = new PhotoTableModel();
        table = new JTable(model);
        table.setRowHeight(150);
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        // 240x160 120x80
        images = new PhotoPanel(); // 6*6=360 6*4=240 4*6=24 4*4=160
//		images.setSize( 300, 300 );
//		images.setMaximumSize( new Dimension(300,300) );
//		JScrollPane scroll = new JScrollPane( table );
        JScrollPane scroll = new JScrollPane(images);
        scroll.setAutoscrolls(true);
//		scroll.setSize( 300, 300 );
//		scroll.setMaximumSize( new Dimension(300,300) );
        JScrollPane scroll2 = new JScrollPane(tree);
        JSplitPane split = new JSplitPane();
        split.setDividerLocation(250);
        split.setLeftComponent(scroll2);
        split.setRightComponent(scroll);
        add(split);
        pack();
        setSize(640, 480);
        setVisible(true);
    }

    public void setLibrary(Library library) {
        this.library = library;
        log("Loading library ");
        root.removeAllChildren();
        for (Album alb : library.list()) {
            root.add(new DefaultMutableTreeNode(alb));
        }
        tree.scrollPathToVisible(new TreePath(root.getFirstLeaf().getPath()));
    }

    public Library getLibrary() {
        return library;
    }

    public void log(String message) {
        System.out.println("Browser: \t" + message);
    }

    public static void main(String[] args) throws Exception {
        Parser parser = new Parser();
        Browser browser = new Browser();
        browser.setLibrary(parser.load());
    }

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode o = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        Album alb = (Album) o.getUserObject();
        model.setAlbum(alb, table);
        images.removeAll();
        for (Photo p : alb.list()) {
            images.add(new PhotoIcon(p));
        }
    }

}
