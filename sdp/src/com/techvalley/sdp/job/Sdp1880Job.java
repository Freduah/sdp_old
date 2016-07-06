package com.techvalley.sdp.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.sdp.dbcon.SDP1880Connection;
import com.techvalley.sdp.sender.Sdp1880Sender;


public class Sdp1880Job implements Job  {
	
	SDP1880Connection sdp1880Connection = new SDP1880Connection();
	Sdp1880Sender sdp1880Sender = new Sdp1880Sender();
	
	CallableStatement SDP1880SenderCallableStatement = null;
    ResultSet SDP1880SenderResult = null;
	String SDP1880SmsSender = "{ call sp_1880SmsSender() }";	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try{
			
			SDP1880SenderCallableStatement = sdp1880Connection.SDP1880DBConnection().prepareCall(SDP1880SmsSender);	
			SDP1880SenderResult = SDP1880SenderCallableStatement.executeQuery();
            while(SDP1880SenderResult.next()) {
            	
            	sdp1880Sender.SmsSender(SDP1880SenderResult.getString("spId"), SDP1880SenderResult.getString("spPassword"), 
            			SDP1880SenderResult.getString("serviceId"), SDP1880SenderResult.getString("timeStamp"), SDP1880SenderResult.getString("linkid"), 
            			SDP1880SenderResult.getString("msisdn"), SDP1880SenderResult.getString("shortCode"), SDP1880SenderResult.getString("message"), 
            			SDP1880SenderResult.getString("correlator"));
            	//sdp1880Sender.SmsSender(spId, spPassword, serviceId, timeStamp, linkid, addresses, senderName, message, correlator);
           
            	// System.out.print("SDP 1880 Job is running here ...\n");	
            }          
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			cleanConnection();
		}	
			
	}	
	
	
	private void cleanConnection() {

		try{
		
	        if (sdp1880Connection.SDP1880DBConnection() != null) {
	        	sdp1880Connection.SDP1880DBConnection().close();
	        }
	
	         if (SDP1880SenderCallableStatement != null){
	        	 SDP1880SenderCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }

}
