package org.academiadecodigo.bootcamp.test;

import org.academiadecodigo.bootcamp.client.Cadet;
import org.academiadecodigo.bootcamp.client.Company;

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
    static String type;
    String host;
    int port;
    Socket client = null;


    public static void main(String[] args) {

        User user = new User();
        user.UserConstrutor();
        Thread userThread = new Thread(user);
        userThread.start();

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

    Cadet cadet = new Cadet();
    Company company = new Company();

    @Override
    public void run() {

        PrintWriter out = null;

        BufferedReader in = null;

        try {

            out = new PrintWriter(client.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String message = type;

            while (!message.equals("/close")) {

                out.println(message);
                message = message();
                out.println(message);
            }


            //SIMPLE LOGIN VERIFICATION
            if (type.toUpperCase().equals(Cadet.CadetInfo.values()) || type.toUpperCase().equals(Company.CompanyInfo.values())) {
                //proceed with next step...not sure what to copy in
            } else {
                System.out.println("Invalid login, please try again.");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
                out.close();
                client.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    /**
     * Class Construtor
     * receives a name
     * receives a type
     */
    public void UserConstrutor() {


        host = "localhost";
        port = 9999;
        System.out.println("What is your name? ");
        name = question();
        System.out.println("Are you a cadet or company");
        type = question();

        try {
            client = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}