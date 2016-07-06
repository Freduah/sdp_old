package com.techvalley.sdp.data;

import java.sql.CallableStatement;

import com.techvalley.sdp.dbcon.SDPDeliveryConnection;

public class SDPDeliveryReceiptSQL {
	
	SDPDeliveryConnection DeliveryConn = new SDPDeliveryConnection();
	
	CallableStatement SDPDeliveryReceiptCallableStatement = null;
	String SDPDeliveryReceiptCall = "{ call sp_sdpdeliveryreceipt(?, ?, ?, ?, ?, ?, ?) }";
	
	public void SQDDeliveryReceiptSQLObject(String spId, String serviceId, String traceUniqueID, 
            String OperatorID, String correlator, String msisdn, String deliveryStatus){
				
		try{
			
			SDPDeliveryReceiptCallableStatement = DeliveryConn.SDPDeliveryReceiptConnection().prepareCall(SDPDeliveryReceiptCall);
			SDPDeliveryReceiptCallableStatement.setString(1, spId);
			SDPDeliveryReceiptCallableStatement.setString(2, serviceId);
			SDPDeliveryReceiptCallableStatement.setString(3, traceUniqueID);
			SDPDeliveryReceiptCallableStatement.setString(4, OperatorID);
			SDPDeliveryReceiptCallableStatement.setString(5, correlator);
			SDPDeliveryReceiptCallableStatement.setString(6, msisdn);
			SDPDeliveryReceiptCallableStatement.setString(7, deliveryStatus);
			
			SDPDeliveryReceiptCallableStatement.executeQuery();
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally{
			cleanConnection();
		}		
		
	}
	
	
	private void cleanConnection() {

		try{
		
	        if (DeliveryConn.SDPDeliveryReceiptConnection() != null) {
	        	DeliveryConn.SDPDeliveryReceiptConnection().close();
	        }
	
	         if (SDPDeliveryReceiptCallableStatement != null){
	        	 SDPDeliveryReceiptCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }	

}
