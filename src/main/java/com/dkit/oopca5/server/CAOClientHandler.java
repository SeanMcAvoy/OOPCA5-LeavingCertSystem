package com.dkit.oopca5.server;

/*
The CAOClientHandler will run as a thread. It should listen for messages from the Client and respond to them.There should be one CAOClientHandler per Client.
 */

import com.dkit.oopca5.core.CAOService;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

public class CAOClientHandler implements Runnable
{
    //String[] components = message.split(CAOService.BREAKING_CHARACTER);
    BufferedReader socketReader;
    PrintWriter socketWriter;
    Socket socket;
    int clientNumber;

    public CAOClientHandler(Socket clientSocket, int clientNumber)
    {
        try
        {
            InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
            this.socketReader = new BufferedReader(isReader);

            OutputStream os = clientSocket.getOutputStream();
            this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

            this.clientNumber = clientNumber;  // ID number that we are assigning to this client

            this.socket = clientSocket;  // store socket ref for closing

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        //write code logic here
        String message ="";
        String [] components = message.split(CAOService.BREAKING_CHARACTER);
        try
        {
            while ((message = socketReader.readLine()) != null)
            {
                System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);
                if(components[0].equals(CAOService.REGISTER_COMMAND))
                {
                    System.out.println("reg");

                }


            }

            socket.close();

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");

    }
}
