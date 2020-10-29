package model;

import java.io.Serializable;

public abstract class Car implements Serializable {
	private static final long serialVersionUID = 1L;
	private String brand;
	private float speed;
	private Radio radio;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float maxSpeed) {
		this.speed = maxSpeed;
	}
	public Radio getRadio() {
		return radio;
	}
	public void setRadio(Radio radio) {
		this.radio = radio;
	}
	
	public Car() {
		super();
		this.radio = new Radio();
	}
	public Car(String brand, float speed, float station, boolean isOn){
		super();
		this.brand = brand;
		this.speed = speed;
		this.radio = new Radio(station, isOn);
	}
	
	public void move() {
		Tools.print("The car goes brrr\n");
	}
	public String toString() {
		String result = brand + " " + Float.toString(speed) + " " + radio.toString();
		return result;
	}
	
	public class Radio implements Serializable{
		private static final long serialVersionUID = 1L;

		public Radio() {
			super();
		}
		public Radio(float station, boolean isOn) {
			super();
			this.station = station;
			this.isOn = isOn;
		}
		
		private float station;
		private boolean isOn;
		
		public float getStation() {
			return station;
		}
		public void setStation(float station) {
			this.station = station;
		}
		
		public boolean isOn() {
			return isOn;
		}
		public void setOn(boolean isOn) {
			this.isOn = isOn;
		}
		public void playTunes() {
			if(isOn)
				Tools.print("The radio goes brrr\n");
			else
				Tools.print("Radio is off...\n");
		}
		
		@Override
		public String toString() {
			String result = "";
			result += Float.toString(station) + " " + Boolean.toString(isOn);
			return result;
		}
	}
}
