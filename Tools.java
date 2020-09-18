package prjct;

import java.io.*;
import java.util.Scanner;

public class Tools
{
	public static class file{
		/* method create a new file and write content in
	    /* method doesn't rewrite the content
	     */
	    public static void createFile(String path, String content) {
	    	try {
	    		File f = new File(path);
				if (f.createNewFile()) {
					System.out.println("File created: " + f.getName());
				}
				else {
					System.out.println("File already exists.");
				}
				
				FileWriter writer = new FileWriter(f);
		        writer.write(content);
		        writer.close();
			}
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
	    }
	    
	    /* method create a new file and write content in
	    /* method can rewrite the content if stated
	     */
	    public static void createFile(String path, String content, boolean doRewrite) {
	    	try {
	    		File f = new File(path);
				if (f.createNewFile() || doRewrite) {
					System.out.println("File created: " + f.getName());
				}
				else {
					System.out.println("File already exists.");
				}
				
				FileWriter writer = new FileWriter(f);
		        writer.write(content);
		        writer.close();
			}
			catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
	    }
	    
	    //method to read content of the file
	    public static String readFile(String path) {
	    	String result = "";
	    	try {
	    		File f = new File(path);
	    		FileReader reader = new FileReader(f);
	    		Scanner scan = new Scanner(reader);
	    		while (scan.hasNextLine()) {
	               result += scan.nextLine() + "\n";
	            }
	    		scan.close();
	    		reader.close();
	    	}
	    	catch(IOException e) {
	    		System.out.println("An error occurred.");
				e.printStackTrace();
	    	}
	    	return result;
	    }
	}
	
	//prints indefinite amount of arguments
    public static <T> void print(final T... args) {
        for (final T i : args) {
            System.out.print(i);
        }
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
}