package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class AndroidServer implements Runnable{
	public void run() {
		try {
			String certificationString = null;
			ServerSocket serverSocket=new ServerSocket(12121);
			while(true)
			{
				System.out.println("等待接收用户连接：");
				//接受客户端请求
				Socket client=serverSocket.accept();
				try
				{
					
					//接受客户端信息
					BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
					String str=in.readLine();
					System.out.println("read:  "+str);
					
					//向服务器发送消息
					PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
					if(str.equals("12345")){
					 certificationString=("login_success"+","+"rtsp://192.168.1.21:8080/test.sdp");
//					certificationString=("login_success"+","+"rtsp://121.195.143.148:8080/test.sdp");
//					certificationString=("login_success"+","+"rtsp://222.199.230.172:8080/test.sdp");
					 
					out.println(certificationString);
					}
					else {
						certificationString="Not exist username";
						out.println(certificationString);
						}
					in.close();
					out.close();
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
				finally
				{
					client.close();
					System.out.println(certificationString);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String [] args)
	{
		Thread desktopServerThread=new Thread(new AndroidServer());
		desktopServerThread.start();
	}
}
