package com.techvalley.sdp.dbcon;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;

public class databaseConnection {
	
	public Connection mtnDBConnection() throws Exception{
		
		Context initCtx = new InitialContext();
        DataSource ds = (DataSource) initCtx.lookup("java:/comp/env/jdbc/MtnDB");
       
        return ds.getConnection();
	}

}
