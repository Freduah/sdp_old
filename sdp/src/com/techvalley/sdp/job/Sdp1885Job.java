package com.techvalley.sdp.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.sdp.dbcon.SDP1885Connection;
import com.techvalley.sdp.sender.Sdp1885Sender;

public class Sdp1885Job implements Job {

	SDP1885Connection sdp1885Connection = new SDP1885Connection();
	Sdp1885Sender sdp1885Sender = new Sdp1885Sender();
	
	CallableStatement SDP1885SenderCallableStatement = null;
    ResultSet SDP1885SenderResult = null;
	String SDP1885SmsSender = "{ call sp_1885SmsSender() }";	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try{
			
			SDP1885SenderCallableStatement = sdp1885Connection.SDP1885DBConnection().prepareCall(SDP1885SmsSender);	
			SDP1885SenderResult = SDP1885SenderCallableStatement.executeQuery();
            while(SDP1885SenderResult.next()) {
            	
            	sdp1885Sender.SmsSender(SDP1885SenderResult.getString("spId"), SDP1885SenderResult.getString("spPassword"), 
            			SDP1885SenderResult.getString("serviceId"), SDP1885SenderResult.getString("timeStamp"), SDP1885SenderResult.getString("linkid"), 
            			SDP1885SenderResult.getString("msisdn"), SDP1885SenderResult.getString("shortCode"), SDP1885SenderResult.getString("message"), 
            			SDP1885SenderResult.getString("correlator"));
            	//sdp1885Sender.SmsSender(spId, spPassword, serviceId, timeStamp, linkid, addresses, senderName, message, correlator);
            }
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			cleanConnection();
		}
		
		//System.out.print("SDP 1885 Job is running here ...\n");		
	}	
	
	
	private void cleanConnection() {

		try{
		
	        if (sdp1885Connection.SDP1885DBConnection() != null) {
	        	sdp1885Connection.SDP1885DBConnection().close();
	        }
	
	         if (SDP1885SenderCallableStatement != null){
	        	 SDP1885SenderCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }


}
