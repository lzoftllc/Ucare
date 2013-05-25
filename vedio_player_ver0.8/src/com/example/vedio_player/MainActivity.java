package com.example.vedio_player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.ActivityManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener{

	String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
	ImageButton btnplay, btnstop, btnpause,btnopen,btnScreenshot;
	String vedioURL;
	SurfaceView surfaceView;
	MediaPlayer mediaPlayer;
	EditText editText;
	int position;
	//private Context self;
	
//-----------------------
	public class Element {
	    private float mX;
	    private float mY;

	    private Bitmap mBitmap;
	    

	    public Element(Resources res, int x, int y) {
	        mBitmap = BitmapFactory.decodeStream(new BufferedInputStream(res.openRawResource(R.drawable.open)));
	        mX = x - mBitmap.getWidth() / 2;
	        mY = y - mBitmap.getHeight() / 2;
	    }

	    public void doDraw(Canvas canvas) {
	        canvas.drawBitmap(mBitmap, mX, mY, null);
	    }
	}
//-------------------------

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		 Intent intent = this.getIntent();    //获得当前的Intent  
	     Bundle bundle = intent.getExtras();  //获得全部数据  
	     vedioURL = bundle.getString("rtspAddr");  //获得名为rtspAddr的值 
	     
	     mediaPlayer=new MediaPlayer();
		 surfaceView=(SurfaceView) this.findViewById(R.id.surfaceView);
		
	 	     
//		btnplay=(ImageButton)this.findViewById(R.id.btnplay);
		btnstop=(ImageButton)this.findViewById(R.id.btnstop);
//		btnpause=(ImageButton)this.findViewById(R.id.btnpause);
//		btnopen=(ImageButton)this.findViewById(R.id.btnopen);
//		btnScreenshot=(ImageButton)this.findViewById(R.id.btnscreenshot);
		
		btnstop.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
//		btnScreenshot.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				saveScreenshot();
//			}
//			
//			String mScreenshotPath = Environment.getExternalStorageDirectory() + "/droidnova";
//
//			public void saveScreenshot() {
//			    if (ensureSDCardAccess()) {
//			        Bitmap bitmap = Bitmap.createBitmap(300, 405, Bitmap.Config.ARGB_8888);
//			        Canvas canvas = new Canvas(bitmap);
//			        canvas.drawBitmap(bitmap, 0, 0, null);
////			        doDraw(1, canvas);
//			        File file = new File(mScreenshotPath + "/" + currentDateTimeString + ".jpg");
//			        FileOutputStream fos;
//			        try {
//			            fos = new FileOutputStream(file);
//			            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//			            fos.close();
//			        } catch (FileNotFoundException e) {
//			            Log.e("Panel", "FileNotFoundException", e);
//			        } catch (IOException e) {
//			            Log.e("Panel", "IOEception", e);
//			        }
//			    }
//			}
//
//			private boolean ensureSDCardAccess() {
//			    File file = new File(mScreenshotPath);
//			    if (file.exists()) {
//			        return true;
//			    } else if (file.mkdirs()) {
//			        return true;
//			    }
//			    return false;
//			}
//		
//
//		});
		
		
	
//		btnplay.setOnClickListener(this);
//		btnpause.setOnClickListener(this);
//		btnopen.setOnClickListener(this);
		
		Toast.makeText(MainActivity.this, "为您缓冲中请稍后~~", Toast.LENGTH_LONG).show();
		
		//设置SurfaceView自己不管理的缓冲区
		surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);		
		surfaceView.getHolder().addCallback(new Callback() {		
			public void surfaceDestroyed(SurfaceHolder holder) {
		
			}
		
			public void surfaceCreated(SurfaceHolder holder) {
//				if (position>0) {
					try {
						//开始播放
						play();
						//并直接从指定位置开始播放
						mediaPlayer.seekTo(position);
						position=0;						
					} catch (Exception e) {
						// TODO: handle exception
					}
//				}
			}			
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {

			}
		});		
//		 play();
	}

	
	
//	public void onClick(View v) {	
//		switch (v.getId()) {
//		case R.id.btnopen:
//			System.out.println("open");
//			open();
//			break;
//			
//		case R.id.btnplay:
//			play();
//			break;
//			
//		case R.id.btnpause:
//			if (mediaPlayer.isPlaying()) {
//				mediaPlayer.pause();
//			}
//			else{
//				mediaPlayer.start();
//			}
//			break;
//
//		case R.id.btnstop:
//			if (mediaPlayer.isPlaying()) {
//				mediaPlayer.stop();
//			}
//			
//			break;
//		default:
//			break;
//		}
//
//	}
//	@Override
//	protected void onPause() {	
//		//先判断是否正在播放
//		if (mediaPlayer.isPlaying()) {
//			//如果正在播放我们就先保存这个播放位置
//			position=mediaPlayer.getCurrentPosition();
//			mediaPlayer.stop();
//		}
//		super.onPause();
//	}
//	private void open(){
//		try {
//			 
//			 new AlertDialog.Builder(this)
//			.setTitle("请输入rtsp流地址端口,例如：rtsp://")
//			.setIcon(android.R.drawable.ic_dialog_info)
//			.setView(editText=new EditText(this))
//			.setPositiveButton("打开", 
//				new DialogInterface.OnClickListener() {
//				
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					vedioURL=editText.getText().toString();
//					play();
//							}
//			})
//			.setNegativeButton("取消", null)
//			.show();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//	}

	private void play() {
		try {
			System.out.println("paly"+vedioURL);
			mediaPlayer.reset();
//			 mediaPlayer.setWakeMode(null, position) ; 
			//mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			//设置需要播放的视频
			  mediaPlayer.setDataSource(vedioURL);
			//mediaPlayer.setDataSource("rtsp://192.168.1.88/2?admin:admin");//直接读摄像机
//			  mediaPlayer.setDataSource("rtsp://172.31.6.7:8080/test.sdp");//读服务器
//			  mediaPlayer.setDataSource("rtsp://58.200.131.2/BTV-9");
			
			//把视频画面输出到SurfaceView
			mediaPlayer.setDisplay(surfaceView.getHolder());
			mediaPlayer.prepare();
			//播放
			mediaPlayer.start();		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	

	



	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
}
