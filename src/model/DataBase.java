package model;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataBase implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static DataBase base = null;
	
	private String filePath;
	
	private ArrayList<PassengerCar> passCars = new ArrayList<PassengerCar>();
	private ArrayList<Truck> trucks= new ArrayList<Truck>();;
	private ArrayList<RoadPatrol> patrols= new ArrayList<RoadPatrol>();

	public static DataBase getInstance() {
		if(base == null) {
			base = new DataBase();
		}
		return base;
	}
	public static DataBase getInstance(String dbFilePath) {
		if(base == null) {
			base = new DataBase(dbFilePath);
		}
		return base;
	}

	private DataBase() {
		super();
	}
	private DataBase(String dbFilePath) {
		super();
		
		this.filePath = dbFilePath;
		
	}
	
	public String getDbFilePath() {
		return filePath;
	}
	public void setDbFilePath(String dbFilePath) {
		this.filePath = dbFilePath;
	}
	public List<PassengerCar> getPassCars() {
		return passCars;
	}
	public void setPassCars(ArrayList<PassengerCar> passCars) {
		this.passCars = passCars;
	}
	public List<Truck> getTrucks() {
		return trucks;
	}
	public void setTrucks(ArrayList<Truck> trucks) {
		this.trucks = trucks;
	}
	public List<RoadPatrol> getPatrols() {
		return patrols;
	}
	public void setPatrols(ArrayList<RoadPatrol> patrols) {
		this.patrols = patrols;
	}
	
	public void addPassCar(PassengerCar c) {
		passCars.add(c);
	}
	public void addTruck(Truck t) {
		trucks.add(t);
	}
	public void addRoadPatrol(RoadPatrol rp) {
		patrols.add(rp);
	}
	
	public PassengerCar getPassCarAt(int index) {
		return passCars.get(index);
	}
	public Truck getTruckAt(int index) {
		return trucks.get(index);
	}
	public RoadPatrol getRoadPatrolAt(int index) {
		return patrols.get(index);
	}
	
	public void removePassCarAt(int index) {
		passCars.remove(index);
	}
	public void removeTruckAt(int index) {
		trucks.remove(index);
	}
	public void removeRoadPatrolAt(int index) {
		patrols.remove(index);
	}
	
	@Override
	public String toString() {
		String result = "";
		
		for(PassengerCar passCar : passCars) {
			result += "PassengerCar " + passCar.toString() + '\n';
		}
		for(Truck truck : trucks) {
			result += "Truck " + truck.toString() + '\n';
		}
		for(RoadPatrol patrol : patrols) {
			result += "RoadPatrol " + patrol.toString() + '\n';
		}
		return result;
	}
	public void upload() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
			oos.writeObject(base);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void load() {
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
				base = (DataBase)in.readObject();
			}
			catch(IOException e) {
			    e.printStackTrace();
			}
			catch(ClassNotFoundException e) {
			    e.printStackTrace();
			}
		}
		else {
			Tools.file.createFile(filePath, "");
		}
	}
}
