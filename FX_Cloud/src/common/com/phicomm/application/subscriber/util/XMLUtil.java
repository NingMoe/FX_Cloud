package com.phicomm.application.subscriber.util;



import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLUtil {
	private static Document versionDocument;
	private static Document mailDocument;

	public synchronized static Document getVersionDocument(){
		if(versionDocument!=null) return versionDocument;
		try {
			SAXReader reader = new SAXReader();
			versionDocument = reader.read(XMLUtil.class.getClassLoader()
					.getResourceAsStream("/version.xml"));
			return versionDocument;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized static Document getMailDocument(){
		if(mailDocument!=null) return mailDocument;
		try {
			SAXReader reader = new SAXReader();
			mailDocument = reader.read(XMLUtil.class.getClassLoader()
					.getResourceAsStream("/mail.xml"));
			return mailDocument;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
