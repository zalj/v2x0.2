package com.qm.v2x.property;

public class TrafficLight {
	
	private int TrafficLightID;
	
	private double longitude;
	private double latitude;
	
	private Phase[] phases;

	public int getTrafficLightID() {
		return TrafficLightID;
	}

	public void setTrafficLightID(int trafficLightID) {
		TrafficLightID = trafficLightID;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Phase[] getPhases() {
		return phases;
	}

	public void setPhases(Phase[] phases) {
		this.phases = phases;
	}
	
	
}
