package cmecf.success;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileUtility {
  public static void logMsgToFile(String msg, String fileName) {
    final File f;
    try {
      // if file does not exist, create it.
      f = new File(fileName);
      if (!f.exists()) {
        f.createNewFile();
      }
    }
    catch (final IOException e) {
      e.printStackTrace();
      return;
    }
    try (FileWriter fout = new FileWriter(fileName, true);) {
      fout.write(new Date() + ": " + msg + "\n");
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
  }
public static void main(String[] args) {
  logMsgToFile("Hello world "+new Date(), "/home/ecf_web/cmecf.log");
}
}
