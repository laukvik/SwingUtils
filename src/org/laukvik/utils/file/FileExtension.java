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
