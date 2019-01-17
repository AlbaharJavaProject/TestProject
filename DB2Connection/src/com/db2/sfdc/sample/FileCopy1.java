package com.db2.sfdc.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FileCopy1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//private final static String fileInput = "D:/Albahar/JavaAPIRunSit/log/JavaApiSetviceLogSit_25122018.log.txt";
		///private static String fileOutput = "D:/Albahar/JavaAPIRunSit/log/OldLogFiles/";
		
		
		String fileOutputPath = "D:/Albahar/JavaAPIRunSit/log/OldLogFiles/";
		
		Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        //Current date need to write in property file
        String currentdate = formatter.format(date);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
		String yesterdayDate =  formatter.format(cal.getTime());
		
		fileOutputPath = fileOutputPath +yesterdayDate+"LogFileFolder/";
		File outputFile = new File(fileOutputPath);
		
		if (!outputFile.exists()) {
			outputFile.mkdir();
            System.out.println("222222222222 ==");
        }
		File sourceFile = new File("D:\\Albahar\\JavaAPIRunSit\\log\\JavaApiSetviceLogSit_25122018.log.txt");
		File destinationFile = new File(outputFile+"\\JavaApiSetviceLogSit_25122018.log.txt");

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(sourceFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(destinationFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int bufferSize;
		byte[] bufffer = new byte[512];
		try {
			while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
			    fileOutputStream.write(bufffer, 0, bufferSize);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileInputStream.close();
			fileOutputStream.close();
			System.out.println("file write completed");
			sourceFile.delete();
			 File fileNew = new File("D:\\Albahar\\JavaAPIRunSit\\log\\JavaApiSetviceLogSit_25122018.log.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
