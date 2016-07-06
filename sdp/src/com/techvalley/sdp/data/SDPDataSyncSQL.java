package com.techvalley.sdp.data;

import java.sql.CallableStatement;

import com.techvalley.sdp.dbcon.SDPDataSyncConnection;

public class SDPDataSyncSQL {
	
	SDPDataSyncConnection DataSyncCon = new SDPDataSyncConnection();
	
	CallableStatement SDPDataSyncCallableStatement = null;
	String SDPDataSyncCall = "{ call sp_sdpdatasync(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
	
	
	public void SDPDataSyncSQLObject( String msisdn, String spID, String productID, String serviceID, String serviceList, String updateType, 
			String updateTime, String updateDesc, String effectiveTime, String expiryTime, String TraceUniqueID ){
		
		try{
			SDPDataSyncCallableStatement = DataSyncCon.DataSyncConnection().prepareCall(SDPDataSyncCall);
			SDPDataSyncCallableStatement.setString(1, msisdn);
			SDPDataSyncCallableStatement.setString(2, spID);
			SDPDataSyncCallableStatement.setString(3, productID);
			SDPDataSyncCallableStatement.setString(4, serviceID);
			SDPDataSyncCallableStatement.setString(5, serviceList);
			SDPDataSyncCallableStatement.setString(6, updateType);
			SDPDataSyncCallableStatement.setString(7, updateTime);
			SDPDataSyncCallableStatement.setString(8, updateDesc);
			SDPDataSyncCallableStatement.setString(9, effectiveTime);
			SDPDataSyncCallableStatement.setString(10, expiryTime);
			SDPDataSyncCallableStatement.setString(11, TraceUniqueID);
			
			SDPDataSyncCallableStatement.executeQuery();
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			cleanConnection();
		}
		
	}
	
	private void cleanConnection() {

		try{
		
	        if (DataSyncCon.DataSyncConnection() != null) {
	        	DataSyncCon.DataSyncConnection().close();
	        }
	
	         if (SDPDataSyncCallableStatement != null){
	        	 SDPDataSyncCallableStatement.close();
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	

}
