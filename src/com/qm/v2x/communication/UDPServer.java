package com.qm.v2x.communication;

import java.io.IOException;
import java.net.*;

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
   
    public UDPServer(){
        try {
            /******* ������������**/
            // ����һ�����ݱ��׽��֣�������󶨵�ָ��port��
            datagramSocket = new DatagramSocket(PORT_NUM);
            // DatagramPacket(byte buf[], int length),����һ���ֽ�����������UDP��
            datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);
            // receive()���ȴ�����UDP���ݱ�
            datagramSocket.receive(datagramPacket);
           
            /****** �������ݱ�****/
            String receStr = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
            System.out.println("Server Rece:" + receStr);
            System.out.println("Server Port:" + datagramPacket.getPort());
           
            /***** ����ACK��Ϣ���ݱ�*/
            // ��װ���ݱ�
            byte[] buf = "I receive the message".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, datagramPacket.getAddress(), datagramPacket.getPort());
            // ������Ϣ
            datagramSocket.send(sendPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // �ر�socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
    public static void main(String[] args) {
		UDPServer server = new UDPServer();
	}
}