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

import com.techvalley.sdp.data.SDPDeliveryReceiptSQL;
import com.techvalley.sdp.data.SDPSmsResponse;


@WebServlet("/sdpdeliveryreceipt")
public class SDPDeliveryReceipt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SDPSmsResponse sdpSmsResponse = new SDPSmsResponse();

	SDPDeliveryReceiptSQL sdpDeliveryReceiptSQL = new SDPDeliveryReceiptSQL();
	
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
			 			  
			  String spId = rootElement.getElementsByTagName("ns1:spId").item(0).getTextContent();
			  String serviceId = rootElement.getElementsByTagName("ns1:serviceId").item(0).getTextContent(); 
			  String traceUniqueID = rootElement.getElementsByTagName("ns1:traceUniqueID").item(0).getTextContent();
	          String OperatorID = rootElement.getElementsByTagName("ns1:OperatorID").item(0).getTextContent(); 
	          String correlator = rootElement.getElementsByTagName("ns2:correlator").item(0).getTextContent();
	          String msisdn = rootElement.getElementsByTagName("address").item(0).getTextContent();
	          String deliveryStatus = rootElement.getElementsByTagName("deliveryStatus").item(0).getTextContent();
			  			  
	          //sdpDeliveryReceiptSQL.SQDDeliveryReceiptSQLObject(spId, serviceId, traceUniqueID, OperatorID, correlator, msisdn, deliveryStatus);
			  
			  System.out.println("-------SDP Delivery Receipt Information---------".toUpperCase());			  
			  System.out.println(spId + " " + serviceId + " " + traceUniqueID + " " + OperatorID + " " + correlator + " " + msisdn + " " + deliveryStatus);			  
			  System.out.println("-------End SDP Delivery Receipt Information---------".toUpperCase());
			  
			  if(xmlDoc != null){
			    	
			    	response.setContentType("text/xml; charset=UTF-8");
					response.getWriter().print(sdpSmsResponse.notifyDeliveryResponseXML(msisdn, deliveryStatus));
			    	
			  }					  
			  
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			
			
			
		}
		
	}

}
