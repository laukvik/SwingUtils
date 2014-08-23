package org.laukvik.utils.file;

public class FileExtensionTest {

    public static void main(String[] args) {

        assert !FileExtension.getExtensions().isEmpty() : "Didnt find any extensions!";

        assert FileExtension.getDescription("bak").equalsIgnoreCase("Backup") : "Didn't find backup extensions";

        for (String ext : FileExtension.getExtensions()) {
            System.out.println(ext + "=" + FileExtension.getDescription(ext));
        }

    }

}
