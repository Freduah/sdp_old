package com.techvalley.sdp.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.sdp.dbcon.SDP1881Connection;
import com.techvalley.sdp.sender.Sdp1881Sender;

public class Sdp1881Job implements Job {

	SDP1881Connection sdp1881Connection = new SDP1881Connection();
	Sdp1881Sender sdp1881Sender = new Sdp1881Sender();
	
	CallableStatement SDP1881SenderCallableStatement = null;
    ResultSet SDP1881SenderResult = null;
	String SDP1881SmsSender = "{ call sp_1881SmsSender() }";	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try{
			
			SDP1881SenderCallableStatement = sdp1881Connection.SDP1881DBConnection().prepareCall(SDP1881SmsSender);	
			SDP1881SenderResult = SDP1881SenderCallableStatement.executeQuery();
            while(SDP1881SenderResult.next()) {
            	
            	sdp1881Sender.SmsSender(SDP1881SenderResult.getString("spId"), SDP1881SenderResult.getString("spPassword"), 
            			SDP1881SenderResult.getString("serviceId"), SDP1881SenderResult.getString("timeStamp"), SDP1881SenderResult.getString("linkid"), 
            			SDP1881SenderResult.getString("msisdn"), SDP1881SenderResult.getString("shortCode"), SDP1881SenderResult.getString("message"), 
            			SDP1881SenderResult.getString("correlator"));
            	//sdp1881Sender.SmsSender(spId, spPassword, serviceId, timeStamp, linkid, addresses, senderName, message, correlator);
            }
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			cleanConnection();
		}
		
		//System.out.print("SDP 1881 Job is running here ...\n");		
	}	
	
	
	private void cleanConnection() {

		try{
		
	        if (sdp1881Connection.SDP1881DBConnection() != null) {
	        	sdp1881Connection.SDP1881DBConnection().close();
	        }
	
	         if (SDP1881SenderCallableStatement != null){
	        	 SDP1881SenderCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }


}
