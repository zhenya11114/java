package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Properties implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private boolean isRoot;
	private boolean doLog;
	private boolean doTests;
	private boolean isFirstStart;
	
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


	public Properties(){
		super();
		
		username = "default";
		password = "default";
		isRoot = false;
		doLog = false;
		doTests = false;
		isFirstStart = true;
	}
	
	public void upload() {
		Tools.file.serialize("props.txt", username, password, isRoot, doLog, doTests, isFirstStart);
		
		//Tools.file.createFile(dbFilePath, toString(), true);
	}
	public void load() {
		ArrayList<Object> list = Tools.file.deserialize("props.txt");
		if (list.size() > 0) {
			username = (String)list.get(0);
			password = (String)list.get(1);
			isRoot = (boolean)list.get(2);
			doLog = (boolean)list.get(3);
			doTests = (boolean)list.get(4);
			isFirstStart = (boolean)list.get(5);
		}
	}
}
