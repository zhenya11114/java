package model;

public class PassengerCar extends Car {
	private static final long serialVersionUID = 1L;

	public PassengerCar() {
		super();
	}
	public PassengerCar(String brand, float speed, float station, boolean isOn) {
		super(brand, speed, station, isOn);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
