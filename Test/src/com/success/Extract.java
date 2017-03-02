package com.success;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Extract {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Tamil\\live_debugging\\ORB\\rules_engine\\11-1\\catalina.out"), "Cp1252"));         

      String line;
      int count = 0;
      while ((line = br.readLine()) != null) {
          if(line.contains("Job instance ID")){
            System.out.println(line);
            count ++;
            if(count %2==0){
              System.out.println("");
            }
          }
      }
      br.close();

  } catch (IOException e) {
      e.printStackTrace();
  }
  }
}
