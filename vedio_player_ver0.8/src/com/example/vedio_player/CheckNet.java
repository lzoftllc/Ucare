package com.example.vedio_player;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNet extends Activity{
		boolean checkNetwork() {
		
		// ÊµÀý»¯ConnectivityManager
		
		ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		// »ñµÃµ±Ç°ÍøÂçÐÅÏ¢
		NetworkInfo info = manager.getActiveNetworkInfo();
		// ÅÐ¶ÏÊÇ·ñÁ¬½Ó
		if (info == null || !info.isConnected()) {
			return false;
			}
		return true;
			}
		}


