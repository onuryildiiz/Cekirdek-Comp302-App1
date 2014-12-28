


package Core;


import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

import javax.management.modelmbean.XMLParseException;


public class SaveGame {
	
	public static String filename = "data/";

	
	public static void write(XMLObject x) throws Exception{
		XMLEncoder encoder =
		           new XMLEncoder(
		              new BufferedOutputStream(
		                new FileOutputStream(filename+x.getGameName()+".xml")));
		        encoder.writeObject(x);
		        encoder.close();
	}
	
	public static XMLObject read(String gamename) throws Exception {
		
		try {
			XMLDecoder decoder =
		            new XMLDecoder(new BufferedInputStream(
		                new FileInputStream(filename+gamename+".xml")));
		        XMLObject x = (XMLObject)decoder.readObject();
		        decoder.close();
		        
		        
		        	return x;
		} catch(Exception e){
			throw new XMLParseException();
		}
		
        
        
        
    }
	
	public static boolean isFileNameExists(String gameName){
		
		String path = filename+gameName+".xml";
		
		File f = new File(path);
		if(f.exists() && !f.isDirectory()) { return true; }
		return false;
		
	}
	

	
	
	
	/*
	public static void main(String argv[]) {
		 
		  try {
	 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(SidePanel.);
			doc.appendChild(rootElement);
	 
			// staff elements
			Element staff = doc.createElement("Staff");
			rootElement.appendChild(staff);
	 
			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			staff.setAttributeNode(attr);
	 
			// shorten way
			// staff.setAttribute("id", "1");
	 
			// firstname elements
			Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode("yong"));
			staff.appendChild(firstname);
	 
			// lastname elements
			Element lastname = doc.createElement("lastname");
			lastname.appendChild(doc.createTextNode("mook kim"));
			staff.appendChild(lastname);
	 
			// nickname elements
			Element nickname = doc.createElement("nickname");
			nickname.appendChild(doc.createTextNode("mkyong"));
			staff.appendChild(nickname);
	 
			// salary elements
			Element salary = doc.createElement("salary");
			salary.appendChild(doc.createTextNode("100000"));
			staff.appendChild(salary);
	 
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\file.xml"));
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
			System.out.println("File saved!");
	 
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		}
	
	*/
	
	  
}


