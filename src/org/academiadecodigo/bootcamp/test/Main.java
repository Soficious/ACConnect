package org.academiadecodigo.bootcamp.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Renato on 04/03/17.
 */
public class Main {

    public static void main(String[] args) {

        ServerSocket welcomeSocket = null;

        try {
            welcomeSocket = new ServerSocket(9999);

            while (true) {


                System.out.println("Waiting...");
                Socket cSock = welcomeSocket.accept();
                System.out.println("Accepted connection : " + cSock);

                Server a = new Server();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




