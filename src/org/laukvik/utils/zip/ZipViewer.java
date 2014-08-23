package org.laukvik.utils.zip;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class ZipViewer extends JFrame {

    private static final long serialVersionUID = 8798394300690156009L;

    public ZipViewer(String file) throws IOException {
        ZipTableModel model = new ZipTableModel(new ZipFile(file));
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(100);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(ZipViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        new ZipViewer("/Users/morten/Java/apache-maven-3.2.2/lib/aether-api-0.9.0.M2.jar");
    }

}
