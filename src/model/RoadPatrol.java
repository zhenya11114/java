package model;

import java.io.Serializable;

public class RoadPatrol implements Serializable{
	private static final long serialVersionUID = 1L;
	private float maxSpeedAvailable;
	private float maxBodyHeightAvailable;
	private float maxWeightAvailable;
	
	public float getMaxSpeedAvailable() {
		return maxSpeedAvailable;
	}
	public void setMaxSpeedAvailable(float maxSpeedAvailable) {
		this.maxSpeedAvailable = maxSpeedAvailable;
	}
	public float getMaxBodyHeightAvailable() {
		return maxBodyHeightAvailable;
	}
	public void setMaxBodyHeightAvailable(float maxBodyHeightAvailable) {
		this.maxBodyHeightAvailable = maxBodyHeightAvailable;
	}
	public float getMaxWeightAvailable() {
		return maxWeightAvailable;
	}
	public void setMaxWeightAvailable(float maxWeightAvailable) {
		this.maxWeightAvailable = maxWeightAvailable;
	}

	
	public RoadPatrol() {
		super();
	}
	public RoadPatrol(float maxSpeedAvailable, float maxBodyHeightAvailable, float maxWeightAvailable) {
		super();
		this.maxSpeedAvailable = maxSpeedAvailable;
		this.maxBodyHeightAvailable = maxBodyHeightAvailable;
		this.maxWeightAvailable = maxWeightAvailable;
	}
	
	public boolean pass(Car car) {
		if (car.getSpeed() > maxSpeedAvailable) {
			return true;
		}
		if(Tools.isSameType(car, Truck.class)) {
			Truck truck = (Truck)car;
			if (truck.getBodyHeight() > maxBodyHeightAvailable ||
				truck.getWeight() > maxWeightAvailable) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String result = Float.toString(maxSpeedAvailable) + " " +
						Float.toString(maxBodyHeightAvailable) + " " + 
						Float.toString(maxWeightAvailable);
		return result;
	}
}
