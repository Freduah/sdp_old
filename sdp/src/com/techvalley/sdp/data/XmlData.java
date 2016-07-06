package com.techvalley.sdp.data;

public class XmlData {
	
	
	public String dataSyncXml(){		
		
		StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        sb.append(" <soapenv:Body>")
          .append("  <ns1:syncOrderRelation xmlns:ns1=\"http://www.csapi.org/schema/parlayx/data/sync/v1_0/local\">")
          .append("     <ns1:userID>")
          .append("        <ID>233244800250</ID>")
          .append("        <type>0</type>")
          .append("     </ns1:userID>")
          .append("     <ns1:spID>2330110000230</ns1:spID>")
          .append("     <ns1:productID>23301220000001252</ns1:productID>")
          .append("     <ns1:serviceID>233012000001864</ns1:serviceID>")
          .append("     <ns1:serviceList>233012000001864</ns1:serviceList>")
          .append("     <ns1:updateType>2</ns1:updateType>")
          .append("     <ns1:updateTime>20141103164029</ns1:updateTime>")
          .append("     <ns1:updateDesc>Deletion</ns1:updateDesc>")
          .append("     <ns1:effectiveTime>20141103152510</ns1:effectiveTime>")
          .append("     <ns1:expiryTime>20141103164029</ns1:expiryTime>")
          .append("     <ns1:extensionInfo>")
          .append("        <item>")
          .append("           <key>Starttime</key>")
          .append("           <value>20141103152510</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>chargeMode</key>")
          .append("           <value>0</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>MDSPSUBEXPMODE</key>")
          .append("           <value>1</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>objectType</key>")
          .append("           <value>1</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>isAutoExtend</key>")
          .append("           <value>0</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>keyword</key>")
          .append("           <value>fun</value>")
          .append("        </item>")
          .append("        <item>")
          .append("          <key>cycleEndTime</key>")
          .append("           <value>20141103164029</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>updateReason</key>")
          .append("           <value>1</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>operatorID</key>")
          .append("           <value>23301</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>payType</key>")
          .append("           <value>1</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>transactionID</key>")
          .append("           <value>1415032829284</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>orderKey</key>")
          .append("           <value>999000000000081235</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>channelID</key>")
          .append("           <value>102</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>TraceUniqueID</key>")
          .append("           <value>000000405021411031640290535003</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>rentSuccess</key>")
          .append("           <value>true</value>")
          .append("        </item>")
          .append("        <item>")
          .append("           <key>try</key>")
          .append("           <value>false</value>")
          .append("        </item>")
          .append("     </ns1:extensionInfo>")
          .append("  </ns1:syncOrderRelation>")
         .append("</soapenv:Body>")
      .append("</soapenv:Envelope>");

        return sb.toString();
	}
	
	
	public String motestXML()
    {
        StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        sb.append(" <soapenv:Header>")
        .append("  <ns1:NotifySOAPHeader xmlns:ns1=\"http://www.huawei.com.cn/schema/common/v2_1\">")
        .append("    <ns1:spId>2330110000230</ns1:spId>")
        .append("    <ns1:serviceId>233012000001878</ns1:serviceId>")
        .append("    <ns1:linkid>28182354075500557821</ns1:linkid>")
        .append("    <ns1:traceUniqueID>404090104991410281823544632003</ns1:traceUniqueID>")
        .append("    <ns1:OperatorID>23301</ns1:OperatorID>")
        .append("  </ns1:NotifySOAPHeader>")
        .append(" </soapenv:Header>")
        .append(" <soapenv:Body>")
        .append("  <ns2:notifySmsReception xmlns:ns2=\"http://www.csapi.org/schema/parlayx/sms/notification/v2_2/local\">")
        .append("   <ns2:correlator>7124</ns2:correlator>")
        .append("     <ns2:message>")
        .append("      <message>Stop</message>")
        .append("      <senderAddress>tel:233245193710</senderAddress>")
        .append("      <smsServiceActivationNumber>tel:7124</smsServiceActivationNumber>")
        .append("      <dateTime>2014-10-31T20:44:08Z</dateTime>")
        .append("     </ns2:message>")
        .append("  </ns2:notifySmsReception>")
        .append(" </soapenv:Body>")
        .append("</soapenv:Envelope>");

        return sb.toString();
    }
	
	
	public String notifyTestXML()
    {
        StringBuilder sb = new StringBuilder ("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        sb.append(" <soapenv:Header>")
          .append("<ns1:NotifySOAPHeader xmlns:ns1=\"http://www.huawei.com.cn/schema/common/v2_1\">")
          .append("    <ns1:spId>2330110000230</ns1:spId>")
          .append("    <ns1:serviceId>233012000001860</ns1:serviceId>")
          .append("    <ns1:traceUniqueID>404090104991411041752200922006</ns1:traceUniqueID>")
          .append("    <ns1:OperatorID>23301</ns1:OperatorID>")
          .append(" </ns1:NotifySOAPHeader>")
          .append(" </soapenv:Header>")
          .append(" <soapenv:Body>")
          .append("    <ns2:notifySmsDeliveryReceipt xmlns:ns2=\"http://www.csapi.org/schema/parlayx/sms/notification/v2_2/local\">")
          .append("       <ns2:correlator>2331770</ns2:correlator>")
          .append("       <ns2:deliveryStatus>")
          .append("          <address>233244800250</address>")
          .append("          <deliveryStatus>DeliveredToTerminal</deliveryStatus>")
          .append("       </ns2:deliveryStatus>")
          .append("    </ns2:notifySmsDeliveryReceipt>")
          .append(" </soapenv:Body>")
          .append("</soapenv:Envelope>");
        
        return sb.toString();
    }
	
	
	

}
