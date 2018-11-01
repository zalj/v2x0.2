package com.qm.v2x.property;

import java.io.IOException;

public class CarProperty {
	private final int CAR_ID = 1;
	private final int LONGITUDE = 2;
	private final int LATITUDE = 3;
	private final int SPEED_RATE = 4;
	private final int PATH_ANGLE = 5;
	
	private int carID;				// Ψһ��ʶһ�����鳵��ID
	private double longitude;		// ����
	private double latitude;		// γ��
	private double speedRate;		// ��ʻ����
	private double pathAngle;		// �����
	
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
}
