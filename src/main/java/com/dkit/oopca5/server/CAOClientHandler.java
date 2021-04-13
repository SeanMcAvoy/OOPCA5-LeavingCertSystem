package com.dkit.oopca5.server;

/*
The CAOClientHandler will run as a thread. It should listen for messages from the Client and respond to them.There should be one CAOClientHandler per Client.
 */

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Student;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;

import static java.lang.Integer.parseInt;

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
        CourseDaoInterface ICourseDAO = new MySqlCourseDAO();
        StudentDaoInterface IStudentDAO = new MySqlStudentDAO();
        CourseChoiceDaoInterface ICourseChoiceDAO = new MySqlCourseChoice();

        //write code logic here
        String message = "", response;

        try
        {
            while ((message = socketReader.readLine()) != null)
            {
                String [] components = message.split(CAOService.BREAKING_CHARACTER);
                System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);
                if(components[0].equals(CAOService.REGISTER_COMMAND))
                {
                    int caoNumber = parseInt(components[1]);
                    String date = components[2];
                    String password = components[3];
                    Student s = new Student(caoNumber,date,password);
                    try{
                        if(!IStudentDAO.isRegistered(s))
                        {
                            if(IStudentDAO.registerStudent(s))
                            {
                                response = CAOService.SUCCESSFUL_REGISTER;
                            }
                            else{
                                response = CAOService.FAILED_REGISTER;
                            }
                        }else{
                            response = CAOService.CAO_Number_Duplicate;
                        }

                        socketWriter.println(response);
                    }catch (DaoException e)
                     {
                         e.printStackTrace();
                     }
                }
                if(components[0].equals(CAOService.LOGIN_COMMAND))
                {
                    int caoNumber = parseInt(components[1]);
                    String date = components[2];
                    String password = components[3];
                    Student s = new Student(caoNumber,date,password);
                    try{
                        if(IStudentDAO.login(s))
                        {
                           response = CAOService.SUCCESSFUL_LOGIN;
                        }else{
                            response = CAOService.FAILED_LOGIN;
                        }
                        socketWriter.println(response);
                    }catch (DaoException e)
                    {
                        e.printStackTrace();
                    }

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
