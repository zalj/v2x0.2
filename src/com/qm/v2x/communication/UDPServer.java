package com.qm.v2x.communication;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import com.qm.v2x.property.CarProperty;

public class UDPServer {
    // ����һЩ����
    private final int MAX_LENGTH = 1024; // �������ֽڳ���
    private final int PORT_NUM   = 5066;   // port��
    // ���Դ�Ž������ݵ��ֽ�����
    private byte[] receMsgs = new byte[MAX_LENGTH];
    // ���ݱ��׽���
    private DatagramSocket datagramSocket;
    // ���Խ������ݱ�
    private DatagramPacket datagramPacket;
    
    private static int SELF_ID = 1;
    
    public void setSelfId(int id) {
    	SELF_ID = id;
    }
    
    public CarProperty self;
    public ArrayList<CarProperty> others;
    
    public UDPServer(){
        try {
            /***** ������������ *****/
            datagramSocket = new DatagramSocket(PORT_NUM);// ����һ�����ݱ��׽��֣�������󶨵�ָ��port��
            datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);// DatagramPacket(byte buf[], int length),����һ���ֽ�����������UDP��
            
            while(true) {
	            datagramSocket.receive(datagramPacket);// receive()���ȴ�����UDP���ݱ�
	            /****** �������ݱ�****/
	            String receStr = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
	            System.out.println("Server Rece:" + receStr);
	            System.out.println("Server Port:" + datagramPacket.getPort());
	            
	            handle(receStr);
	            
	            /***** ����ACK��Ϣ���ݱ� *****/
	            // ��װ���ݱ�
	            byte[] buf = "I receive the message".getBytes();
	            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, datagramPacket.getAddress(), datagramPacket.getPort());
	            // ������Ϣ
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
    	 * 	�˴�����Ҫ��others��������Ȼ��ʹ��foreachѭ������others���б������ж�Σ�ճ̶ȡ�
    	 * 	��others���վ�����С��������
    	 */
    	for(CarProperty anotherCar : others) {
    		self.checkState(anotherCar);
    	}
	}

	public static void main(String[] args) {
		UDPServer server = new UDPServer();
	}
}