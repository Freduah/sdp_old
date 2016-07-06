package com.techvalley.sdp.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.sdp.dbcon.databaseConnection;
import com.techvalley.sdp.sender.SdpSubscriptionSender;

public class SdpSubscriptionJob implements Job {
	
	databaseConnection databaseConn = new databaseConnection();
	SdpSubscriptionSender sdpSubscriptionSender = new SdpSubscriptionSender();
	
	CallableStatement SDPSubscribeSenderCallableStatement = null;
    ResultSet SDPSubscribeSenderResult = null;
	String SDPSubscribeSmsSender = "{ call sp_subscriptionSmsSender() }";	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try{
			
			SDPSubscribeSenderCallableStatement = databaseConn.mtnDBConnection().prepareCall(SDPSubscribeSmsSender);
			SDPSubscribeSenderResult = SDPSubscribeSenderCallableStatement.executeQuery();
			
			while(SDPSubscribeSenderResult.next()) {				
				sdpSubscriptionSender.subscribeSender(SDPSubscribeSenderResult.getString("spId"), SDPSubscribeSenderResult.getString("password"), 
						SDPSubscribeSenderResult.getString("timeStamp"), SDPSubscribeSenderResult.getString("msisdn"), 
						SDPSubscribeSenderResult.getString("productId"), Boolean.parseBoolean(SDPSubscribeSenderResult.getString("isAutoExtend")), 
						SDPSubscribeSenderResult.getString("channelId"));
				// SELECT `Id`, `spId`, `password`, `timeStamp`, `msisdn`, `productId`, `isAutoExtend`, `channelId` FROM `tbl_subscription` WHERE isSent = 0 LIMIT 3;
			}			
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
