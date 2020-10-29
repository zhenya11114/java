package model;

import java.util.Scanner;

public class UserConsoleInput {
	private String input = "";
	private Scanner inputReader = new Scanner(System.in);
	private Properties props = new Properties();
	
	DataBase db;
	
	public RoadPatrol createPatrolPoint() {
		RoadPatrol rp = new RoadPatrol();
		Tools.print("Enter Road Patrol info\n");
		Tools.print("Max body height: ");
		input = inputReader.nextLine();
		rp.setMaxBodyHeightAvailable(Float.parseFloat(input));
		Tools.print("Max speed: ");
		input = inputReader.nextLine();
		rp.setMaxSpeedAvailable(Float.parseFloat(input));
		Tools.print("Max weight: ");
		input = inputReader.nextLine();
		rp.setMaxWeightAvailable(Float.parseFloat(input));
		
		return rp;
	}
	public RoadPatrol createPatrolPointLog() {
		RoadPatrol rp = new RoadPatrol();
		
		Tools.printLog("log.txt", "Enter Road Patrol info\n");
		Tools.printLog("log.txt", "Max body height: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		rp.setMaxBodyHeightAvailable(Float.parseFloat(input));
		
		Tools.printLog("log.txt", "Max speed: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		rp.setMaxSpeedAvailable(Float.parseFloat(input));
		
		Tools.printLog("log.txt", "Max weight: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		rp.setMaxWeightAvailable(Float.parseFloat(input));
		
		return rp;
	}
	
	public PassengerCar createPassCar() {
		PassengerCar pc = new PassengerCar();
		
		Tools.print("Enter Passenger Car info\n");
		Tools.print("Brand: ");
		input = inputReader.nextLine();
		pc.setBrand(input);
		Tools.print("Is radio on: ");
		input = inputReader.nextLine();
		pc.getRadio().setOn(Boolean.parseBoolean(input));
		Tools.print("Set radio station: ");
		input = inputReader.nextLine();
		pc.getRadio().setStation(Float.parseFloat(input));
		Tools.print("Speed: ");
		input = inputReader.nextLine();
		pc.setSpeed(Float.parseFloat(input));
		
		return pc;
	}
	public PassengerCar createPassCarLog() {
		PassengerCar pc = new PassengerCar();
		
		Tools.printLog("log.txt", "Enter Passenger Car info\n");
		Tools.printLog("log.txt", "Brand: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		pc.setBrand(input);
		
		Tools.printLog("log.txt", "Is radio on: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		pc.getRadio().setOn(Boolean.parseBoolean(input));
		
		Tools.printLog("log.txt", "Set radio station: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		pc.getRadio().setStation(Float.parseFloat(input));
		
		Tools.printLog("log.txt", "Speed: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		pc.setSpeed(Float.parseFloat(input));
		
		return pc;
	}
	
	public Truck createTruck() {
		Truck t = new Truck();
		
		Tools.print("Enter Truck info\n");
		Tools.print("Body height: ");
		input = inputReader.nextLine();
		t.setBodyHeight(Float.parseFloat(input));
		Tools.print("Weight: ");
		input = inputReader.nextLine();
		t.setWeight(Float.parseFloat(input));
		
		Tools.print("Brand: ");
		input = inputReader.nextLine();
		t.setBrand(input);
		Tools.print("Is radio on: ");
		input = inputReader.nextLine();
		t.getRadio().setOn(Boolean.parseBoolean(input));
		Tools.print("Set radio station: ");
		input = inputReader.nextLine();
		t.getRadio().setStation(Float.parseFloat(input));
		Tools.print("Speed: ");
		input = inputReader.nextLine();
		t.setSpeed(Float.parseFloat(input));
		
		return t;
	}
	public Truck createTruckLog() {
		Truck t = new Truck();
		
		Tools.printLog("log.txt", "Enter Truck info\n");
		Tools.printLog("log.txt", "Body height: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		t.setBodyHeight(Float.parseFloat(input));
		
		Tools.printLog("log.txt", "Weight: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		t.setWeight(Float.parseFloat(input));
		
		Tools.printLog("log.txt", "Brand: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		t.setBrand(input);
		
		Tools.printLog("log.txt", "Is radio on: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		t.getRadio().setOn(Boolean.parseBoolean(input));
		
		Tools.printLog("log.txt", "Set radio station: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		t.getRadio().setStation(Float.parseFloat(input));
		
		Tools.printLog("log.txt", "Speed: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);
		t.setSpeed(Float.parseFloat(input));
		
		return t;
	}
	
	public String getPatrolPointInfo(RoadPatrol rp) {
		return "Road patrol: " + rp.toString() + '\n';
	}
	public String getPassCarInfo(PassengerCar pc) {
		return "Passenger car: " + pc.toString();
	}
	public String getTruckInfo(Truck t) {
		return "Truck: " + t.toString() + '\n';
	}
	public String getIfPassInfo(Car c, RoadPatrol rp) {
		String res = "";
		if(Tools.isSameType(c, PassengerCar.class))
			res = "The passenger car " + (!rp.pass(c) ? "passed" : "not passed") + '\n';
		if(Tools.isSameType(c, Truck.class))
			res = "The truck " + (!rp.pass(c) ? "passed" : "not passed") + '\n';
		
		return res;
	}
	
	@Override
	protected void finalize(){
		inputReader.close();
	}

	public void start() {
		props.load();
		
		if(props.isFirstStart()) {
			Tools.file.createFile("log.txt", "");
			Tools.file.createFile("props.txt", "");
			
			props.setFirstStart(false);
			
			Tools.print("Enter your username: ");
			input = inputReader.nextLine();
			props.setUsername(input);
			
			Tools.print("Enter your password: ");
			input = inputReader.nextLine();
			props.setPassword(input);
			
			props.setRoot(true);
		}
		
		if(props.isDoLog()) {
			interactLog();
		}
		else {
			interact();	
		}
	}
	
	private void interact() {
		Tools.print("Start.");
		
		if(props.isDoTests()) {
			doTests();
		}
		
		Tools.print("Welcome - ", props.getUsername(), "\n");
		
		if(props.isRoot()) {
			Tools.print("You are in a root mode\n");
		}
		else {
			Tools.print("You are in a user mode\n");
		}
		
		boolean pass = false;
		while(!pass) {
			Tools.print("Enter your password: ");
			input = inputReader.nextLine();
			if(props.getPassword().equals(input)) {
				pass = true;
			}
		}
		
		boolean end = false;
		while(!end) {
			end = userMenu();
		}
		
		props.upload();
		Tools.print("Exit.\n");		
	}
	private void interactLog() {
		Tools.printLog("log.txt", "Start.");
		
		if(props.isDoTests()) {
			doTestsLog();
		}
		
		Tools.printLog("log.txt", "Welcome - ", props.getUsername(), "\n");
		
		if(props.isRoot()) {
			Tools.printLog("log.txt", "You are in a root mode\n");
		}
		else {
			Tools.printLog("log.txt", "You are in a user mode\n");
		}
		
		boolean pass = false;
		while(!pass) {
			Tools.printLog("log.txt", "Enter your password: ");
			input = inputReader.nextLine();
			Tools.file.appendToFile("log.txt", input);
			if(props.getPassword().equals(input)) {
				pass = true;
			}
		}
		
		boolean end = false;
		while(!end) {
			end = userMenuLog();
		}
		
		props.upload();
		Tools.printLog("log.txt", "Exit.\n");
	}
	
	private void doTests() {
		Tools.print("Doing tests\n");
//		float maxSpeedAvailable = 100;
//		float maxBodyHeightAvailable = 10;
//		float maxWeightAvailable = 2000;
//		
//		RoadPatrol patrol = new RoadPatrol(maxSpeedAvailable, maxBodyHeightAvailable, maxWeightAvailable);
//		PassengerCar passCar = new PassengerCar("DeLorean", 999F, 100.1F, true);
//		Truck truck = new Truck("Kamaz", 60F, 100.1F, false, 2000F, 5F);
		
//		RoadPatrol patrol = uci.createPatrolPoint();
//		PassengerCar passCar = uci.createPassCar();
//		Truck truck = uci.createTruck();
		
//		getIfPassInfo(passCar, patrol);
//		getIfPassInfo(truck, patrol);
//		getPatrolPointInfo(patrol);
//		
//		passCar.getRadio().playTunes();
//		truck.getRadio().playTunes();
		
//		DataBase.getInstance("data.txt");
//		DataBase.getInstance().load();
//		
//		DataBase.getInstance().addPassCar(passCar);
//		DataBase.getInstance().addTruck(truck);
//		DataBase.getInstance().addRoadPatrol(patrol);
//		
//		DataBase.getInstance().upload();
//		
//		Tools.print(DataBase.getInstance().getPassCars().size());
	}
	private void doTestsLog() {
		Tools.printLog("log.txt", "Doing tests\n");
	}
	
	private boolean userMenu() {
		Tools.print("Enter 0 to exit\n",
					"Enter 1 to add new passenger car\n",
					"Enter 2 to add new truck\n",
					"Enter 3 to add new patrol point\n",
					"Enter 4 to get passenger car data\n",
					"Enter 5 to get truck data\n",
					"Enter 6 to get patrol point data\n",
					"Enter 7 to remove passenger data\n",
					"Enter 8 to remove truck data\n",
					"Enter 9 to remove patrol point data\n"
				);
		if(props.isRoot()) {
			Tools.print("Enter 10 to set debug mode\n",
						"Enter 11 to set test mode\n");
		}
		
		Tools.print("You enter: ");
		input = inputReader.nextLine();
		
		if(props.isRoot()) {
			String localIn;
			if(Integer.parseInt(input) == 10) {
				Tools.print("Activate logging?: ");
				localIn = inputReader.nextLine();
				props.setDoLog(Boolean.parseBoolean(localIn));
			}
			if(Integer.parseInt(input) == 11) {
				Tools.print("Activate tests?: ");
				localIn = inputReader.nextLine();
				props.setDoTests(Boolean.parseBoolean(localIn));
			}
		}
		
		switch(Integer.parseInt(input)) {
		case 0:
			return true;
		case 1:
			PassengerCar passCar = createPassCar();
			DataBase.getInstance().addPassCar(passCar);			
			break;
		case 2:
			Truck truck = createTruck();
			DataBase.getInstance().addTruck(truck);
			break;
		case 3:
			RoadPatrol patrol = createPatrolPoint();
			DataBase.getInstance().addRoadPatrol(patrol);
			break;
		case 4:
			Tools.print("Enter index of element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < db.getInstance().getPassCars().size()) {
				Tools.print(getPassCarInfo(db.getInstance().getPassCarAt(Integer.parseInt(input))));
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 5:
			Tools.print("Enter index of element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < db.getInstance().getTrucks().size()) {
				Tools.print(getTruckInfo(db.getInstance().getTruckAt(Integer.parseInt(input))));
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 6:
			Tools.print("Enter index of element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < db.getInstance().getPatrols().size()) {
				Tools.print(getPatrolPointInfo(db.getInstance().getRoadPatrolAt(Integer.parseInt(input))));
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 7:
			Tools.print("Enter index of deleting element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < db.getInstance().getPatrols().size()) {
				DataBase.getInstance().removePassCarAt(Integer.parseInt(input));
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 8:
			Tools.print("Enter index of deleting element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < db.getInstance().getPatrols().size()) {
				DataBase.getInstance().removeTruckAt(Integer.parseInt(input));
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 9:
			Tools.print("Enter index of deleting element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < db.getInstance().getPatrols().size()) {
				DataBase.getInstance().removeRoadPatrolAt(Integer.parseInt(input));
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		default:
			break;
		}
		return false;
	}
	private boolean userMenuLog() {
		Tools.printLog("log.txt",
				"Enter 0 to exit\n",
				"Enter 1 to add new passenger car\n",
				"Enter 2 to add new truck\n",
				"Enter 3 to add new patrol point\n",
				"Enter 4 to get passenger car data\n",
				"Enter 5 to get truck data\n",
				"Enter 6 to get patrol point data\n",
				"Enter 7 to remove passenger data\n",
				"Enter 8 to remove truck data\n",
				"Enter 9 to remove patrol point data\n"
			);
		if(props.isRoot()) {
			Tools.printLog("log.txt", "Enter 10 to set debug mode\n",
						"Enter 11 to set test mode\n");
		}
		
		Tools.printLog("log.txt", "You enter: ");
		input = inputReader.nextLine();
		Tools.file.appendToFile("log.txt", input);		
		
		if(props.isRoot()) {
			String localIn;
			if(Integer.parseInt(input) == 10) {
				Tools.printLog("log.txt", "Activate logging?: ");
				localIn = inputReader.nextLine();
				Tools.file.appendToFile("log.txt", localIn);
				
				props.setDoLog(Boolean.parseBoolean(localIn));
			}
			if(Integer.parseInt(input) == 11) {
				Tools.printLog("log.txt", "Activate tests?: ");
				localIn = inputReader.nextLine();
				Tools.file.appendToFile("log.txt", localIn);
				
				props.setDoTests(Boolean.parseBoolean(localIn));
			}
		}
		
		switch(Integer.parseInt(input)) {
		case 0:
			return true;
		case 1:
			PassengerCar passCar = createPassCarLog();
			DataBase.getInstance().addPassCar(passCar);			
			break;
		case 2:
			Truck truck = createTruckLog();
			DataBase.getInstance().addTruck(truck);
			break;
		case 3:
			RoadPatrol patrol = createPatrolPointLog();
			DataBase.getInstance().addRoadPatrol(patrol);
			break;
		case 4:
			Tools.printLog("log.txt", "Enter index of element: ");
			input = inputReader.nextLine();
			Tools.file.appendToFile("log.txt", input);	
			
			if(Integer.parseInt(input) < db.getInstance().getPassCars().size()) {
				Tools.printLog("log.txt", getPassCarInfo(db.getInstance().getPassCarAt(Integer.parseInt(input))));
			}
			else {
				Tools.printLog("log.txt", "Exceeded size of array\n");
			}
			break;
		case 5:
			Tools.printLog("log.txt", "Enter index of element: ");
			input = inputReader.nextLine();
			Tools.file.appendToFile("log.txt", input);	
			
			if(Integer.parseInt(input) < db.getInstance().getTrucks().size()) {
				Tools.printLog("log.txt", getTruckInfo(db.getInstance().getTruckAt(Integer.parseInt(input))));
			}
			else {
				Tools.printLog("log.txt", "Exceeded size of array\n");
			}
			break;
		case 6:
			Tools.printLog("log.txt", "Enter index of element: ");
			input = inputReader.nextLine();
			Tools.file.appendToFile("log.txt", input);	
			
			if(Integer.parseInt(input) < db.getInstance().getPatrols().size()) {
				Tools.printLog("log.txt", getPatrolPointInfo(db.getInstance().getRoadPatrolAt(Integer.parseInt(input))));
			}
			else {
				Tools.printLog("log.txt", "Exceeded size of array\n");
			}
			break;
		case 7:
			Tools.printLog("log.txt", "Enter index of deleting element: ");
			input = inputReader.nextLine();
			Tools.file.appendToFile("log.txt", input);	
			
			if(Integer.parseInt(input) < db.getInstance().getPatrols().size()) {
				DataBase.getInstance().removePassCarAt(Integer.parseInt(input));
			}
			else {
				Tools.printLog("log.txt", "Exceeded size of array\n");
			}
			break;
		case 8:
			Tools.printLog("log.txt", "Enter index of deleting element: ");
			input = inputReader.nextLine();
			Tools.file.appendToFile("log.txt", input);	
			
			if(Integer.parseInt(input) < db.getInstance().getPatrols().size()) {
				DataBase.getInstance().removeTruckAt(Integer.parseInt(input));
			}
			else {
				Tools.printLog("log.txt", "Exceeded size of array\n");
			}
			break;
		case 9:
			Tools.printLog("log.txt", "Enter index of deleting element: ");
			input = inputReader.nextLine();
			Tools.file.appendToFile("log.txt", input);	
			
			if(Integer.parseInt(input) < db.getInstance().getPatrols().size()) {
				DataBase.getInstance().removeRoadPatrolAt(Integer.parseInt(input));
			}
			else {
				Tools.printLog("log.txt", "Exceeded size of array\n");
			}
			break;
		default:
			break;
		}
		return false;
	}
}
