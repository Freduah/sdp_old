package com.techvalley.sdp.job;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SDPUnsubscriptionJob implements Job {

	CallableStatement SDPUnsubscribeSenderCallableStatement = null;
    ResultSet SDPUnsubscribeSenderResult = null;
	String SDPUnsubscribeSmsSender = "{ call sp_unsubscriptionSmsSender() }";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
