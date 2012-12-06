package com.itheima.serviceDemo3;

import com.itheima.serviceDemo3.R;
import com.itheima.serviceDemo3.R.id;
import com.itheima.serviceDemo3.R.layout;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlayBindMusic extends Activity implements OnClickListener {

	private static final String TAG = "PlayBindMusic";
	private Button playBtn;
	private Button stopBtn;
	private Button pauseBtn;
	private Button exitBtn;
	private BindMusicService musicService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		findViewById(R.id.play).setOnClickListener(this);
		findViewById(R.id.stop).setOnClickListener(this);
		findViewById(R.id.pause).setOnClickListener(this);
		findViewById(R.id.close).setOnClickListener(this);
		findViewById(R.id.exit).setOnClickListener(this);
		connection();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			Log.d(TAG, "onClick: binding srvice");
			if(musicService != null){
				musicService.play();
			}
			break;
		case R.id.stop:
			Log.d(TAG, "onClick: stoping srvice");
			if (musicService != null) {
				musicService.stop();
			}
			break;
		case R.id.pause:
			Log.d(TAG, "onClick: pausing srvice");
			if (musicService != null) {
				musicService.pause();
			}
			break;
		case R.id.close:
			Log.d(TAG,"onClick:finish Activity only");
			//由于是绑定的，Activity消亡了，Service也就消亡了，所以不存在后台
			//播放的情况
			this.finish();
			break;
		case R.id.exit:
			Log.d(TAG, "onClick:finish Activity and stop Service");
			this.finish();
			break;
		}
	}

	private void connection() {
		Log.d(TAG, "connecting.....");
		Intent intent = new Intent("ccc.bindService");
		bindService(intent, sc, Context.BIND_AUTO_CREATE);
	}

	private ServiceConnection sc = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			musicService = ((BindMusicService.MyBinder) (service)).getService();
			Log.d(TAG, "in onServiceConnected");
		}
		
		public void onServiceDisconnected(ComponentName name) {
			musicService = null;
			Log.d(TAG, "in onServiceDisconnected");
		}

	};

	@Override
	protected void onDestroy() {
		//显示解绑:防止内存泄露
		unbindService(sc);
		super.onDestroy();
	}
	
}