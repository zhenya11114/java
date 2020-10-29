package model;

public class Truck extends Car {
	private static final long serialVersionUID = 1L;
	
	private float weight;
	private float bodyHeight;
	
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getBodyHeight() {
		return bodyHeight;
	}
	public void setBodyHeight(float height) {
		this.bodyHeight = height;
	}
	
	public Truck() {
		super();
	}
	public Truck(String brand, float speed, float station, boolean isOn, float weight, float height) {
		super(brand, speed, station, isOn);
		this.weight = weight;
		this.bodyHeight = height;
	}
	
	@Override
	public String toString() {
		String result = super.toString() + " " + Float.toString(weight) + " " + Float.toString(bodyHeight);
		return result;
	}
}
