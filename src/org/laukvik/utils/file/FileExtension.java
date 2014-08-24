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
package org.laukvik.utils.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * A utility class which enables you to lookup a file extension and return a
 * short description of the type of file. FileExtension recognises 4290
 * different extensions
 * <p>
 * The utility doesn't support internationalization of the description
 *
 *
 * @author Morten Laukvik
 * @version 1.0
 */
public final class FileExtension {

    private final static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("org.laukvik.utils.file.extensions");
    }

    /**
     * Returns all registered extensions in lower case format.
     *
     * @return a list of extensions
     */
    public final static List<String> getExtensions() {
        return Collections.list(bundle.getKeys());
    }

    /**
     * Returns the description of the specified extension
     *
     * @param extension
     * @return description of the extension
     */
    public static String getDescription(String extension) {
        return bundle.getString(extension.toLowerCase());
    }

    /**
     *
     */
    private static void parse() {
        String ext = null;
        String desc = "";
        try (
                FileReader fr = new FileReader("/Users/morten/Projects/LaukvikUtils/src/org/laukvik/utils/file/extensions.txt");
                BufferedReader is = new BufferedReader(fr);) {
            while (is.ready()) {
                String s = is.readLine();
                boolean isNew = s.startsWith(".");
                if (isNew) {
                    if (ext != null && !ext.isEmpty()) {
                        System.out.println(ext + "=" + desc);
                    }
                    ext = s.substring(1, 6).trim().toLowerCase();
                    desc = s.substring(6).trim();
                } else {
                    desc = desc + ", " + s.trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
