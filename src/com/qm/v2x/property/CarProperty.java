package com.qm.v2x.property;

import java.io.IOException;

public class CarProperty implements Comparable<CarProperty>{
	private final int CAR_ID = 1;
	private final int LONGITUDE = 2;
	private final int LATITUDE = 3;
	private final int SPEED_RATE = 4;
	private final int PATH_ANGLE = 5;
	
	private int carID;				// 唯一标识一辆试验车的ID
	private double longitude;		// 经度
	private double latitude;		// 纬度
	private double speedRate;		// 行驶速率
	private double pathAngle;		// 航向角
	
	public double getField(int field) throws IOException {
		if(field == CAR_ID)
			return getCarID();
		else if(field == LONGITUDE)
			return getLongitude();
		else if(field == LATITUDE)
			return getLatitude();
		else if(field == SPEED_RATE)
			return getSpeedRate();
		else if(field == PATH_ANGLE)
			return getPathAngle();
		else
			throw new IOException("no this field");
	}
	
	private int getCarID() {
		return carID;
	}
	
	private double getLongitude() {
		return longitude;
	}
	
	private double getLatitude() {
		return latitude;
	}
	
	private double getSpeedRate() {
		return speedRate;
	}
	
	private double getPathAngle() {
		return pathAngle;
	}
	
	public void checkState(CarProperty anotherCar) {
		/**
		 * Ready to complete
		 */
	}

	
	
	/**
	 * @param carID
	 * @param longitude
	 * @param latitude
	 * @param speedRate
	 * @param pathAngle
	 */
	public CarProperty(int carID, double longitude, double latitude, double speedRate, double pathAngle) {
		super();
		this.carID = carID;
		this.longitude = longitude;
		this.latitude = latitude;
		this.speedRate = speedRate;
		this.pathAngle = pathAngle;
	}

	@Override
	public String toString() {
		return "[" + carID + ", " + longitude + ", " + latitude + ", " + speedRate + ", " + pathAngle + "]";
	}

	private double getDistance(CarProperty anotherCar) {
		/**
		 * Ready to complete
		 */
		return 0;
	}
	
	@Override
	public int compareTo(CarProperty o) {
		return (int)getDistance(o);
	}
}
