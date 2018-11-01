package com.qm.v2x.communication;

import java.io.IOException;
import java.net.*;

public class UDPClient {

    private String sendStr = "SendString";
    private String netAddress = "127.0.0.1";
    private final int PORT_NUM = 5066;
    private final static int DEFAULT_ID = 0x1;		// 车的默认编号
    private static int SELF_ID;						// 车的唯一辨识ID

    private static void setSELF_ID(int sELF_ID) {
		SELF_ID = sELF_ID;
	}
    
    private double longitude;
    private double latitude;
    private double speedRate;
    private double pathAngle;
    
    public String setSendStr() {
    	sendStr = SELF_ID + ",";
    	sendStr += longitude + ",";
    	sendStr += latitude + ",";
    	sendStr += speedRate + ",";
    	sendStr += pathAngle;
    	return sendStr;
    }
    
	private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    
    public UDPClient(int id) {
    	setSELF_ID(id);
    	try {
    		while(true) {
	            /*** 发送数据***/
	            datagramSocket = new DatagramSocket();	// 初始化datagramSocket,注意与前面Server端实现的差别
	            sendStr = setSendStr();
	            byte[] buf = sendStr.getBytes();		// 使用DatagramPacket(byte buf[], int length, InetAddress address, int port)函数组装发送UDP数据报
	            InetAddress address = InetAddress.getByName(netAddress);
	            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
	            // 发送数据
	            datagramSocket.send(datagramPacket);
    		}
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(datagramSocket != null)
                datagramSocket.close();
        }
    }
    
    public UDPClient(){
        this(DEFAULT_ID);
    }  
    public static void main(String[] args) {
		UDPClient client = new UDPClient();
	}
}
