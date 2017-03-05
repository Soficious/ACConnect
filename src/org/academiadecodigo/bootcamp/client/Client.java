package org.academiadecodigo.bootcamp.client;
import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ricardo on 27-02-2017.
 */

public abstract class Client {



    private String name;
    private String motto;
    private File file;
    private boolean matched;


    /**
     * Class construtor.
     * Receive String and sets the name
     * insticiates a File with the correct showCompanyFile using the name
     */
    public Client(String name) {
        this.motto = motto;
        this.name = name;
        this.file = new File("resources/company/" + name + ".pdf");
    }
}
