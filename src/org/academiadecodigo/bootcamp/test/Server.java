package org.academiadecodigo.bootcamp.test;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.academiadecodigo.bootcamp.client.Cadet;
import org.academiadecodigo.bootcamp.client.Company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ricardo on 27-02-2017.
 */

public class Server {

    private CopyOnWriteArrayList<Cadet> cadetTypelist;
    private CopyOnWriteArrayList<Company> companyTypelist;

    public Server() {

        cadetTypelist = new CopyOnWriteArrayList<>();
        companyTypelist = new CopyOnWriteArrayList<>();

    }

    private static int getPort() {

        Scanner reader = new Scanner(System.in);
        System.out.print("Port? ");
        return reader.nextInt();
    }

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        boolean socketListener = true;

        try {
            serverSocket = new ServerSocket(getPort());

            while (socketListener) {

                Socket clientSocket = new Socket();
                clientSocket = serverSocket.accept();

                Clienthandler clienthandler = new Clienthandler(clientSocket);

                Thread thread = new Thread(clienthandler);

                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Clienthandler implements Runnable {

        private int currentCompany = -1;
        private int currentCadet = -1;
        private PrintWriter out = null;
        BufferedReader in = null;
        private Socket socket = null;
        String CompanyMessage;
        String CadetMessage;

        public Clienthandler(Socket socket) {
            this.socket = socket;
        }

        public void showCompanysMoto() {

            currentCompany++;
            if (currentCompany == Company.CompanyInfo.values().length) {
                currentCompany = 0;
            }
            for (int i = currentCompany; i < Company.CompanyInfo.values().length; i++) {

                out.println(Company.CompanyInfo.values()[i].description);
                break;

            }

        }

        public void showCadetsPitch() {

            currentCadet++;
            if (currentCadet == Cadet.CadetInfo.values().length) {
                currentCadet = 0;
            }
            for (int i = currentCadet; i < Cadet.CadetInfo.values().length; i++) {

                out.println(Cadet.CadetInfo.values()[i].saying);
                break;

            }

        }

        public void showCompanyFile() {

            for (int i = currentCompany; i < Company.CompanyInfo.values().length; i++) {
                out.println(Company.CompanyInfo.values()[i].file);
                break;
            }
        }

        public void showCadetsFile() {

            for (int i = currentCadet; i < Cadet.CadetInfo.values().length; i++) {
                out.println(Cadet.CadetInfo.values()[i].filepath);
                break;
            }
        }

        public void cadetsList() {

            try {

                showCadetsPitch();

                while (true) {

                    CadetMessage = in.readLine();

                    switch (CadetMessage) {
                        case ("next"):
                            showCadetsPitch();
                            break;

                        case ("moreinfo"):
                            showCadetsFile();
                            CadetMessage = in.readLine();

                            while (CadetMessage.equals("moreinfo") && !CadetMessage.equals("next")) {
                                out.println("choose next or match");
                                CadetMessage = in.readLine();
                            }

                            if (CadetMessage.equals("match")) {
                                showCadetsPitch();
                                break;

                            }
                                showCadetsPitch();
                                break;


                        case ("match"):
                            showCadetsPitch();
                            break;

                    }
                    if (CadetMessage.equals("/close")) {

                        out.close();
                        in.close();
                        socket.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    socket.close();
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void companyList() {

            try {

                showCompanysMoto();

                while (true) {

                    CompanyMessage = in.readLine();

                    switch (CompanyMessage) {
                        case ("next"):
                            showCompanysMoto();
                            break;

                        case ("moreinfo"):
                            showCompanyFile();
                            CompanyMessage = in.readLine();

                            while (!CompanyMessage.equals("match") && !CompanyMessage.equals("next")) {
                                out.println("choose next or match");
                                CompanyMessage = in.readLine();
                            }

                            if (CompanyMessage.equals("match")) {
                                showCompanysMoto();
                                break;
                            }
                            showCompanysMoto();
                            break;


                        case ("match"):
                            showCompanysMoto();
                            break;
                    }
                    if (CompanyMessage.equals("/close")) {

                        out.close();
                        in.close();
                        socket.close();
                        break;
                    }
                }
            } catch (
                    IOException e)

            {
                e.printStackTrace();
            } finally

            {

                try {
                    socket.close();
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


        @Override
        public void run() {

            try {

                System.out.println("enter the run");

                out = new PrintWriter(socket.getOutputStream(), true);

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("What is your name");
                String message = in.readLine();
                //TODO METHOD LOGIN VERIFICATION OR ADD NEWONE
                out.println("What are you?");
                System.out.println("listening");
                message = in.readLine();

                while (!message.equals("cadet") && !message.equals("company")) {
                    out.println("wrong command: write company or cadet");
                    message = in.readLine();
                }

                if (message.equals("cadet")) {
                    System.out.println("in if cadet of run");
                    companyList();
                } else {
                    System.out.println("in if company of run");
                    cadetsList();
                }


            } catch (
                    IOException e)

            {
                e.printStackTrace();
            }
        }


    }

}







