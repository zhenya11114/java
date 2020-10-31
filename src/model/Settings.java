package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

//implements Properties
public class Settings{
	private String username;
	private String password;
	private boolean isRoot;
	private boolean doLog;
	private boolean doTests;
	private boolean isFirstStart;
	
	String filePath = "props.properties";
	
	static private Properties props = new Properties();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean isDoLog() {
		return doLog;
	}
	public void setDoLog(boolean doLog) {
		this.doLog = doLog;
	}

	public boolean isDoTests() {
		return doTests;
	}
	public void setDoTests(boolean doTests) {
		this.doTests = doTests;
	}
	
	public boolean isFirstStart() {
		return isFirstStart;
	}
	public void setFirstStart(boolean isFirstStart) {
		this.isFirstStart = isFirstStart;
	}

	public Settings(){
		super();
		
		username = "default";
		password = "default";
		isRoot = false;
		doLog = false;
		doTests = false;
		isFirstStart = true;
	}
	
	public void upload() {
		try (OutputStream output = new FileOutputStream(filePath)) {
            props.setProperty("username", username);
            props.setProperty("password", password);
            
            props.setProperty("isRoot", Boolean.toString(isRoot));
            props.setProperty("doLog", Boolean.toString(doLog));
            props.setProperty("doTests", Boolean.toString(doTests));
            props.setProperty("isFirstStart", Boolean.toString(isFirstStart));

            props.store(output, null);
        }
		catch (IOException io) {
            io.printStackTrace();
        }
	}
	public void load() {
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) {
			try(FileInputStream fis = new FileInputStream(f)) {
				props.load(fis);
				
				username = props.getProperty("username");
				password = props.getProperty("password");
				
				isRoot = Boolean.parseBoolean(props.getProperty("isRoot"));
				doLog = Boolean.parseBoolean(props.getProperty("doLog"));
				doTests = Boolean.parseBoolean(props.getProperty("doTests"));
				isFirstStart = Boolean.parseBoolean(props.getProperty("isFirstStart"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			Tools.file.createFile(filePath, "");
		}
	}
}
