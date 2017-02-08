package cn.com.zhixin;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseXml {
	
	  private static Document document;
	  
	    public static void init() {
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory
	                    .newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            document = builder.newDocument();
	        } catch (ParserConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	    public static void createXml(String fileName) {
	    	init();
	        Element root = document.createElement("configs");   //创建根节点
	        document.appendChild(root);
	        
	        /* 创建一个完成的节点，start */
	        Element item = document.createElement("item");
	        Attr name = document.createAttribute("name");
	        name.setValue("wifi_on");
	        item.setAttributeNode(name);
	        item.appendChild(document.createTextNode("1"));
	        root.appendChild(item);
	        //item.appendChild(document.createTextNode("wangchenyang"));
	        /*  end  */
	        
	        Element item1 = document.createElement("item");
	        Attr name1 = document.createAttribute("name");
	        item1.setAttribute("name", "eth_on");    //增加属性的另一种方法
	        item1.appendChild(document.createTextNode("1"));
	        root.appendChild(item1);
	        
	        //将DOM对象document写入到xml文件中
	        TransformerFactory tf = TransformerFactory.newInstance();
	        try {
	            Transformer transformer = tf.newTransformer();
	            DOMSource source = new DOMSource(document);
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
	            StreamResult result = new StreamResult(pw);
	            transformer.transform(source, result);     //关键转换
	            System.out.println("生成XML文件成功!");
	            System.out.println(root.toString());
	        } catch (TransformerConfigurationException e) {
	            System.out.println(e.getMessage());
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (TransformerException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	    public void parserXml(String fileName) {
	        try {
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document document = db.parse(fileName);
	             
	            NodeList employees = document.getChildNodes();
	            for (int i = 0; i < employees.getLength(); i++) {
	                Node employee = employees.item(i);
	                NodeList employeeInfo = employee.getChildNodes();
	                for (int j = 0; j < employeeInfo.getLength(); j++) {
	                    Node node = employeeInfo.item(j);
	                    NodeList employeeMeta = node.getChildNodes();
	                    for (int k = 0; k < employeeMeta.getLength(); k++) {
	                        System.out.println(employeeMeta.item(k).getNodeName()
	                                + ":" + employeeMeta.item(k).getTextContent());
	                    }
	                }
	            }
	            System.out.println("解析完毕");
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } catch (ParserConfigurationException e) {
	            System.out.println(e.getMessage());
	        } catch (SAXException e) {
	            System.out.println(e.getMessage());
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    public static void main(String args[]){
	    	
	    	
	    	ParseXml.createXml("test2.xml");
	    	
	    }
	    
}
