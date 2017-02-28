package org.academiadecodigo.bootcamp;

import java.io.File;
import java.net.Socket;

/**
 * Created by ricardo on 27-02-2017.
 */
public class Company implements Runnable{


    private String name;
    private String moto;
    private File file;
    //private LinkedList<E>; for matched Cadets
    private boolean matched;
    Socket clientSocket = null;


    public Company (String name){
        this.name = name;
        this.file = new File("resources/company/" + name + ".pdf");
    }

    @Override
    public void run() {
        clientSocket = new Socket();
    }
}
