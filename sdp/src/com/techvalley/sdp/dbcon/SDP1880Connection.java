package com.techvalley.sdp.dbcon;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SDP1880Connection {

	public Connection SDP1880DBConnection() throws Exception{
		
		Context initCtx = new InitialContext();
        DataSource ds = (DataSource) initCtx.lookup("java:/comp/env/jdbc/MtnDB");
       
        return ds.getConnection();
	}
}
