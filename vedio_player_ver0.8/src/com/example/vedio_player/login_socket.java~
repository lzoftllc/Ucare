package com.example.vedio_player;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
//客户端的实现
public class login_socket extends Activity {
protected static final int MSG_FAILURE = 0;
protected static final int MSG_SUCCESS = 1;
//	private TextView text1;
	private Button loginbtn;
	private EditText usernameEditText;
	private final String DEBUG_TAG="mySocketAct";
	private String[] mstrSplit;
	private RelativeLayout RLay;


	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);
        
        checkNetAll();

        loginbtn=(Button)findViewById(R.id.login_btn);
        usernameEditText=(EditText)findViewById(R.id.username_tx);
        RLay = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        RLay.setBackgroundColor(00000000);
        
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        
        loginbtn.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View v) {
        		
				new Thread(loginTd).start();			//实际版本

			}        	
        });
    }
    Runnable loginTd = new Runnable() {
		public void run() {
			try {
				String mesg=usernameEditText.getText().toString()+"\r\n";
				Socket socket=null;
//				socket=new Socket("121.195.143.120",12121);
				socket=new Socket("192.168.1.21",12121);
//				socket=new Socket("222.199.230.172",12121);
//				socket=new Socket("172.31.6.156",12121);
				//向服务器发送信息
				PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
				out.println(mesg);
				
				//接受服务器的信息
				BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String mstr=br.readLine();
				System.out.println(mstr);
				 mstrSplit = mstr.split(",");
				if(mstrSplit[0].equals("login_success"))
				{
					mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
					
				}else
				{
					mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
					
				}
				out.close();
				br.close();
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}catch(Exception e)
			{
				Log.e(DEBUG_TAG,e.toString());
			}
			
		}
	};

	 public boolean checkNetwork() {
			
			// 实例化ConnectivityManager
			
			ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			// 获得当前网络信息
			NetworkInfo info = manager.getActiveNetworkInfo();
			// 判断是否连接
			if (info == null || !info.isConnected()) {
				return false;
				}
			return true;
				}
	    		private void checkNetAll(){
	    		if(checkNetwork()==false){
	    	    Toast.makeText(login_socket.this, "连不上网，请检查网络连接", Toast.LENGTH_LONG).show();   //这里我只做了一个提示，可根据情况设置成其它形式。
	    	  }
	    	  
	    	}
	    	private Handler mHandler = new Handler() {  
	    	        public void handleMessage (Message msg) {//此方法在ui线程运行  
	    	            switch(msg.what) {  
	    	            case MSG_SUCCESS:   
	    	            	Toast.makeText(login_socket.this, "恭喜，登陆成功！", Toast.LENGTH_LONG).show();
	    	            	Intent intent = new Intent();  
	    	                intent.setClass(login_socket.this, MainActivity.class);  //从login_socket跳转到MainActivity  
	    	                intent.putExtra("rtspAddr", mstrSplit[1]);  //放入数据  
	    	                finish();
	    	                startActivity(intent);  //开始跳转  
	    	                break;  
	    	  
	    	            case MSG_FAILURE:  
	    	            	Toast.makeText(login_socket.this, "汪汪，主人再检查一下您输对手机号了嘛~~", Toast.LENGTH_LONG).show();  
	    	                break;  
	    	            }  
	    	        }  
	    	    };  
}
   

