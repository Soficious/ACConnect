package org.academiadecodigo.bootcamp.test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.academiadecodigo.bootcamp.client.Cadet;
import org.academiadecodigo.bootcamp.client.Company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
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
            System.out.println(cadets);
            createCompanyList();
            System.out.println(companies);

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

        public void cadetLogin() {
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

        public void companyLogin() {
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

        public void cadetsList() {

            try {
                //out.println("HERE'S A CANDIDATE ...choose MOREINFO, MATCH OR NEXT");
                showCadetsPitch();
                while (true) {

                    CadetMessage = in.readLine();

                    switch (CadetMessage) {
                        case ("next"):
                            // out.println("HERE'S ONE CADET ....CHOOSE,NEXT OR MORE INFO");
                            showCadetsPitch();
                            break;

                        case ("more info"):
                            showCadetsFile();
                            out.println("CHOOSE NEXT OR MATCH");

                            CadetMessage = in.readLine();

                            while (CadetMessage.equals("more info")) {
                                System.out.println("no while do moreinfo");
                                out.println("choose next or match");
                                CadetMessage = in.readLine();
                            }
                            if (CadetMessage.equals("next")) {
                                // out.println("HERE'S ONE CADET ....CHOOSE,NEXT OR MORE INFO");
                                showCadetsPitch();
                                break;
                            } else {
                                companyMatchList.add(String.valueOf(Cadet.CadetInfo.values()[currentCadet]));
                                // out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ONE CADET...CHOOSE, NEXT OR MORE INFO");
                                showCadetsPitch();
                                break;
                            }

                        case ("match"):
                            companyMatchList.add(String.valueOf(Cadet.CadetInfo.values()[currentCadet]));
                            out.println(companyMatchList);
                            // out.println("YOU CHOOSE MATCH...NOW WAIT...HERE'S ONE CADET...CHOOSE, NEXT OR MOREINFO");
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

                // out.println("HERE'S A COMPANY...choose MORE INFO, MATCH OR NEXT");
                showCompanysMoto();


                while (true) {

                    CompanyMessage = in.readLine();

                    switch (CompanyMessage) {
                        case ("next"):
                            //out.println("HERE'S ONE COMPANY...CHOOSE, NEXT OR MORE INFO");
                            showCompanysMoto();
                            break;

                        case ("more info"):


                            showCompanyFile();
                            // out.println("CHOOSE NEXT OR MATCH");
                            System.out.println("escuta antes do while do moreinfo");
                            //  CompanyMessage = in.readLine();

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
                    }
                    if (CompanyMessage.equals("/close")) {

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
//                    System.out.println("i'm here");
//                    out.println("press enter to continue");
//                    message = in.readLine();

                } else if (message.equals("Company")) {
                    companyLogin();

                } else {
                    out.println("Please try again. Are you a Cadet or a Company?");
                }
                System.out.println("listening");
                message = in.readLine();

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

//    public enum CompanyInfo {
//        GOOGLE("Are you feeling lucky?", "resources/company/logicalis.pdf"),
//        LOGICALIS("We're Dutch and super cool!", "resources/company/logicalis.pdf"),
//        MICROSOFT("Open the Windows!", "resources/company/logicalis.pdf"),
//        ALTRAN("America first, France second!", "resources/company/logicalis.pdf"),
//        ACADEMIA("Stay here and Padawan with us!", "resources/company/logicalis.pdf"),
//        READINESSIT("Get ready to travel!", "resources/company/logicalis.pdf");
//
//        public final String description;
//        public final File file;
//
//        CompanyInfo(String desc, String path) {
//            this.description = desc;
//            file = new File(path);
//        }
//
//
//    }
//
//    public enum CadetInfo {
//
//
//        RENATO("I'm always the loudest person in the room!", "resources/codecadet/Renato.pdf"),
//        SOFIA("Eh pa, honestamente não concordo!", "resources/codecadet/Sofia.pdf"),
//        RICARDO("I have no battery!", "resources/codecadet/Ricardo.pdf"),
//        JESSE("I fucking hate Fundão!", "resources/codecadet/Jesse.pdf"),
//        PEDRO("My battery died too!", "resources/codecadet/Pedro.pdf");
//
//        public final String saying;
//        public final File filepath;
//
//        CadetInfo(String pitch, String cfile) {
//            this.saying = pitch;
//            filepath = new File(cfile);
//        }
//
//
//    }









