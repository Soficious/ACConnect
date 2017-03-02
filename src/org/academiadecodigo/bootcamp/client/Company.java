package org.academiadecodigo.bootcamp.client;

/**
 * Created by Renato on 28/02/17.
 */
public class Company extends Client {

    /**
     * Class construtor.
     * Receive String and sets the name
     * insticiates a File with the correct path using the name
     *
     * @param name
     * @param moto
     */
    public Company(String name, String moto) {
        super(name, moto);
    }
}
