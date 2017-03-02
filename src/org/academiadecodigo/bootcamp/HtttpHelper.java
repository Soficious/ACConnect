package org.academiadecodigo.bootcamp;

import java.util.Iterator;

/**
 * Created by ricardo on 27-02-2017.
 */
public class HtttpHelper implements Iterable {



    //String to request Headers


    /**
     * Method for a Bad Request
     * @return HTTP 400 Bad Request
     */
    public static String badRequest() {
        return "HTTP/1.0 400 Bad Request\r\n";
    }

    /**
     * Method for a page not found
     * @return a 404 Page not Found
     */
    public static String notFound() {
        return "HTTP/1.0 404 Not Found\r\n";
    }

    public static String notAllowed() {
        return "HTTP/1.0 405 Method Not Allowed\r\n" +
                "Allow: GET\r\n";
    }

    public static String ok() {
        return "HTTP/1.0 200 Document Follows\r\n";
    }

    public static String contentLength(long lenght) {

        //TODO: instanciar ficheiro...passar o tamanho

        //an extra line is needed before content
        return "Content-Length: " + lenght + "\r\n\r\n";
    }

    public static String unsupportedMedia() {
        return "HTTP/1.0 415 Unsupported Media Type\r\n";
    }

    public static String contentType(String file) {

        if (Media.isImage(file)) {
            return "Content-Type: image/" + Media.getExtension(file) + "\r\n";
        }
        if (Media.isPdf(file)){
            return "Content-Type: codeCadet/" + Media.getExtension(file) + "\r\n";
        }

        return "Content-Type: text/html; charset=UTF-8\r\n";

    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
