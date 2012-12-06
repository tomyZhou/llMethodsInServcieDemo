package com.itheima.serviceDemo1;

import java.io.IOException;

import com.itheima.serviceDemo1.R;
import com.itheima.serviceDemo1.R.raw;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
 
/**
 * 服务使用了系统自带MediaPlayer进行音乐的播放控制。 
 * 当调用了startService后服务会先调用onCreate，我们在里面
 * 对MediaPlayer进行初始化。接着会调用onStart，可以看到传递
 * 给startService()的Intent对象会传递给onStart()方法，这样
 * 我们就可以得到intent里面的操作码： 
 *Iundle bundle = intent.getExtras();
 *int op = bundle.getInt("op");
 *
 */
public class MusicService extends Service {

	private static final String TAG = "MyService";
	private MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "onCreate");
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(this, R.raw.aa);
			mediaPlayer.setLooping(false);
		}
	}

	@Override
	public void onDestroy() {
		Log.v(TAG, "onDestroy");
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(TAG, "onStart");
		System.out.println(startId+"********");
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				int op = bundle.getInt("op");
				switch (op) {
				case 1:
					play();
					break;
				case 2:
					stop();
					break;
				case 3:
					pause();
					break;
				}

			}
		}

	}

	public void play() {
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();
		}
	}

	public void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			try {
				// 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
				mediaPlayer.prepare();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}