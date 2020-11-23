package model;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

import graph.GraphFrame;

public class DataBaseOperationGraph {	
	public void build() {
		ArrayList<PassengerCar> restore = DataBase.getInstance().getPassCars();
		
		ArrayList<Float> X = new ArrayList<>();
		ArrayList<Float> YmAdd = new ArrayList<>();
		ArrayList<Float> YmDel = new ArrayList<>();
		ArrayList<Float> YfAdd = new ArrayList<>();
		ArrayList<Float> YfDel = new ArrayList<>();
		
		int mul = 1;
		for(int m = 0; m < 5; m++) {			
			float addTime = 0;
			float removeTime = 0;
			
			int iterationAmount = 10 * mul;
			int addAmount = iterationAmount;
			int removeAmount = iterationAmount/10;
			
			for(int i = 0; i < addAmount; i++) {
				Tools.Func o = () -> {
					DataBase.getInstance().addRndPassCar();
				};
				float operationTime = (float)Tools.countTime(o)/1000;
				addTime += operationTime;
			}
			for(int i = 0; i < removeAmount; i++) {
				int removeIndex = Tools.genRandomNum(0, DataBase.getInstance().getPassCarsLinked().size()-1);
				
				Tools.Func o = () -> {
					DataBase.getInstance().removePassCarAt(removeIndex);
				};
				float operationTime = (float)Tools.countTime(o)/1000;
				removeTime += operationTime;
			}
			
			Tools.print("Elapsed time: ", addTime + removeTime, "s\n");
			Tools.print("Elapsed median time: ", (addTime + removeTime)/(addAmount + removeAmount), "s\n");
			Tools.print("Add time: ", addTime, "s, elemets ", addAmount, "\n");
			Tools.print("Add median time: ", addTime/addAmount, "s, elemets ", addAmount, "\n");
			Tools.print("Remove time: ", removeTime, "s, elements ", removeAmount, "\n");
			Tools.print("Remove median time: ", removeTime/removeAmount, "s, elements ", removeAmount, "\n");
			Tools.print("End tests\n");
			
			float x = addAmount;
			
			X.add(x);
			YfAdd.add(addTime);
			YfDel.add(removeTime);
			YmAdd.add(addTime/addAmount);
			YmDel.add(removeTime/removeAmount);

			mul *= 10;
			
			DataBase.getInstance().setPassCars(restore);
		}
		
		buildGraph(X, YfAdd, "Full add");
		buildGraph(X, YfDel, "Full del");
		buildGraph(X, YmAdd, "Med add");
		buildGraph(X, YmDel, "Med del");
		
		/////////////////////////////////////////////////////////////		
		LinkedList<PassengerCar> restoreLinked = DataBase.getInstance().getPassCarsLinked();
		
		X = new ArrayList<>();
		YmAdd = new ArrayList<>();
		YmDel = new ArrayList<>();
		YfAdd = new ArrayList<>();
		YfDel = new ArrayList<>();
		
		mul = 1;
		for(int m = 0; m < 5; m++) {			
			float addTime = 0;
			float removeTime = 0;
			
			int iterationAmount = 10 * mul;
			int addAmount = iterationAmount;
			int removeAmount = iterationAmount/10;
			
			for(int i = 0; i < addAmount; i++) {
				Tools.Func o = () -> {
					DataBase.getInstance().addRndPassCarLinked();
				};
				float operationTime = (float)Tools.countTime(o)/1000;
				addTime += operationTime;
			}
			for(int i = 0; i < removeAmount; i++) {
				int removeIndex = Tools.genRandomNum(0, DataBase.getInstance().getPassCarsLinked().size()-1);
				
				Tools.Func o = () -> {
					DataBase.getInstance().removePassCarAtLinked(removeIndex);
				};
				float operationTime = (float)Tools.countTime(o)/1000;
				removeTime += operationTime;
			}
			
			Tools.print("Elapsed time: ", addTime + removeTime, "s\n");
			Tools.print("Elapsed median time: ", (addTime + removeTime)/(addAmount + removeAmount), "s\n");
			Tools.print("Add time: ", addTime, "s, elemets ", addAmount, "\n");
			Tools.print("Add median time: ", addTime/addAmount, "s, elemets ", addAmount, "\n");
			Tools.print("Remove time: ", removeTime, "s, elements ", removeAmount, "\n");
			Tools.print("Remove median time: ", removeTime/removeAmount, "s, elements ", removeAmount, "\n");
			Tools.print("End tests\n");
			
			float x = addAmount;
			
			X.add(x);
			YfAdd.add(addTime);
			YfDel.add(removeTime);
			YmAdd.add(addTime/addAmount);
			YmDel.add(removeTime/removeAmount);

			mul *= 10;
			
			DataBase.getInstance().setPassCarsLinked(restoreLinked);
		}
		
		buildGraph(X, YfAdd, "Full add linked");
		buildGraph(X, YfDel, "Full del linked");
		buildGraph(X, YmAdd, "Med add linked");
		buildGraph(X, YmDel, "Med del linked");
	}
	
	private void buildGraph(ArrayList<Float> X, ArrayList<Float> Y, String graphName) {
		JFrame frame = new GraphFrame(X,Y);
		frame.setTitle(graphName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
