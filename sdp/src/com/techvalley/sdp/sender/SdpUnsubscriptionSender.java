package com.techvalley.sdp.sender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SdpUnsubscriptionSender {
	
	
	public void unsubscribeSender(String spId, String password, String timeStamp, String msisdn, String productId, Boolean isAutoExtend, String channelId){
		
		URL urlObject;
		String SmsXMLDoc = unsubscribeXML(spId, password, timeStamp, msisdn, productId, isAutoExtend, channelId);
		
		try {
			//urlObject = new URL("http://196.201.33.98:8310/SubscribeManageService/services/SubscribeManage");
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
	
	
	
	private String unsubscribeXML(String spId, String password, String timeStamp, String msisdn, String productId, Boolean isAutoExtend, String channelId)
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


}
