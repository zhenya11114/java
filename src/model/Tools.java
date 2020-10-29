package model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Tools
{
	public static class file{
		@SafeVarargs
		public static <T> void serialize(String filePath, final T... args) {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath)))
	        {
				for(final T i : args) {
					oos.writeObject(i);
				}
	        }
	        catch(Exception e){
	        	System.out.println("An error occurred.");
				e.printStackTrace();
	        } 
		}
		public static ArrayList<Object> deserialize(String filePath) {
			ArrayList<Object> result = new ArrayList<Object>();
			
			File f = new File(filePath);
    		if(f.exists() && !f.isDirectory()) {
    			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f)))
    			{
    				while(true) {
    					result.add(in.readObject());
    				}
    			}
    			catch(EOFException e) {
    				//no need to do anything, the file is read
    			}
    			catch(IOException e)
    			{
    			    System.out.println("IOException is caught");
    			    e.printStackTrace();
    			}
    			catch(ClassNotFoundException e)
    			{
    			    System.out.println("ClassNotFoundException is caught");
    			    e.printStackTrace();
    			}
    		}
    		else {
    			Tools.file.createFile(filePath, "");
    		}
			
			return result;
		}
		
		/* method create a new file and write content in
	    /* method doesn't rewrite the content
	     */
	    public static void appendToFile(String path, String content) {
	    	try {
	    		if(content.charAt(content.length()-1) != '\n') {
	    			content += "\n";
	    		}
	    		
	    		File f = new File(path);
	    		if(f.exists() && !f.isDirectory()) {
	    			FileWriter writer = new FileWriter(f, true);
			        writer.write(content);
			        writer.close();
	    		}
	    		else {
	    			Tools.file.createFile(path, "");
	    		}
			}
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
	    }
	    
	    /* method create a new file and write content in
	    /* method can rewrite the content if stated
	     */
	    public static void createFile(String path, String content) {
	    	try {
	    		File f = new File(path);
				if (f.createNewFile()) {
					//System.out.println("File created: " + f.getName() + '\n');
				}
				
				FileWriter writer = new FileWriter(f);
		        writer.write(content);
		        writer.close();
	    	}
			catch (IOException e) {
				System.out.println("An error occured.");
				e.printStackTrace();
			}
	    }
	    
	    //method to read content of the file
	    public static String readFile(String path) {
	    	String result = "";
	    	try {
	    		File f = new File(path);
	    		if(f.exists() && !f.isDirectory()) {
	    			FileReader reader = new FileReader(f);
		    		Scanner scan = new Scanner(reader);
		    		while (scan.hasNextLine()) {
		               result += scan.nextLine() + "\n";
		            }
		    		scan.close();
		    		reader.close();
	    		}
	    		else {
	    			Tools.file.createFile(path, "");
	    		}
	    	}
	    	catch(IOException e) {
	    		System.out.println("File not found or error occured.");
				//e.printStackTrace();
				return "";
	    	}
	    	return result;
	    }
	}
	
	//prints indefinite amount of arguments
    @SafeVarargs
	public static <T> void print(final T... args) {
        for (final T i : args) {
            System.out.print(i);
        }
    }
    
    //logs indefinite amount of arguments
    @SafeVarargs
	public static <T> void printLog(String filePath, final T... args) {
    	LocalDate day = LocalDate.now();
    	LocalTime time = LocalTime.now(); 
    	String toFile = day.toString() + " " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + '\n';
        for (final T i : args) {
        	toFile += i + "";
        	System.out.print(i);
        }
        file.appendToFile(filePath, toFile);
    }
    
    /* implement this interface like:
     * Tools.Func myFunc = () -> { }
     */
    public static interface Func extends Runnable{
    	public void run();
    }
    
    //run this method using Func or Runnable object
    public static long countTime(Func toRun) {
    	long now1 = System.currentTimeMillis();
    	toRun.run();
	    long now2 = System.currentTimeMillis();
	    Tools.print("Ellapsed milliseconds: ",now2-now1,"\n");
	    return now2-now1;
    }
    
    public static boolean isSameType(Object obj1, Object obj2) {
    	if(obj1.getClass() == obj2.getClass()) {
    		return true;
    	}
    	return false;
    }
    public static boolean isSameType(Object ob, Class cl) {
    	if(ob.getClass() == cl) {
    		return true;
    	}
    	return false;
    }
}
