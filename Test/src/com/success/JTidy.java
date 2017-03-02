package com.success;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.tidy.Node;
import org.w3c.tidy.Tidy;

public class JTidy {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Tidy tidy = new Tidy();
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(File.createTempFile("dfdsf", ".txt")));
		String input = "<html></html> &^^<>,,<sdd";
//		ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes("UTF-8"));
		File f = new File("C:\\Tamil\\WinSCP_Docs\\file_orb_live_1453401355911_1150_1");
		FileInputStream bis = new FileInputStream(f);
		Node node =  tidy.parse(bis, bos);
		System.out.println("dfdsf");
	}
}
