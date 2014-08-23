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
