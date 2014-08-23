package org.laukvik.utils.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Zipper {

    private ZipFile zip;

    public Zipper(ZipFile zip) {
    }

    @SuppressWarnings("unchecked")
    public void list() {
        int x = 0;
        // Enumerate each entry
        for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
            // Get the entry name
            ZipEntry e = ((ZipEntry) entries.nextElement());
            System.out.println(x + ". " + e.getName() + "\t" + e.getSize() + "\t" + new Date(e.getTime()));
            x++;
        }
    }

    public void extract(ZipEntry e, File file) throws Exception {
        InputStream in = zip.getInputStream(e);
        OutputStream out = new FileOutputStream(file);
        // Transfer bytes from the compressed file to the output file
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        // Close the file and stream
        in.close();
        out.close();
    }

}
