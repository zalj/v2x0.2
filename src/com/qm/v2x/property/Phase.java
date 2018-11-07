package com.qm.v2x.property;

public class Phase {
	/**
	 * 该相位ID
	 */
	int phaseID;
	/**
	 * 绿灯时间
	 */
	int green;
	/**
	 * 黄灯时间
	 */
	int yellow;
	/**
	 * 红灯时间
	 */
	int red;
	/**
	 * 当前状态：0-灯全灭 、1-绿灯、2-黄灯、3-红灯
	 */
	int status;
	/**
	 * 当前剩余时间
	 */
	int timeLeft;
	/**
	 * 该相位对应的车辆航向角
	 */
	double pathAngel;
	public int getPhaseID() {
		return phaseID;
	}
	public void setPhaseID(int phaseID) {
		this.phaseID = phaseID;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getYellow() {
		return yellow;
	}
	public void setYellow(int yellow) {
		this.yellow = yellow;
	}
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	public double getPathAngel() {
		return pathAngel;
	}
	public void setPathAngel(double pathAngel) {
		this.pathAngel = pathAngel;
	}
	
	
}
