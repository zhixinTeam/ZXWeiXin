package com.zhixin.tools;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParseXml {

	
	public static NodeList parsexml(String paramxml){
		NodeList nodelist =null;
		try {
			StringReader read = new StringReader(paramxml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(source);
			nodelist = document.getChildNodes();
			
		} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
		System.out.println(e.getMessage());
		} catch (SAXException e) {
		System.out.println(e.getMessage());
		} catch (IOException e) {
		System.out.println(e.getMessage());
		}
		return nodelist;
	}
	
	
	public static Document toDocment(String paramxml){
		Document document =null;
		try {
			StringReader read = new StringReader(paramxml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(source);
			
		} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
		System.out.println(e.getMessage());
		} catch (SAXException e) {
		System.out.println(e.getMessage());
		} catch (IOException e) {
		System.out.println(e.getMessage());
		}
		return document;
	}
	
	
	public static Document newDocment(){
		Document document =null;
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        try {
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            document = builder.newDocument();
	            document.setXmlVersion("1.0");
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return document;
	}
	
	public static Node selectSingleNode(String express, Object source) {//查找节点，并返回第一个符合条件节点
        Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        
        return result;
    }
	
	
	 public static NodeList selectNodes(String express, Object source) {//查找节点，返回符合条件的节点集。
	        NodeList result=null;
	        XPathFactory xpathFactory=XPathFactory.newInstance();
	        XPath xpath=xpathFactory.newXPath();
	        try {
	            result=(NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
	        } catch (XPathExpressionException e) {
	            e.printStackTrace();
	        }
	        
	        return result;
	    }
	

	
	public static void output(Node node) {//将node的XML字符串输出到控制台
        TransformerFactory transFactory=TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("encoding", "utf-8");
            transformer.setOutputProperty("indent", "yes");

            DOMSource source=new DOMSource();
            source.setNode(node);
            StreamResult result=new StreamResult();
            result.setOutputStream(System.out);
            
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }   
    }
	
}
