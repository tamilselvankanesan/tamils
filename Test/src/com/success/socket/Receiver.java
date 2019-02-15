package com.success.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Receiver {

  private void get() {
    int counter = 0;
    while (counter < 5) {
      try {
        Socket client = new Socket("localhost", 8888);
        System.out.println("client - after creating socket client ... ");

        OutputStream out = client.getOutputStream();
        DataOutputStream dataOs = new DataOutputStream(out);
        System.out.println("client - before writing from client ... ");
        dataOs.writeUTF("Hello from client. counter " + counter);
        System.out.println("client - after writing from client ... ");

        System.out.println("client - before receiving from server... ");
        DataInputStream dataIn = new DataInputStream(client.getInputStream());
        System.out.println("client - Received from server .. " + dataIn.readUTF());

        client.close();
        Thread.sleep(5000);
        counter++;
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {

    for (int i = 0; i < 4; i++) {
      new Thread(() -> new Receiver().get(), "Thread "+i).start();
    }
  }

}
