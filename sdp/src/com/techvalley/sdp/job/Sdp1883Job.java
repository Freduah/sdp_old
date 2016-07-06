package com.techvalley.sdp.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.sdp.dbcon.SDP1883Connection;
import com.techvalley.sdp.sender.Sdp1883Sender;

public class Sdp1883Job implements Job {

	SDP1883Connection sdp1883Connection = new SDP1883Connection();
	Sdp1883Sender sdp1883Sender = new Sdp1883Sender();
	
	CallableStatement SDP1883SenderCallableStatement = null;
    ResultSet SDP1883SenderResult = null;
	String SDP1883SmsSender = "{ call sp_1883SmsSender() }";	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try{
			
			SDP1883SenderCallableStatement = sdp1883Connection.SDP1883DBConnection().prepareCall(SDP1883SmsSender);	
			SDP1883SenderResult = SDP1883SenderCallableStatement.executeQuery();
            while(SDP1883SenderResult.next()) {
            	
            	sdp1883Sender.SmsSender(SDP1883SenderResult.getString("spId"), SDP1883SenderResult.getString("spPassword"), 
            			SDP1883SenderResult.getString("serviceId"), SDP1883SenderResult.getString("timeStamp"), SDP1883SenderResult.getString("linkid"), 
            			SDP1883SenderResult.getString("msisdn"), SDP1883SenderResult.getString("shortCode"), SDP1883SenderResult.getString("message"), 
            			SDP1883SenderResult.getString("correlator"));
            	//sdp1883Sender.SmsSender(spId, spPassword, serviceId, timeStamp, linkid, addresses, senderName, message, correlator);
            }
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			cleanConnection();
		}
		
		//System.out.print("SDP 1883 Job is running here ...\n");		
	}	
	
	
	private void cleanConnection() {

		try{
		
	        if (sdp1883Connection.SDP1883DBConnection() != null) {
	        	sdp1883Connection.SDP1883DBConnection().close();
	        }
	
	         if (SDP1883SenderCallableStatement != null){
	        	 SDP1883SenderCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }


}
