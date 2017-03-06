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
            serverSocket = new ServerSocket(getPort());
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
        BufferedReader in = null;
        private Socket socket = null;
        String CompanyMessage;
        String CadetMessage;


        public Clienthandler(Socket socket) {
            this.socket = socket;
        }

        public void cadetLogin() throws IOException {
            out.println("What is your name?");

            String userIn = null;
            try {
                userIn = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String cadet : cadets) {
                if (!cadet.equals(userIn.toUpperCase())) {
                    out.println("please try again");
                }
                out.println("successful login - press enter to see the companies");
                companyList();
                break;
            }
        }

        public void companyLogin() throws IOException {
            out.println("What is your name?");

            String userIn = null;
            try {
                userIn = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String company : companies) {
                if (!company.equals(userIn.toUpperCase())) {
                    out.println("please try again");
                }
                out.println("successful login - press enter to see the candidates");
                cadetsList();
                break;
            }
        }

        public void showCompanysMoto() {

            currentCompany++;
            if (currentCompany == Company.CompanyInfo.values().length) {
                currentCompany = -1;
            }
            for (int i = currentCompany; i < Company.CompanyInfo.values().length; i++) {

                out.println(Company.CompanyInfo.values()[i].description);
                break;
            }
        }

        public void showCadetsPitch() {

            currentCadet++;
            if (currentCadet == Cadet.CadetInfo.values().length) {
                currentCadet = -1;
            }
            for (int i = currentCadet; i < Cadet.CadetInfo.values().length; i++) {

                out.println(Cadet.CadetInfo.values()[i].saying);
                break;

            }

        }


        public void showCompanyFile() {

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


        public void showCadetsFile() {
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

//        public void createPerfectMatchList() {
//
//            for (int i = 0; i < cadetMatchList.size(); i++) {
//                if (String.valueOf(Cadet.CadetInfo.values()[currentCadet]).equals(Cadet.CadetInfo.JESSE)) {
//                    perfectMatchList.add(String.valueOf(Cadet.CadetInfo.values()[currentCadet]));
//                }
//            }
//
//            for (int i = 0; i < companyMatchList.size(); i++) {
//                if (String.valueOf(Company.CompanyInfo.values()[currentCompany]).equals(Company.CompanyInfo.GOOGLE)) {
//                    perfectMatchList.add(String.valueOf(Company.CompanyInfo.values()[currentCompany]));
//                }
//            }
//
//        }

        public void cadetsList() throws IOException {
            try {
                //out.println("HERE'S A CANDIDATE ...choose MOREINFO, MATCH OR NEXT");
                showCadetsPitch();
                while (true) {
                    CadetMessage = in.readLine();
                    switch (CadetMessage) {
                        case ("next"):
                            showCadetsPitch();
                            break;
                        case ("more info"):
                            showCadetsFile();
                            out.println("Choose next or match");
                            CadetMessage = in.readLine();

                            while (!CadetMessage.equals("match") && !CadetMessage.equals("next")) {
                                out.println("Choose next or match");
                                CadetMessage = in.readLine();
                            }
                            if (CadetMessage.equals("next")) {
                                // out.println("HERE'S ONE CADET ....CHOOSE,NEXT OR MORE INFO");
                                showCadetsPitch();
                                break;
                            } else {
                                companyMatchList.add(String.valueOf(Cadet.CadetInfo.values()[currentCadet]));
                                // out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ONE CADET...CHOOSE, NEXT OR MORE INFO");
                            }
                            if (CadetMessage.equals("match")) {
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
                    if (CadetMessage.equals("/close")) {

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


        public void companyList() throws IOException {

            try {

                showCompanysMoto();

                while (true) {

                    CompanyMessage = in.readLine();

                    switch (CompanyMessage) {
                        case ("next"):
                            showCompanysMoto();
                            break;


                        case ("more info"):


                            showCompanyFile();
                            out.print("choose next or match");
                            CompanyMessage = in.readLine();


                            while (CompanyMessage.equals("more info")) {
                                System.out.println("no while do moreinfo");
                                out.println("choose next or match");
                                CompanyMessage = in.readLine();
                            }
                            if (CompanyMessage.equals("next")) {
                                //out.println("HERE'S ONE COMPANY...CHOOSE, NEXT OR MORE INFO");
                                showCompanysMoto();
                                break;
                            } else {
                                //out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ANOTHER COMPANY...CHOOSE, NEXT OR MORE INFO");
                                cadetMatchList.add(String.valueOf(Company.CompanyInfo.values()[currentCompany]));

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


                            if (CompanyMessage.equals("/close")) {

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
                if (message.equals("Cadet")) {
                    cadetLogin();

                } else if (message.equals("Company")) {
                    companyLogin();


                } else {
                    out.println("Please try again. Are you a Cadet or a Company?");
                }
                System.out.println("listening");
                message = in.readLine();

                if (message.equals("cadet")) {
                    System.out.println("in if cadet of run");
                    companyList();
                    System.out.println("out of the cadet run");

                }
                System.out.println("in if company of run");
                cadetsList();


//                    while (!message.equals("cadet") && !message.equals("company")) {
//                        out.println("wrong command: write company or cadet");
//                        message = in.readLine();
//                    }
//
//                    if (message.equals("cadet")) {
//                        System.out.println("in if cadet of run");
//                        companyList();
//                    } else {
//                        System.out.println("in if company of run");
//                        cadetsList();
//                    }

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}





