package org.laukvik.utils.iphoto;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

    private Library lib;

    public Parser() {
    }

    public File getDefaultLibraryFile() {
//        File file = new File(System.getProperty("user.home"), "Pictures/iPhoto Library/AlbumData.xml");
        File file = new File(System.getProperty("user.home"), "Pictures/iPhoto-bibliotek.photolibrary/AlbumData.xml");
        return file;
    }

    public Library load() throws ParserConfigurationException, SAXException, IOException {
        return load(getDefaultLibraryFile());
    }

    public void log(String message) {
        System.out.println(message);
    }

    public Library load(File file) throws ParserConfigurationException, SAXException, IOException {
        log("Loading: '" + file.getAbsolutePath() + "'");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xml = builder.parse(file);
        log("Parsing: ");
        Node plist = xml.getChildNodes().item(0);
        lib = new Library(plist);
        return lib;
    }

    public void addAlbums(Node albumsUsingArrayNode) {
        NodeList list = albumsUsingArrayNode.getChildNodes();
        for (int x = 0; x < list.getLength(); x++) {
            Node node = list.item(x);
            if (node.getNodeName().equalsIgnoreCase("dict")) {
                addAlbum(node);
            }
        }
    }

    public void addAlbum(Node albumUsingDictNode) {
        NodeList list = albumUsingDictNode.getChildNodes();
        String key = "";
        String value = "";
        Album album = null;

        for (int x = 0; x < list.getLength(); x++) {
            Node node = list.item(x);
            if (node.getNodeName().equalsIgnoreCase("key")) {
                key = node.getTextContent();

            } else if (node.getNodeName().equalsIgnoreCase("string")) {
                value = node.getTextContent();

                if (key.equalsIgnoreCase("AlbumName")) {
                    album = new Album(value);
                    album.setNode(node);
                    lib.add(album);
                }
            } else if (node.getNodeName().equalsIgnoreCase("array")) {
                addPhotos(node, album);

            } else if (node.getNodeName().equalsIgnoreCase("integer")) {
                album.setAlbumID(node.getTextContent());
            }
        }
    }

    public void addPhotos(Node albumsUsingArrayNode, Album album) {
        NodeList list = albumsUsingArrayNode.getChildNodes();
        for (int x = 0; x < list.getLength(); x++) {
            Node node = list.item(x);
            if (node.getNodeName().equalsIgnoreCase("string")) {
                addPhoto(album, node.getTextContent());
            }
        }
    }

    public void addPhoto(Album album, String photoID) {
        album.addById(photoID, lib.getImagesNode());
    }

}
