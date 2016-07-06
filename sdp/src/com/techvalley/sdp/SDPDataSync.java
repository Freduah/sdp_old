package com.techvalley.sdp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.techvalley.sdp.data.SDPDataSyncSQL;
import com.techvalley.sdp.data.SDPSmsResponse;


@WebServlet("/sdpdatasync")
public class SDPDataSync extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SDPDataSyncSQL sdpDataSyncSQL = new SDPDataSyncSQL();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;
		try {
			  builder = factory.newDocumentBuilder();
			  Document xmlDoc = builder.parse(new InputSource(request.getInputStream())); 
			  xmlDoc.getDocumentElement().normalize();	
			 
			  Element rootElement = xmlDoc.getDocumentElement();
			  			  
			  String msisdn = rootElement.getElementsByTagName("ID").item(0).getTextContent();
			  String spID =  rootElement.getElementsByTagName("ns1:spID").item(0).getTextContent();
			  String productID =  rootElement.getElementsByTagName("ns1:productID").item(0).getTextContent();
			  String serviceID =  rootElement.getElementsByTagName("ns1:serviceID").item(0).getTextContent();
			  String serviceList = rootElement.getElementsByTagName("ns1:serviceList").item(0).getTextContent(); 
			  String updateType = rootElement.getElementsByTagName("ns1:updateType").item(0).getTextContent();
			  String updateTime = rootElement.getElementsByTagName("ns1:updateTime").item(0).getTextContent(); 
			  String updateDesc = rootElement.getElementsByTagName("ns1:updateDesc").item(0).getTextContent();
			  String effectiveTime = rootElement.getElementsByTagName("ns1:effectiveTime").item(0).getTextContent(); 
			  String expiryTime = rootElement.getElementsByTagName("ns1:expiryTime").item(0).getTextContent(); 
			  String TraceUniqueID = rootElement.getElementsByTagName("value").item(13).getTextContent();
			  
			   //sdpDataSyncSQL.SDPDataSyncSQLObject(msisdn, spID, productID, serviceID, serviceList, updateType, updateTime, updateDesc, effectiveTime, expiryTime, TraceUniqueID);
			  
			  System.out.println("-------SDP DataSync Information---------");
			  System.out.println("DataSync Details : " + msisdn + " " +  spID + " " + productID + " " + serviceID + " " + serviceList 
					  + " " + updateType + " " + updateTime + " " + updateDesc + " " + effectiveTime + " " + expiryTime + " " 
					  + TraceUniqueID + " End Of DataSync Details");
			  System.out.println("-------END DataSync Information---------");
			  
			 
			  if(xmlDoc != null){
			    	
			    	response.setContentType("text/xml; charset=UTF-8");
					response.getWriter().print(dataSyncResponseXML());
			    	
			  }
			  
			  			  
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {			
			
			
		}       
		
	}
	
	
	private String dataSyncResponseXML()
    {
        StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:loc=\"http://www.csapi.org/schema/parlayx/data/sync/v1_0/local\">");
        sb.append(" <soapenv:Header/>")
          .append("   <soapenv:Body>")
          .append("     <loc:syncOrderRelationResponse>")
          .append("        <loc:result>0</loc:result>")
          .append("        <loc:resultDescription>OK</loc:resultDescription>")
          .append("     </loc:syncOrderRelationResponse>")
          .append("  </soapenv:Body>")
          .append("</soapenv:Envelope>");

        return sb.toString(); 
    }
	
	

}
