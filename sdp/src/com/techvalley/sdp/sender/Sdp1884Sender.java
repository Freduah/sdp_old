package com.techvalley.sdp.sender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sdp1884Sender {
	
	public void SmsSender(String spId, String spPassword,String serviceId, String timeStamp, String linkid, String addresses, String senderName, String message, String correlator) {
		
		
		URL urlObject;
		String SmsXMLDoc = smsXMLObject(spId, spPassword, serviceId, timeStamp, linkid, addresses, senderName, message, correlator );
		
		try {
			//urlObject = new URL("http://196.201.33.98:8310/SendSmsService/services/SendSms");
			urlObject = new URL("http://localhost:1780/sdp/sdpdeliveryreceipt");
			HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/xml");
			con.addRequestProperty("Content-Length", "" + SmsXMLDoc.getBytes().length);
	       
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(SmsXMLDoc);
			wr.flush();
			wr.close();
			
			con.connect();
	        
	        int responseCode = con.getResponseCode();
            if(responseCode == 200) {
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

                //System.out.println("Submission result: " + in.readLine());
                in.close();
            }
	            
		} catch (Exception e) {
			// TODO Auto-generated catch block  Exception 
			e.printStackTrace();
		} 			
		
	}	
		
	
	private String smsXMLObject(String spId, String spPassword,String serviceId, String timeStamp, String linkid, String addresses, String senderName, String message, String correlator)
    {
        StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"  xmlns:v2=\"http://www.huawei.com.cn/schema/common/v2_1\" xmlns:loc=\"http://www.csapi.org/schema/parlayx/sms/send/v2_2/local\">");
        sb.append("  <soapenv:Header>")
            .append("    <v2:RequestSOAPHeader>")
            .append("      <v2:spId>").append(spId).append("</v2:spId>")
            .append("      <v2:spPassword>").append(spPassword).append("</v2:spPassword>")
            .append("      <v2:bundleID/>")
            .append("      <v2:serviceId>").append(serviceId).append("</v2:serviceId>")
            .append("      <v2:timeStamp>").append(timeStamp).append("</v2:timeStamp>")
            .append("      <v2:OA>").append(addresses).append("</v2:OA>")
            .append("      <v2:FA>").append(addresses).append("</v2:FA>")
            .append("      <v2:linkid>").append(linkid).append("</v2:linkid>")
            .append("      <v2:presentid/>")
            .append("    </v2:RequestSOAPHeader>")
            .append(" </soapenv:Header>")
            .append(" <soapenv:Body>")
            .append("   <loc:sendSms>")
            .append("     <loc:addresses>").append(addresses).append("</loc:addresses>")
            .append("     <loc:senderName>").append(senderName).append("</loc:senderName>")
            .append("     <loc:message>").append(message).append("</loc:message>")
            .append("     <loc:receiptRequest>")
            .append("     <endpoint>http://45.58.43.180:1780/sdp/sdpdeliveryreceipt</endpoint>")
            .append("     <interfaceName>SmsNotification</interfaceName>")
            .append("     <correlator>").append(correlator).append("</correlator>")
            .append("     </loc:receiptRequest>")
            .append("  </loc:sendSms>")
            .append(" </soapenv:Body>")
            .append("</soapenv:Envelope>");
        return sb.toString();
    }	


}
