package com.techvalley.sdp.data;

import java.sql.CallableStatement;

import com.techvalley.sdp.dbcon.SDPMOConnection;

public class SDPMORequestSQL {
	
	SDPMOConnection sdpMOConn = new SDPMOConnection();
	
	CallableStatement SDPMORequestCallableStatement = null;
	String SDPMORequestCall = "{ call sp_sdpmorequest(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
		
	public void SDPMORequestSQLObject(String spId, String serviceId, String linkid, String traceUniqueID, 
			String OperatorID, String correlator, String message, String msisdn, 
			String smsServiceActivationNumber, String eventDateTime){
		
		try{
			
			SDPMORequestCallableStatement = sdpMOConn.SDPMORequestConnection().prepareCall(SDPMORequestCall);
			SDPMORequestCallableStatement.setString(1, spId);
			SDPMORequestCallableStatement.setString(2, serviceId);
			SDPMORequestCallableStatement.setString(3, linkid);
			SDPMORequestCallableStatement.setString(4, traceUniqueID);
			SDPMORequestCallableStatement.setString(5, OperatorID);
			SDPMORequestCallableStatement.setString(6, correlator);
			SDPMORequestCallableStatement.setString(7, message);
			SDPMORequestCallableStatement.setString(8, msisdn);
			SDPMORequestCallableStatement.setString(9, smsServiceActivationNumber);
			SDPMORequestCallableStatement.setString(10, eventDateTime);
			
			SDPMORequestCallableStatement.executeQuery();
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			cleanConnection();
		}
		
	}
	
	
	private void cleanConnection() {

		try{
		
	        if (sdpMOConn.SDPMORequestConnection() != null) {
	        	sdpMOConn.SDPMORequestConnection().close();
	        }
	
	         if (SDPMORequestCallableStatement != null){
	        	 SDPMORequestCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }

}
