package com.techvalley.sdp.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.sdp.dbcon.SDP1882Connection;
import com.techvalley.sdp.sender.Sdp1882Sender;

public class Sdp1882Job implements Job {

	SDP1882Connection sdp1882Connection = new SDP1882Connection();
	Sdp1882Sender sdp1882Sender = new Sdp1882Sender();
	
	CallableStatement SDP1882SenderCallableStatement = null;
    ResultSet SDP1882SenderResult = null;
	String SDP1882SmsSender = "{ call sp_1882SmsSender() }";	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try{
			
			SDP1882SenderCallableStatement = sdp1882Connection.SDP1882DBConnection().prepareCall(SDP1882SmsSender);	
			SDP1882SenderResult = SDP1882SenderCallableStatement.executeQuery();
            while(SDP1882SenderResult.next()) {
            	
            	sdp1882Sender.SmsSender(SDP1882SenderResult.getString("spId"), SDP1882SenderResult.getString("spPassword"), 
            			SDP1882SenderResult.getString("serviceId"), SDP1882SenderResult.getString("timeStamp"), SDP1882SenderResult.getString("linkid"), 
            			SDP1882SenderResult.getString("msisdn"), SDP1882SenderResult.getString("shortCode"), SDP1882SenderResult.getString("message"), 
            			SDP1882SenderResult.getString("correlator"));
            	//sdp1882Sender.SmsSender(spId, spPassword, serviceId, timeStamp, linkid, addresses, senderName, message, correlator);
            }
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			cleanConnection();
		}
		
		//System.out.print("SDP 1882 Job is running here ...\n");		
	}	
	
	
	private void cleanConnection() {

		try{
		
	        if (sdp1882Connection.SDP1882DBConnection() != null) {
	        	sdp1882Connection.SDP1882DBConnection().close();
	        }
	
	         if (SDP1882SenderCallableStatement != null){
	        	 SDP1882SenderCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }


}
