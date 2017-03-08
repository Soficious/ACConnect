package org.academiadecodigo.bootcamp.test;

import java.util.LinkedList;
import java.util.List;

import org.academiadecodigo.bootcamp.client.Cadet;
import org.academiadecodigo.bootcamp.client.Company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;


/**
 * Created by ricardo on 27-02-2017.
 */

public class Server {

    final static List<String> cadets = new LinkedList<>();
    final static List<String> companies = new LinkedList<>();

    static void createCadetsList() {
        for (int i = 0; i < Cadet.CadetInfo.values().length; i++) {
            cadets.add(String.valueOf(Cadet.CadetInfo.values()[i]));
        }
    }

    static void createCompanyList() {
        for (int i = 0; i < Company.CompanyInfo.values().length; i++) {
            companies.add(String.valueOf(Company.CompanyInfo.values()[i]));
        }
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
            serverSocket = new ServerSocket(9999);
            createCadetsList();
            createCompanyList();


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

    static List<String> companyMatchList = new LinkedList<>();
    static List<String> cadetMatchList = new LinkedList<>();
    static List<String> perfectMatchList = new LinkedList<>();


    private static class Clienthandler implements Runnable {

        private int currentCompany = -1;
        private int currentCadet = -1;
        private PrintWriter out = null;
        private BufferedReader in = null;
        private Socket socket = null;
        private String userIn;


        public Clienthandler(Socket socket) {
            this.socket = socket;
        }

        public synchronized boolean cadetLogin() throws IOException {

            out.println("What is your name?");
            try {
                userIn = in.readLine().toUpperCase();

                for (String cadet : cadets) {

                    if (cadet.equals(userIn)) {
                        out.println("successful login");
                        System.out.printf("retorna true");
                        return true;
                    }
                }
                System.out.printf("sai do for");
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("Please try again");
            System.out.println("retornar falso");
            return false;
        }

        public synchronized boolean companyLogin() throws IOException {

            out.println("What is your name?");
            try {
                userIn = in.readLine().toUpperCase();

                for (String company : companies) {

                    if (company.equals(userIn)) {
                        out.println("successful login");
                        System.out.printf("retorna true");
                        return true;
                    }
                }
                System.out.printf("sai do for");
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("Please try again");
            System.out.println("retornar falso");
            return false;
        }

        public synchronized void showCompanysMoto() {

            currentCompany++;
            if (currentCompany == Company.CompanyInfo.values().length) {
                currentCompany = -1;
            }
            for (int i = currentCompany; i < Company.CompanyInfo.values().length; i++) {

                out.println(Company.CompanyInfo.values()[i].description);
                break;
            }
        }

        public synchronized void showCadetsPitch() {

            currentCadet++;
            if (currentCadet == Cadet.CadetInfo.values().length) {
                currentCadet = -1;
            }
            for (int i = currentCadet; i < Cadet.CadetInfo.values().length; i++) {

                out.println(Cadet.CadetInfo.values()[i].saying);
                break;

            }

        }


        public synchronized void showCompanyFile() {

            try {

                File pdfFile = new File(String.valueOf(Company.CompanyInfo.values()[currentCompany].file));
                if (pdfFile.exists()) {

                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(pdfFile);
                    } else {
                        System.out.println("Awt Desktop is not supported!");
                    }

                } else {
                    System.out.println("File does not exist!");
                }

                System.out.println("Done");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }


        public synchronized void showCadetsFile() {
            try {


                File pdfFile = new File(String.valueOf(Cadet.CadetInfo.values()[currentCadet].filepath));
                if (pdfFile.exists()) {

                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(pdfFile);
                    } else {
                        System.out.println("Awt Desktop is not supported!");
                    }

                } else {
                    System.out.println("File is not exists!");
                }

                System.out.println("Done cadets file");


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        public synchronized void cadetsList() throws IOException {
            try {
                //out.println("HERE'S A CANDIDATE ...choose MOREINFO, MATCH OR NEXT");
                showCadetsPitch();
                while (true) {
                    userIn = in.readLine();
                    switch (userIn) {
                        case ("next"):
                            showCadetsPitch();
                            break;
                        case ("more info"):
                            showCadetsFile();
                            out.println("Choose next or match");
                            userIn = in.readLine();

                            while (!userIn.equals("match") && !userIn.equals("next")) {
                                out.println("Choose next or match");
                                userIn = in.readLine();
                            }
                            if (userIn.equals("next")) {
                                // out.println("HERE'S ONE CADET ....CHOOSE,NEXT OR MORE INFO");
                                showCadetsPitch();
                                break;
                            } else {
                                companyMatchList.add(String.valueOf(Cadet.CadetInfo.values()[currentCadet]));
                                // out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ONE CADET...CHOOSE, NEXT OR MORE INFO");
                            }
                            if (userIn.equals("match")) {
                                showCadetsPitch();
                                break;
                            }
                            showCadetsPitch();
                            break;
                        case ("match"):

                            companyMatchList.add(String.valueOf(Cadet.CadetInfo.values()[currentCadet]));
                            out.println(companyMatchList);
                            // out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ONE CADET...CHOOSE, NEXT OR MOREINFO");

                            showCadetsPitch();
                            break;
                        default:
                            out.println("Wrong command: please choose next match or moreinfo");
                            break;

                    }
                    if (userIn.equals("/close")) {

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


        public synchronized void companyList() throws IOException {

            try {

                showCompanysMoto();

                while (true) {

                    userIn = in.readLine();

                    switch (userIn) {
                        case ("next"):
                            showCompanysMoto();
                            break;


                        case ("more info"):


                            showCompanyFile();
                            out.print("choose next or match");
                            userIn = in.readLine();


                            while (userIn.equals("more info")) {
                                System.out.println("no while do moreinfo");
                                out.println("choose next or match");
                                userIn = in.readLine();
                            }
                            if (userIn.equals("next")) {
                                showCompanysMoto();
                                break;
                            } else {
                                //out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ANOTHER COMPANY...CHOOSE, NEXT OR MORE INFO");
                                cadetMatchList.add(String.valueOf(Company.CompanyInfo.values()[currentCompany]));

                                while (!userIn.equals("match") && !userIn.equals("next")) {
                                    out.println("choose next or match");
                                    userIn = in.readLine();
                                }

                                if (userIn.equals("match")) {

                                    showCompanysMoto();
                                    break;
                                }
                                showCompanysMoto();
                                break;

                            }
                        case ("match"):

                            System.out.println("dentro do match");
                            cadetMatchList.add(String.valueOf(Company.CompanyInfo.values()[currentCompany]));
                            out.println(cadetMatchList);
                            //out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ANOTHER COMPANY...CHOOSE, NEXT OR MORE INFO");

                            showCompanysMoto();
                            break;
                        default:
                            out.println("wrong command please choose next, match or more info");


                            if (userIn.equals("/close")) {

                                out.close();
                                in.close();
                                socket.close();
                                break;

                            }
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


        @Override
        public void run() {


            try {

                System.out.println("enter the run");

                out = new PrintWriter(socket.getOutputStream(), true);

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("Are you a Cadet or a Company?");
                String message = in.readLine();
//                if (message.equals("Cadet")) {
//                    cadetLogin();
//                    companyList();
//                } else if (message.equals("Company")) {
//                    companyLogin();
//                    cadetsList();
//                } else {
//                    out.println("Please try again.");
//                }


                while (!message.equals("Cadet") && !message.equals("Company")) {
                    out.println("wrong command: write company or cadet");
                    message = in.readLine();
                }
                System.out.println(message);

                if (message.equals("Cadet")) {
                    System.out.println("in if cadet of run");
                    while (!cadetLogin()) {
                        cadetLogin();
                    }

                    companyList();
                    return;
                }if (message.equals("Company")) {
                    System.out.println("in if company of run");
                    while (!companyLogin()) {
                        companyLogin();
                    }
                    System.out.println("estou aqui");
                    cadetsList();

                }

            } catch (IOException e1)

            {
                e1.printStackTrace();
            }

        }
    }
}






