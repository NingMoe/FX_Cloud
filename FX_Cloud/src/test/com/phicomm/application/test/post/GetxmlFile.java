package com.phicomm.application.test.post;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class GetxmlFile {

	/**
	 * xml转字符串
	 */
	public static String xmlConvertString(Document doc) {
 
		String xmlStr = null;
		try {
			// XML转字符串
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty("encoding", "utf-8");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			t.transform(new DOMSource(doc), new StreamResult(bos));
			xmlStr = bos.toString("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return xmlStr;
	}
	
	/**
	 * string转xml
	 */
	public static Document stringConvertXML(String data, String code) {
 
		StringBuilder sXML = new StringBuilder(code);
		sXML.append(data);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			InputStream is = new ByteArrayInputStream(sXML.toString().getBytes(
					"utf-8"));
			doc = dbf.newDocumentBuilder().parse(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	
}
