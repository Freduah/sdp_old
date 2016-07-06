package com.techvalley.sdp.data;

public class SDPSmsResponse {
	
	
	public String dataSyncResponseXML()
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

    public String moResponseXML()
    {
        StringBuilder sb = new StringBuilder("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.csapi.org/schema/parlayx/sms/notification/v2_2/local\">");
          sb.append(" <SOAP-ENV:Body>")
          .append("  <ns1:notifySmsReceptionResponse/>")
          .append("  </SOAP-ENV:Body>")
          .append("</SOAP-ENV:Envelope>");
        return sb.toString();
    }

    public String notifyDeliveryResponseXML(String senderAddress, String deliveryStatus)
    {
        StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        sb.append(" <soapenv:Body>")
         .append("  <ns1:getSmsDeliveryStatusResponse xmlns:ns1=\"http://www.csapi.org/schema/parlayx/sms/send/v2_2/local\">")
         .append("  <ns1:result>")
         .append("  <address>").append(senderAddress).append("</address>")
         .append("  <deliveryStatus>").append(deliveryStatus).append("</deliveryStatus>")
         .append("  </ns1:result>")
         .append("  </ns1:getSmsDeliveryStatusResponse>")
         .append(" </soapenv:Body>")
         .append(" </soapenv:Envelope>");
        return sb.toString();
    }

}
