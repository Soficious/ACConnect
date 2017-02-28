package org.academiadecodigo.bootcamp;

/**
 * Created by ricardo on 27-02-2017.
 */
public class Media {

    public static boolean isImage(String file) {

        switch (getExtension(file)) {
            case "jpg":
            case "png":
            case "ico":
                return true;
            default:
                return false;
        }

    }
    public static boolean isPdf(String file){

        switch (getExtension(file)){
            case "pdf":
            case "txt":
                return true;
            default:
                return false;
        }

    }

    public static boolean isHtml(String file) {
        return getExtension(file).equals("html");
    }

    public static boolean isSupported(String file) {
        return isHtml(file) || isImage(file) || isPdf(file);
    }

    public static String getExtension(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }
}
