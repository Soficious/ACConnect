package org.academiadecodigo.bootcamp.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ricardo on 27-02-2017.
 */

public class ServerTest implements Runnable {

    public static void main(String[] args) {

        ServerTest serverTest = new ServerTest();

        Thread thread = new Thread(serverTest);

        thread.start();

    }


    private static String message() {

        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    private static int getPort() {

        Scanner reader = new Scanner(System.in);
        System.out.print("Port? ");
        return reader.nextInt();
    }

    @Override
    public void run() {

        int port = getPort();
        PrintWriter out = null;
        BufferedReader in = null;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(port);

            clientSocket = serverSocket.accept();

            out = new PrintWriter(clientSocket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("estou a escuta");

            String message = in.readLine();

            while (!message.equals("/close")) {

                if (message.equals("cadet")) {
                    message = in.readLine();


                    if (message.equals("next")) {
                        out.println(nextCompany());

                    }
                    if (message.equals("moreinfo")) {
                        out.println(moreInfo());

                    }
                    if (message.equals("match")) {
                        out.println(match());


                    }
                }

                System.out.println("estou aqui 2");
                message = in.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {

                out.close();
                in.close();
                clientSocket.close();
                serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private String match() {

        String match = new String("you choose match..here's next candidate");
        return match;

    }

    private String moreInfo() {
        String moreInfo = new String("here s more info on company");
        return moreInfo;
    }

    private String nextCompany() {

        String moreInfo = new String("here is your next company");
        return moreInfo;
    }

}




