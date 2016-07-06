package com.techvalley.sdp.job;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.techvalley.sdp.data.XmlData;

public class DataSyncJob implements Job {
	
	XmlData xmlData = new XmlData();

	@Override
	public void execute(JobExecutionContext context) 
			throws JobExecutionException {
		
		//System.out.println("Data Sync Job Runing.....");
		
		URL urlObject;
		try {
			urlObject = new URL("http://localhost:1780/sdp/sdpdatasync");
			HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/xml");
			con.addRequestProperty("Content-Length", "" + xmlData.dataSyncXml().getBytes().length);
	       
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(xmlData.dataSyncXml());
			wr.flush();
			wr.close();
			
			con.connect();
	        
	        int responseCode = con.getResponseCode();
            if(responseCode == 200) {
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

                //System.out.println("Submission result: " + in.readLine());
                in.close();
            }
	            
		} catch (Exception e) {
			// TODO Auto-generated catch block  Exception 
			e.printStackTrace();
		} 
				
		
	}

}
