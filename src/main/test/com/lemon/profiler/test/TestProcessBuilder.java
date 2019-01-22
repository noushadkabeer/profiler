package com.lemon.profiler.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestProcessBuilder {
	public static void main(String args[]) {
		System.out.println("Helloo");
		TestProcessBuilder tpb = new TestProcessBuilder();
		tpb.mainf();
	}
	
	public void method2() {
		
		try {
			//String[] callAndArgs= {"py","C:/Users/emper/PycharmProjects/profileR/venv/Two.py","arg1"};
			String[] cmd = {
				      "py",
				      "C:/Users/emper/PycharmProjects/profileR/venv/Two.py", "C:/NSD/work/profiler/Resumes/Sampledocs/temp/",
				    };
	
//			
//			String[] cmd = {
//				      "py",
//				      "C:/Users/emper/PycharmProjects/profileR/venv/One.py", filesPath,
//				    };
			Process p = Runtime.getRuntime().exec(cmd);
//			log.info("Py call triggered");
			
//			ProcessBuilder pb = new ProcessBuilder(cmd);
//	   //     Process p = pb.start();
	        System.out.println("Two");
//	        Process p =Runtime.getRuntime().exec(cmd);
	        p.waitFor();
	        System.out.println("Three");
	        BufferedReader stdInput = new BufferedReader(new 
	                InputStreamReader(p.getInputStream()));

	        BufferedReader stdError = new BufferedReader(new 
	                InputStreamReader(p.getErrorStream()));
	        StringBuffer response = new StringBuffer();
	        StringBuffer errorStr = new StringBuffer();
	        boolean alreadyWaited = false;
	        while (p.isAlive()) {
	            try {
	                if(alreadyWaited) {

	                    // read the output from the command because
	                    //if we don't then the buffers fill up and
	                    //the command stops and doesn't return
	                    String temp;

	                    while ((temp = stdInput.readLine()) != null) {
	                        response.append(temp);
	                    }


	                    String errTemp;
	                    while ((errTemp = stdError.readLine()) != null) {
	                        errorStr.append(errTemp);
	                    }                                                   
	                }
	                Thread.sleep(1000);
	                alreadyWaited = true;
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("Response is " + response);
	            System.out.println("Error is: " + errorStr);
	        }

	    } catch (Exception e) {
	        System.out.println("Error running system command"+ e);
	    }
	}
	
	 public void maint()
	    {
	        try
	        {            
	            Runtime rt = Runtime.getRuntime();
	            Process proc = rt.exec("cmd dir");
	            InputStream stdin = proc.getInputStream();
	            InputStreamReader isr = new InputStreamReader(stdin);
	            BufferedReader br = new BufferedReader(isr);
	            String line = null;
	            System.out.println("<OUTPUT>");
	            while ( (line = br.readLine()) != null)
	                System.out.println(line);
	            System.out.println("</OUTPUT>");
	            int exitVal = proc.waitFor();            
	            System.out.println("Process exitValue: " + exitVal);
	        } catch (Throwable t)
	          {
	            t.printStackTrace();
	          }
	    }
	 public void mainf()
	    {
	        try
	        {            
	            Runtime rt = Runtime.getRuntime();
	            Process proc = rt.exec("py C:/Users/emper/PycharmProjects/profileR/venv/One.py \"C:\\NSD\\work\\profiler\\Resumes\\Sampledocs\\temp\\140258789RESUME\"");
	            InputStream stdin = proc.getInputStream();
	            InputStreamReader isr = new InputStreamReader(stdin);
	            BufferedReader br = new BufferedReader(isr);
	            String line = null;
	            System.out.println("<OUTPUT>");
	            while ( (line = br.readLine()) != null)
	                System.out.println(line);
	            System.out.println("</OUTPUT>");
	            int exitVal = proc.waitFor();            
	            System.out.println("Process exitValue: " + exitVal);
	        } catch (Throwable t)
	          {
	            t.printStackTrace();
	          }
	    }
}
