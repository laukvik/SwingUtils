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
