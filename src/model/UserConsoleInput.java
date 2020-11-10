package model;

import java.io.IOException;
import java.util.Scanner;

public class UserConsoleInput {
	private String input = "";
	private Scanner inputReader = new Scanner(System.in);
	private Settings props = new Settings();
	private Logger logger;
	private ExceptionHandler eHandler;
	
	public UserConsoleInput(){
		logger = new Logger("log.txt");
		eHandler = new ExceptionHandler(logger);
	}
	
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
		
		logger.printLog("Enter Road Patrol info\n");
		logger.printLog("Max body height: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		rp.setMaxBodyHeightAvailable(Float.parseFloat(input));
		
		logger.printLog("Max speed: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		rp.setMaxSpeedAvailable(Float.parseFloat(input));
		
		logger.printLog("Max weight: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
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
		
		logger.printLog("Enter Passenger Car info\n");
		logger.printLog("Brand: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		pc.setBrand(input);
		
		logger.printLog("Is radio on: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		pc.getRadio().setOn(Boolean.parseBoolean(input));
		
		logger.printLog("Set radio station: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		pc.getRadio().setStation(Float.parseFloat(input));
		
		logger.printLog("Speed: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
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
		
		logger.printLog("Enter Truck info\n");
		logger.printLog("Body height: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		t.setBodyHeight(Float.parseFloat(input));
		
		logger.printLog("Weight: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		t.setWeight(Float.parseFloat(input));
		
		logger.printLog("Brand: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		t.setBrand(input);
		
		logger.printLog("Is radio on: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		t.getRadio().setOn(Boolean.parseBoolean(input));
		
		logger.printLog("Set radio station: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
		t.getRadio().setStation(Float.parseFloat(input));
		
		logger.printLog("Speed: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);
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
		try {
			props.load();
		}
		catch (IOException e) {
			eHandler.handle(e);
		}
		
		try {
			DataBase.getInstance("data.txt").load();
		}
		catch (IOException e) {
			eHandler.handle(e);
		}
		catch (ClassNotFoundException e) {
			eHandler.handle(e);
		}
		
		if(props.isFirstStart()) {	
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
		
		try {
			DataBase.getInstance().upload();
		}
		catch (IOException e) {
			eHandler.handle(e);
		}
		
		try {
			props.upload();
		}
		catch (IOException e) {
			eHandler.handle(e);
		}
	}
	
	private void interact() {
		Tools.print("Start.\n");
		
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
		
		Tools.print("Exit.\n");		
	}
	private void interactLog() {
		logger.printLog("Start.\n");
		
		if(props.isDoTests()) {
			doTestsLog();
		}
		
		logger.printLog("Welcome - ", props.getUsername(), "\n");
		
		if(props.isRoot()) {
			logger.printLog("You are in a root mode\n");
		}
		else {
			logger.printLog("You are in a user mode\n");
		}
		
		boolean pass = false;
		while(!pass) {
			logger.printLog("Enter your password: ");
			input = inputReader.nextLine();
			logger.appendToLog(input);
			if(props.getPassword().equals(input)) {
				pass = true;
			}
		}
		
		boolean end = false;
		while(!end) {
			end = userMenuLog();
		}
		
		logger.printLog("Exit.\n");
	}
	
	private void doTests() {
		Tools.print("Start tests\n");
		
		float addTime = 0;
		float removeTime = 0;
		
		int iterationAmount = 10;
		int addAmount = iterationAmount;
		int removeAmount = iterationAmount/10;
		
		for(int i = 0; i < addAmount; i++) {
			Tools.Func o = () -> {
//				DataBase.getInstance().addRndPassCar();
				DataBase.getInstance().addRndPassCarLinked();
			};
			float operationTime = (float)Tools.countTime(o)/1000;
			addTime += operationTime;
			
//			int addIndex = DataBase.getInstance().getPassCars().size()-1;
			int addIndex = DataBase.getInstance().getPassCarsLinked().size()-1;
			Tools.print("Add element at ", addIndex, " in ", operationTime, "s\n");
		}
		for(int i = 0; i < removeAmount; i++) {
//			int removeIndex = Tools.genRandomNum(0, DataBase.getInstance().getPassCars().size()-1);
			int removeIndex = Tools.genRandomNum(0, DataBase.getInstance().getPassCarsLinked().size()-1);
			
			Tools.Func o = () -> {
//				DataBase.getInstance().removePassCarAt(removeIndex);
				DataBase.getInstance().removePassCarAtLinked(removeIndex);
			};
			float operationTime = (float)Tools.countTime(o)/1000;
			removeTime += operationTime;
			
			Tools.print("Remove element at ", removeIndex, " in ", operationTime, "s\n");
		}
		
		Tools.print("Elapsed time: ", addTime + removeTime, "s\n");
		Tools.print("Elapsed median time: ", (addTime + removeTime)/(addAmount + removeAmount), "s\n");
		Tools.print("Add time: ", addTime, "s, elemets ", addAmount, "\n");
		Tools.print("Add median time: ", addTime/addAmount, "s, elemets ", addAmount, "\n");
		Tools.print("Remove time: ", removeTime, "s, elements ", removeAmount, "\n");
		Tools.print("Remove median time: ", removeTime/removeAmount, "s, elements ", removeAmount, "\n");
		Tools.print("End tests\n");
	}
	private void doTestsLog() {
		logger.printLog("Start tests\n");
		
		float addTime = 0;
		float removeTime = 0;
		
		int iterationAmount = 10;
		int addAmount = iterationAmount;
		int removeAmount = iterationAmount/10;
		
		for(int i = 0; i < addAmount; i++) {
			Tools.Func o = () -> {
				DataBase.getInstance().addRndPassCar();
//				DataBase.getInstance().addRndPassCarLinked();
			};
			float operationTime = (float)Tools.countTime(o)/1000;
			addTime += operationTime;
			
			int addIndex = DataBase.getInstance().getPassCars().size()-1;
//			int addIndex = DataBase.getInstance().getPassCarsLinked().size()-1;
			logger.printLog("Add element at ", addIndex, " in ", operationTime, "s\n");
		}
		for(int i = 0; i < removeAmount; i++) {
			int removeIndex = Tools.genRandomNum(0, DataBase.getInstance().getPassCars().size()-1);
//			int removeIndex = Tools.genRandomNum(0, DataBase.getInstance().getPassCarsLinked().size()-1);
			
			Tools.Func o = () -> {
				DataBase.getInstance().removePassCarAt(removeIndex);
//				DataBase.getInstance().removePassCarAtLinked(removeIndex);
			};
			float operationTime = (float)Tools.countTime(o)/1000;
			removeTime += operationTime;
			
			logger.printLog("Remove element at ", removeIndex, " in ", operationTime, "s\n");
		}
		
		logger.printLog("Elapsed time: ", addTime + removeTime, "s\n");
		logger.printLog("Elapsed median time: ", (addTime + removeTime)/(addAmount + removeAmount), "s\n");
		logger.printLog("Add time: ", addTime, "s, elemets ", addAmount, "\n");
		logger.printLog("Add median time: ", addTime/addAmount, "s, elemets ", addAmount, "\n");
		logger.printLog("Remove time: ", removeTime, "s, elements ", removeAmount, "\n");
		logger.printLog("Remove median time: ", removeTime/removeAmount, "s, elements ", removeAmount, "\n");
		logger.printLog("End tests\n");
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
			if(Integer.parseInt(input) < DataBase.getInstance().getPassCars().size()) {
				Tools.print(getPassCarInfo(DataBase.getInstance().getPassCarAt(Integer.parseInt(input))),'\n');
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 5:
			Tools.print("Enter index of element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < DataBase.getInstance().getTrucks().size()) {
				Tools.print(getTruckInfo(DataBase.getInstance().getTruckAt(Integer.parseInt(input))),'\n');
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 6:
			Tools.print("Enter index of element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < DataBase.getInstance().getPatrols().size()) {
				Tools.print(getPatrolPointInfo(DataBase.getInstance().getRoadPatrolAt(Integer.parseInt(input))),'\n');
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 7:
			Tools.print("Enter index of deleting element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < DataBase.getInstance().getPassCars().size()) {
				DataBase.getInstance().removePassCarAt(Integer.parseInt(input));
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 8:
			Tools.print("Enter index of deleting element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < DataBase.getInstance().getTrucks().size()) {
				DataBase.getInstance().removeTruckAt(Integer.parseInt(input));
			}
			else {
				Tools.print("Exceeded size of array\n");
			}
			break;
		case 9:
			Tools.print("Enter index of deleting element: ");
			input = inputReader.nextLine();
			if(Integer.parseInt(input) < DataBase.getInstance().getPatrols().size()) {
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
		logger.printLog("Enter 0 to exit\n",
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
			logger.printLog("Enter 10 to set debug mode\n",
							"Enter 11 to set test mode\n");
		}
		
		logger.printLog("You enter: ");
		input = inputReader.nextLine();
		logger.appendToLog(input);		
		
		if(props.isRoot()) {
			String localIn;
			if(Integer.parseInt(input) == 10) {
				logger.printLog("Activate logging?: ");
				localIn = inputReader.nextLine();
				logger.appendToLog(localIn);
				
				props.setDoLog(Boolean.parseBoolean(localIn));
			}
			if(Integer.parseInt(input) == 11) {
				logger.printLog("Activate tests?: ");
				localIn = inputReader.nextLine();
				logger.appendToLog(localIn);
				
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
			logger.printLog("Enter index of element: ");
			input = inputReader.nextLine();
			logger.appendToLog(input);	
			
			if(Integer.parseInt(input) < DataBase.getInstance().getPassCars().size()) {
				logger.printLog(getPassCarInfo(DataBase.getInstance().getPassCarAt(Integer.parseInt(input))),'\n');
			}
			else {
				logger.printLog("Exceeded size of array\n");
			}
			break;
		case 5:
			logger.printLog("Enter index of element: ");
			input = inputReader.nextLine();
			logger.appendToLog(input);	
			
			if(Integer.parseInt(input) < DataBase.getInstance().getTrucks().size()) {
				logger.printLog(getTruckInfo(DataBase.getInstance().getTruckAt(Integer.parseInt(input))),'\n');
			}
			else {
				logger.printLog("Exceeded size of array\n");
			}
			break;
		case 6:
			logger.printLog("Enter index of element: ");
			input = inputReader.nextLine();
			logger.appendToLog(input);	
			
			if(Integer.parseInt(input) < DataBase.getInstance().getPatrols().size()) {
				logger.printLog(getPatrolPointInfo(DataBase.getInstance().getRoadPatrolAt(Integer.parseInt(input))),'\n');
			}
			else {
				logger.printLog("Exceeded size of array\n");
			}
			break;
		case 7:
			logger.printLog("Enter index of deleting element: ");
			input = inputReader.nextLine();
			logger.appendToLog(input);	
			
			if(Integer.parseInt(input) < DataBase.getInstance().getPassCars().size()) {
				DataBase.getInstance().removePassCarAt(Integer.parseInt(input));
			}
			else {
				logger.printLog("Exceeded size of array\n");
			}
			break;
		case 8:
			logger.printLog("Enter index of deleting element: ");
			input = inputReader.nextLine();
			logger.appendToLog(input);	
			
			if(Integer.parseInt(input) < DataBase.getInstance().getTrucks().size()) {
				DataBase.getInstance().removeTruckAt(Integer.parseInt(input));
			}
			else {
				logger.printLog("Exceeded size of array\n");
			}
			break;
		case 9:
			logger.printLog("Enter index of deleting element: ");
			input = inputReader.nextLine();
			logger.appendToLog(input);	
			
			if(Integer.parseInt(input) < DataBase.getInstance().getPatrols().size()) {
				DataBase.getInstance().removeRoadPatrolAt(Integer.parseInt(input));
			}
			else {
				logger.printLog("Exceeded size of array\n");
			}
			break;
		default:
			break;
		}
		return false;
	}
}
