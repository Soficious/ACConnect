package org.academiadecodigo.bootcamp.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Renato on 28/02/17.
 */
public class User implements Runnable {

    String name;
    String type;
    String host;
    int port;
    Socket socket = null;

    public User (Socket socket){
        this.socket = socket;
    }

    private static String message() {

        Scanner reader = new Scanner(System.in);
        return reader.nextLine();

    }

    private static String question() {

        Scanner reader = new Scanner(System.in);
        return reader.nextLine();

    }

    private static int port(String portQuestion) {

        Scanner reader = new Scanner(System.in);
        System.out.print(portQuestion);
        return reader.nextInt();

    }

    public void userConstrutor() {

        host = "localhost";
        port = 9999;
        System.out.println("What is your name? ");
        name = question();
        System.out.println("Are you a cadet or company");
        type = question();
    }

    @Override
    public void run() {

        PrintWriter out = null;

        BufferedReader in = null;

        try {

            out = new PrintWriter(socket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = type;

            while (!message.equals("/close")) {

                out.println(message);
                message = message();
                out.println(message);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    public static void main(String[] args) {

        User user;

    }


}