package com.qm.v2x.property;

import java.io.IOException;

import com.qm.v2x.util.Geo;
import com.qm.v2x.util.posUtil;

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
		
		double[] coord1=Geo.convertToXY(longitude, latitude);
    	double x1=coord1[0];
    	double y1=coord1[0];
    	double[] coord2=Geo.convertToXY(anotherCar.longitude, anotherCar.latitude);
    	double x2=coord2[0];
    	double y2=coord2[0];
		
		//FCW
		if( posUtil.getDistance(longitude, latitude, anotherCar.getLongitude(), anotherCar.getLatitude())<20.0 &&
				posUtil.verticalDistance(longitude, latitude, anotherCar.getLongitude(), anotherCar.getLatitude(), pathAngle)<3.5 &&
					posUtil.inFront(longitude, latitude, anotherCar.getLongitude(), anotherCar.getLatitude(), pathAngle) &&
						Math.abs(pathAngle-anotherCar.getPathAngle())<20.0) {
			System.out.println("FCW");
		}
		
		//ICW
		if( posUtil.getDistance(longitude, latitude, anotherCar.getLongitude(), anotherCar.getLatitude())<50.0 &&
				(Math.abs(pathAngle-anotherCar.getPathAngle())>70.0 && Math.abs(pathAngle-anotherCar.getPathAngle())<110.0)) {
			
//			double[] CollisionXY=posUtil.calculateCollisionPointXY( longitude, latitude, pathAngle, anotherCar.getLongitude(), anotherCar.getLatitude(), anotherCar.getPathAngle());
			
			
			//计算交点
			
			double k1=Math.tan(Math.toRadians(pathAngle));
			double k2=Math.tan(Math.toRadians(anotherCar.getPathAngle()));
			//交点坐标
			double intersecX=(y2-y1+k1*x1-k2*x2)/(k1-k2);
			double intersecY=(k1*k2*(x1-x2)+k1*y2-k2*y1)/(k1-k2);
			//两车到交点的向量
			double s2cVector[]= {intersecX-x1,intersecY-y1};
			double r2cVector[]= {intersecX-x2,intersecY-y2};
			
			double svUnitVector[]= {Math.cos(Math.toRadians(pathAngle)),Math.sin(Math.toRadians(pathAngle))};
			double rvUnitVector[]= {Math.cos(Math.toRadians(anotherCar.getPathAngle())),Math.sin(anotherCar.getPathAngle())};
			
			
			if(s2cVector[0]*svUnitVector[0]+s2cVector[1]*svUnitVector[1]>0 && r2cVector[0]*rvUnitVector[0]+r2cVector[1]*rvUnitVector[1]<0) {
				System.out.println("ICW");
			}
			
			
		}
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
		 * completed
		 */
	   return posUtil.getDistance(longitude, latitude, anotherCar.getLongitude(), anotherCar.getLatitude());
	}
	
	@Override
	public int compareTo(CarProperty o) {
		return (int)getDistance(o);
	}
}
