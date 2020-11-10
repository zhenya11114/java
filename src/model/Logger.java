package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import model.Tools.file;

public class Logger {
	String logPath;
	
	Logger(String logPath){
		super();
		this.logPath = logPath;
	}
	
	public void appendToLog(String content) {
    	try {
    		if(content.charAt(content.length()-1) != '\n') {
    			content += "\n";
    		}
    		
    		File f = new File(logPath);
    		if(f.exists() && !f.isDirectory()) {
    			FileWriter writer = new FileWriter(f, true);
		        writer.write(content);
		        writer.close();
    		}
    		else {
    			Tools.file.createFile(logPath, "");
    		}
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			System.exit(0);
		}
    }
	
	public <T> void printLog(final T... args) {
    	LocalDate day = LocalDate.now();
    	LocalTime time = LocalTime.now(); 
    	String toFile = day.toString() + " " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + '\n';
        for (final T i : args) {
        	toFile += i + "";
        	System.out.print(i);
        }
        file.appendToFile(logPath, toFile);
    }
}
