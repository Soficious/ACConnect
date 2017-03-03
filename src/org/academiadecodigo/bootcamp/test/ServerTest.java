package org.academiadecodigo.bootcamp.test;

import org.academiadecodigo.bootcamp.client.Cadet;
import org.academiadecodigo.bootcamp.client.Company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ricardo on 27-02-2017.
 */

public class ServerTest implements Runnable {

    int currentCompany = -1;
    PrintWriter out = null;

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

        BufferedReader in = null;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;


        try {
            serverSocket = new ServerSocket(port);


            clientSocket = serverSocket.accept();

            out = new PrintWriter(clientSocket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("who are you");

            while (true) {
                System.out.println("in the while");

                //SIMPLE LOGIN VERIFICATION
                //if (User.type.toUpperCase().equals(Cadet.CadetInfo.values().toString()) || User.type.toUpperCase().equals(Company.CompanyInfo.values().toString())) {


                    String message = in.readLine();

                    switch (message) {
                        case ("cadet"):
                            System.out.println("in the if cadet");
                            description();
                            break;
                        case ("next"):
                            System.out.println("in the if next");
                            description();
                            break;
                        case ("moreinfo"):
                            path();
                            break;
                        case ("match"):
                            description();
                            break;
                        case ("/close"):
                            out.close();
                            in.close();
                            clientSocket.close();
                            serverSocket.close();
                        default:
                            System.out.println("Something went terribly wrong!");
                    }

//                } else {
//                    System.out.println("Invalid login, please try again.");
//                    out.println("who are you");
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
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

    public void description() {

        currentCompany++;
        if (currentCompany == Company.CompanyInfo.values().length) {
            currentCompany = 0;
        }
        for (int i = currentCompany; i < Company.CompanyInfo.values().length; i++) {

            out.println(Company.CompanyInfo.values()[i].description);
            break;

        }
    }

    public void path() {

        for (int i = currentCompany; i < Company.CompanyInfo.values().length; i++) {
            out.println(Company.CompanyInfo.values()[i].file);
            break;
        }
    }


    public enum CompanyInfo {
        GOOGLE("Are you feeling lucky?", "resources/company/logicalis.pdf"),
        LOGICALIS("We're Dutch and super cool!", "resources/company/logicalis.pdf"),
        MICROSOFT("Open the Windows!", "resources/company/logicalis.pdf"),
        ALTRAN("America first, France second!", "resources/company/logicalis.pdf"),
        ACADEMIA("Stay here and Padawan with us!", "resources/company/logicalis.pdf"),
        READINESSIT("Get ready to travel!", "resources/company/logicalis.pdf");

        public final String description;
        public final File file;

        CompanyInfo(String desc, String path) {
            this.description = desc;
            file = new File(path);
        }


    }
}




