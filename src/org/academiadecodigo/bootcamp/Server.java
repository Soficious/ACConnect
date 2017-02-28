package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ricardo on 27-02-2017.
 */
public class Server {

    private int port = 8080;

    private ServerSocket serverSocket = null;


    Company company = new Company("logicalis");


    private void listen (int port){
        try {
            serverSocket = new ServerSocket(port);
            serve(serverSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serve(ServerSocket serverSocket) {
        while (true){
            try {
                Socket clientSocket = serverSocket.accept();
                dispatch(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void dispatch(Socket clientSocket) {
        try {


            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
