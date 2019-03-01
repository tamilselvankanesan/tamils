package com.success.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

  private ServerSocket serverSocket;
  
  public Server() throws IOException{
    serverSocket = new ServerSocket(8888);
    System.out.println("Server - Server socket object created");
  }
  
  public void run(){
    int counter = 0;
    try {
      while(counter < 5){
        System.out.println("Server - before accepting connections");
        Socket server = serverSocket.accept();
        
        System.out.println("Server - after accepting connections");
        DataInputStream in = new DataInputStream(server.getInputStream());
        System.out.println("Server - messsage received from client-> "+in.readUTF());
        
        DataOutputStream dataOut = new DataOutputStream(server.getOutputStream());
        System.out.println("Server - before sending message to client -> "+counter);
        dataOut.writeUTF("Hello from server.. counter "+counter);
        System.out.println("Server - after sending message to client -> "+counter);
        server.close();
        System.out.println("Server - server closed");
        counter ++;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args){
    new Thread(()->{
      try {
        new Server().run();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

}
