package com.qm.v2x.property;

public class CarProperty {
	private int carID;				// Ψһ��ʶһ�����鳵��ID
	private double longitude;		// ����
	private double latitude;		// γ��
	private double speedRate;		// ��ʻ����
	private double pathAngle;		// �����
	
	public int getCarID() {
		return carID;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getSpeedRate() {
		return speedRate;
	}
	
	public double getPathAngle() {
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
