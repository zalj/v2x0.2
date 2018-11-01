package com.qm.v2x.app;

public class Vehicle {
	
	private int ID;
	private double x;
	private double y;
	private double speed;
	private double speAngle;
	
	
	
	public Vehicle(int iD, double x, double y, double speed, double speAngle) {
		super();
		ID = iD;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.speAngle = speAngle;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeAngle() {
		return speAngle;
	}
	public void setSpeAngle(double speAngle) {
		this.speAngle = speAngle;
	}
	
	
}
