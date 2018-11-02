package com.qm.v2x.property;

public class Sign {
	
	private int id;
	private double posLon;
	private double posLat;
	private int radius;
	private String context;
	
	
	
	
	public Sign(int id, double posLon, double psoLat, int radius, String context) {
		super();
		this.id = id;
		this.posLon = posLon;
		this.posLat = psoLat;
		this.radius = radius;
		this.context = context;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPosLon() {
		return posLon;
	}
	public void setPosLon(double posLon) {
		this.posLon = posLon;
	}
	public double getPosLat() {
		return posLat;
	}
	public void setPosLat(double psoLat) {
		this.posLat = psoLat;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	

}
