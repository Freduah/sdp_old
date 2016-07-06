package com.techvalley.sdp.data;

public class SDPSmsObjects {
	
	public String smsXMLObject(String spId, String spPassword,String serviceId, String timeStamp, String linkid, String addresses, String senderName, String message, String correlator)
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
            .append("     <endpoint>http://45.58.43.180:1780/sdp/SDPDeliveryReceipt</endpoint>")
            .append("     <interfaceName>SmsNotification</interfaceName>")
            .append("     <correlator>").append(correlator).append("</correlator>")
            .append("     </loc:receiptRequest>")
            .append("  </loc:sendSms>")
            .append(" </soapenv:Body>")
            .append("</soapenv:Envelope>");
        return sb.toString();
    }
	
		
	public String subscribeXML(String spId, String password, String timeStamp, String msisdn, String productId, boolean isAutoExtend, String channelId)
    {
		StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:loc=\"http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local\">");
        sb.append(" <soapenv:Header>")
          .append("       <tns:RequestSOAPHeader xmlns:tns=\"http://www.huawei.com.cn/schema/common/v2_1\">")
          .append("           <tns:spId>").append(spId).append("</tns:spId>")
          .append("           <tns:spPassword>").append(password).append("</tns:spPassword>")
          .append("           <tns:timeStamp>").append(timeStamp).append("</tns:timeStamp>")
          .append("           <oauth_token></oauth_token>")
          .append("       </tns:RequestSOAPHeader>")
          .append(" </soapenv:Header>")
          .append("   <soapenv:Body>")
          .append("       <loc:subscribeProductRequest>")
          .append("           <loc:subscribeProductReq>")
          .append("               <userID>")
          .append("                   <ID>").append(msisdn).append("</ID>")
          .append("                   <type>0</type>")
          .append("               </userID>")
          .append("               <subInfo>")
          .append("                   <productID>").append(productId).append("</productID>")
          .append("                   <operCode>zh</operCode>")
          .append("                   <isAutoExtend>").append(isAutoExtend ? 1 : 0).append("</isAutoExtend>")
          .append("                   <channelID>").append(channelId).append("</channelID>")
          .append("               </subInfo>")
          .append("           </loc:subscribeProductReq>")
          .append("       </loc:subscribeProductRequest>")
          .append("   </soapenv:Body>")
          .append("</soapenv:Envelope>");
        
        return sb.toString();
    }
	
	
	public String unsubscribeXML(String spId, String password, String timeStamp, String msisdn, String productId, boolean isAutoExtend, String channelId)
    {
        StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:loc=\"http://www.csapi.org/schema/parlayx/subscribe/manage/v1_0/local\">");
        sb.append("  <soapenv:Header>")
          .append("      <tns:RequestSOAPHeader xmlns:tns=\"http://www.huawei.com.cn/schema/common/v2_1\">")
          .append("           <tns:spId>").append(spId).append("</tns:spId>")
          .append("           <tns:spPassword>").append(password).append("</tns:spPassword>")
          .append("          <tns:timeStamp>").append(timeStamp).append("</tns:timeStamp>")
          .append("      </tns:RequestSOAPHeader>")
          .append("  </soapenv:Header>")
          .append("  <soapenv:Body>")
          .append("     <loc:unSubscribeProductRequest>")
          .append("        <loc:unSubscribeProductReq>")
          .append("         <userID>")
          .append("            <ID>").append(msisdn).append("</ID>")
          .append("            <type>0</type>")
          .append("         </userID>")
          .append("         <subInfo>")
          .append("            <productID>").append(productId).append("</productID>")
          .append("            <operCode>zh</operCode>")
          .append("            <isAutoExtend>").append(isAutoExtend ? 1 : 0).append("</isAutoExtend>")
          .append("            <channelID>").append(channelId).append("</channelID>")
          .append("            <extensionInfo>")
          .append("               <namedParameters>")
          .append("                  <key>SubType</key>")
          .append("                  <value>0</value>")
          .append("               </namedParameters>")
          .append("            </extensionInfo>")
          .append("         </subInfo>")
          .append("       </loc:unSubscribeProductReq>")
          .append("     </loc:unSubscribeProductRequest>")
          .append("  </soapenv:Body>")
          .append("</soapenv:Envelope>");
        
        return sb.toString();
    }
	
	
	public String dataSyncResponseXML()
    {
        StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:loc=\"http://www.csapi.org/schema/parlayx/data/sync/v1_0/local\">");
        sb.append("  <soapenv:Header/>")
          .append("    <soapenv:Body>")
          .append("      <loc:syncOrderRelationResponse>")
          .append("         <loc:result>0</loc:result>")
          .append("         <loc:resultDescription>OK</loc:resultDescription>")
          .append("      </loc:syncOrderRelationResponse>")
          .append("   </soapenv:Body>")
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
        sb.append("   <soapenv:Body>")
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
