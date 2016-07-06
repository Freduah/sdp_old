package com.techvalley.sdp.dbcon;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SDPDeliveryConnection {
	
public Connection SDPDeliveryReceiptConnection() throws Exception{
		
		Context initCtx = new InitialContext();
        DataSource ds = (DataSource) initCtx.lookup("java:/comp/env/jdbc/MtnDB");
       
        return ds.getConnection();
	}

}
