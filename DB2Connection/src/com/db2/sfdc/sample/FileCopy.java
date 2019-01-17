package com.db2.sfdc.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FileCopy {
	
	private final static String fileInput = "D:\\Albahar\\JavaAPIRunSit\\log\\JavaApiSetviceLogSit_25122018.log.txt";
	private static String fileOutput = "D:\\Albahar\\JavaAPIRunSit\\log\\OldLogFiles\\";
	public static void main(String args[]) {
		
		Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        //Current date need to write in property file
        String currentdate = formatter.format(date);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
		String yesterdayDate =  formatter.format(cal.getTime());
		
		fileOutput = fileOutput +yesterdayDate+"LogFileFolder\\";
		System.out.println("fileOutput =="+fileOutput);
		File inputFile = new File(fileInput);
		File outputFile = new File(fileInput);
		
		try {
			FileCopy.copyDirectory(inputFile,outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void copyDirectory(File sourceLocation , File targetLocation)
		    throws IOException {

		       /* if (sourceLocation.isDirectory()) {
		            if (!targetLocation.exists()) {
		                targetLocation.mkdir();
		            }

		            String[] children = sourceLocation.list();
		            for (int i=0; i<children.length; i++) {
		                copyDirectory(new File(sourceLocation, children[i]),
		                        new File(targetLocation, children[i]));
		            }
		        } else {*/
		        	
					System.out.println("111111111111 ==");
		        	if (!targetLocation.exists()) {
		                targetLocation.mkdir();
		                System.out.println("222222222222 ==");
		            }
		        	System.out.println("3333333333 ==");
		            InputStream in = new FileInputStream(sourceLocation);
		            OutputStream out = new FileOutputStream(targetLocation);
		            System.out.println("55555555555555555 ==");
		            // Copy the bits from instream to outstream
		            byte[] buf = new byte[1024];
		            int len;
		            while ((len = in.read(buf)) > 0) {
		                out.write(buf, 0, len);
		            }
		            System.out.println("6666666666666666666 ==");
		            in.close();
		            out.close();
		        //}
		    }
}
