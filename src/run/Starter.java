package run;

import model.*;

public class Starter {
	public static void main(String[] args) {
		UserConsoleInput uci = new UserConsoleInput();
		uci.start();
//		float maxSpeedAvailable = 100;
//		float maxBodyHeightAvailable = 10;
//		float maxWeightAvailable = 2000;
//		
//		RoadPatrol patrol = new RoadPatrol(maxSpeedAvailable, maxBodyHeightAvailable, maxWeightAvailable);
//		PassengerCar passCar = new PassengerCar("DeLorean", 999F, 100.1F, true);
//		Truck truck = new Truck("Kamaz", 60F, 100.1F, false, 2000F, 5F);
//		
////		RoadPatrol patrol = uci.createPatrolPoint();
////		PassengerCar passCar = uci.createPassCar();
////		Truck truck = uci.createTruck();
//		
//		uci.getIfPassInfo(passCar, patrol);
//		uci.getIfPassInfo(truck, patrol);
//		uci.getPatrolPointInfo(patrol);
//		
//		passCar.getRadio().playTunes();
//		truck.getRadio().playTunes();
//		
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
}