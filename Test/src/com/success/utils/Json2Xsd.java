package com.success.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.w3c.dom.Document;

import com.ethlo.jsons2xsd.Config;
import com.ethlo.jsons2xsd.Jsons2Xsd;
import com.ethlo.jsons2xsd.XmlUtil;

public class Json2Xsd {

	public static void main(String[] args) {
		try {
			Reader r = new InputStreamReader(new FileInputStream(new File("C:\\Users\\btamilselvan\\Desktop\\VRSPIResponse001.schema_modified.json")));
			final Config cfg = new Config.Builder().targetNamespace("http://example.com/myschema.xsd").name("array").build();
			final Document doc = Jsons2Xsd.convert(r, cfg);
			System.out.println(XmlUtil.asXmlString(doc.getDocumentElement()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
