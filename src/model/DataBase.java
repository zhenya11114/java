package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

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
	public ArrayList<PassengerCar> getPassCars() {
		return passCars;
	}
	public void setPassCars(ArrayList<PassengerCar> passCars) {
		this.passCars = passCars;
	}
	public ArrayList<Truck> getTrucks() {
		return trucks;
	}
	public void setTrucks(ArrayList<Truck> trucks) {
		this.trucks = trucks;
	}
	public ArrayList<RoadPatrol> getPatrols() {
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
	
	public void addRndPassCar() {
		PassengerCar c = new PassengerCar();
		c.setBrand(Tools.genRandomString(5));
		c.setSpeed(Tools.genRandomNum(10, 150));
		
		c.getRadio().setOn(Tools.genRandomBool());
		c.getRadio().setStation(Tools.genRandomNum(100, 150));
		passCars.add(c);
	}
	public void addRndTruck() {
		Truck t = new Truck();
		t.setBrand(Tools.genRandomString(5));
		t.setSpeed(Tools.genRandomNum(10, 150));
		
		t.getRadio().setOn(Tools.genRandomBool());
		t.getRadio().setStation(Tools.genRandomNum(100, 150));
		
		t.setBodyHeight(Tools.genRandomNum(0, 15));
		t.setWeight(Tools.genRandomNum(1000, 10000));
		trucks.add(t);
	}
	public void addRndRoadPatrol() {
		RoadPatrol rp = new RoadPatrol();
		rp.setMaxBodyHeightAvailable(Tools.genRandomNum(5, 10));
		rp.setMaxSpeedAvailable(Tools.genRandomNum(50, 80));
		rp.setMaxWeightAvailable(Tools.genRandomNum(5000, 6000));
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
	public void upload() throws IOException{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
			oos.writeObject(base);
		}
	}
	public void load() throws ClassNotFoundException, IOException{
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
				base = (DataBase)in.readObject();
			}
			catch(EOFException e) {
				
			}
		}
		else {
			Tools.file.createFile(filePath, "");
		}
	}
	
	//for tests, remove later
	private LinkedList<PassengerCar> passCarsLinked = new LinkedList<PassengerCar>();
	public void addRndPassCarLinked() {
		PassengerCar c = new PassengerCar();
		c.setBrand(Tools.genRandomString(5));
		c.setSpeed(Tools.genRandomNum(10, 150));
		
		c.getRadio().setOn(Tools.genRandomBool());
		c.getRadio().setStation(Tools.genRandomNum(100, 150));
		passCarsLinked.add(c);
	}
	public void removePassCarAtLinked(int index) {
		passCarsLinked.remove(index);
	}
	public LinkedList<PassengerCar> getPassCarsLinked() {
		return passCarsLinked;
	}
	public void setPassCarsLinked(LinkedList<PassengerCar> passCars) {
		this.passCarsLinked = passCars;
	}
}
