package com.qm.v2x.communication;

import java.io.IOException;
import java.net.*;
import java.util.PriorityQueue;

import com.qm.v2x.property.CarProperty;

public class UDPServer {
    // 定义一些常量
    private final int MAX_LENGTH = 1024; // 最大接收字节长度
    private final int PORT_NUM   = 5066;   // port号
    // 用以存放接收数据的字节数组
    private byte[] receMsgs = new byte[MAX_LENGTH];
    // 数据报套接字
    private DatagramSocket datagramSocket;
    // 用以接收数据报
    private DatagramPacket datagramPacket;
    
    private static final int DEFAULT_ID = 0x1;
    private static int SELF_ID;
    
    public void setSelfId(int id) {
    	SELF_ID = id;
    }
    
    public CarProperty self;
    public PriorityQueue<CarProperty> others;
    
    public UDPServer(int id){
    	setSelfId(id);
        try {
            /***** 接收数据流程 *****/
            datagramSocket = new DatagramSocket(PORT_NUM);// 创建一个数据报套接字，并将其绑定到指定port上
            datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);// DatagramPacket(byte buf[], int length),建立一个字节数组来接收UDP包
            
            while(true) {
	            datagramSocket.receive(datagramPacket);// receive()来等待接收UDP数据报
	            /****** 解析数据报****/
	            String receStr = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
	            System.out.println("Server Rece:" + receStr);
	            System.out.println("Server Port:" + datagramPacket.getPort());
	            
	            handle(receStr);
	            
	            /***** 返回ACK消息数据报 *****/
	            // 组装数据报
	            byte[] buf = "I receive the message".getBytes();
	            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, datagramPacket.getAddress(), datagramPacket.getPort());
	            // 发送消息
	            datagramSocket.send(sendPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) 
                datagramSocket.close();
        }
    }
    
    public UDPServer() {
    	this(DEFAULT_ID);
    }
    
    /**
     * info[0]: ID			int
     * info[1]: Longitude	double
     * info[2]: Latitude	double
     * info[3]: Seed Rate	double
     * info[4]: Path Angle	double
     */
    
    private void handle(String receStr) {
    	String[] info = receStr.split(",");
    	int carID = Integer.parseInt(info[0]);
    	double longitude = Double.parseDouble(info[1]);
    	double latitude = Double.parseDouble(info[2]);
    	double speedRate = Double.parseDouble(info[3]);
    	double pathAngle = Double.parseDouble(info[4]);
    	if(Integer.parseInt(info[0]) == SELF_ID) {
    		self = new CarProperty(carID, longitude, latitude, speedRate, pathAngle);
    	}else {
			others.add(new CarProperty(carID, longitude, latitude, speedRate, pathAngle));
		}
    	
    	/**
    	 * 	此处还需要对others进行排序，然后使用foreach循环，对others进行遍历，判断危险程度。
    	 * 	对others按照距离由小到大排序
    	 */
    	for(CarProperty anotherCar : others) {
    		self.checkState(anotherCar);
    	}
	}

	public static void main(String[] args) {
		UDPServer server = new UDPServer();
	}
}