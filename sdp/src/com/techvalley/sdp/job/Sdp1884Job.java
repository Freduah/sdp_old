package com.techvalley.sdp.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.sdp.dbcon.SDP1884Connection;
import com.techvalley.sdp.sender.Sdp1884Sender;

public class Sdp1884Job implements Job {

	SDP1884Connection sdp1884Connection = new SDP1884Connection();
	Sdp1884Sender sdp1884Sender = new Sdp1884Sender();
	
	CallableStatement SDP1884SenderCallableStatement = null;
    ResultSet SDP1884SenderResult = null;
	String SDP1884SmsSender = "{ call sp_1884SmsSender() }";	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try{
			
			SDP1884SenderCallableStatement = sdp1884Connection.SDP1884DBConnection().prepareCall(SDP1884SmsSender);	
			SDP1884SenderResult = SDP1884SenderCallableStatement.executeQuery();
            while(SDP1884SenderResult.next()) {
            	
            	sdp1884Sender.SmsSender(SDP1884SenderResult.getString("spId"), SDP1884SenderResult.getString("spPassword"), 
            			SDP1884SenderResult.getString("serviceId"), SDP1884SenderResult.getString("timeStamp"), SDP1884SenderResult.getString("linkid"), 
            			SDP1884SenderResult.getString("msisdn"), SDP1884SenderResult.getString("shortCode"), SDP1884SenderResult.getString("message"), 
            			SDP1884SenderResult.getString("correlator"));
            	//sdp1884Sender.SmsSender(spId, spPassword, serviceId, timeStamp, linkid, addresses, senderName, message, correlator);
            }
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			cleanConnection();
		}
		
		//System.out.print("SDP 1884 Job is running here ...\n");		
	}	
	
	
	private void cleanConnection() {

		try{
		
	        if (sdp1884Connection.SDP1884DBConnection() != null) {
	        	sdp1884Connection.SDP1884DBConnection().close();
	        }
	
	         if (SDP1884SenderCallableStatement != null){
	        	 SDP1884SenderCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }

}
