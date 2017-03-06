package org.academiadecodigo.bootcamp.test;

import org.academiadecodigo.bootcamp.client.Cadet;
import org.academiadecodigo.bootcamp.client.Company;

import java.awt.*;
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

    /*
      private void pushCadettoList(String name) {

          userTypelist.add(new Cadet(name));
      }

      private void pushCompanytoList(String name) {
          companyTypelist.add(new Company (name));
      }
  */
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
                            out.println("Choose next or match");
                            CadetMessage = in.readLine();

                            while (!CadetMessage.equals("match") && !CadetMessage.equals("next")) {
                                out.println("Choose next or match");
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

        public void matchCadet() {

            HashMap<Integer, String> mapCadet = new HashMap<Integer, String>();
            mapCadet.put(1, String.valueOf(currentCadet));

            mapCadet.put(1, "one");
            mapCadet.put(2, "two");
            Iterator<Integer> keyIterator = mapCadet.keySet().iterator();

            while (keyIterator.hasNext()) {
                Integer key = keyIterator.next();
                System.out.println();
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
                            out.print("choose next or match");
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
                        default:
                            out.println("wrong command please choose next, match or more info");

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

                }
                System.out.println("in if company of run");
                cadetsList();


            } catch (
                    IOException e)

            {
                e.printStackTrace();
            }
        }


    }
}









