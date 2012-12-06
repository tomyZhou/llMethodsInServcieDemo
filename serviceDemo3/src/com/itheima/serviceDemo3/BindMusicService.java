package com.itheima.serviceDemo3;

import java.io.IOException;

import com.itheima.serviceDemo3.R;
import com.itheima.serviceDemo3.R.raw;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author allin.dev http://allin.cnblogs.com/
 */
public class BindMusicService extends Service {

	private static final String TAG = "MyService";
	private MediaPlayer mediaPlayer;

	private final IBinder binder = new MyBinder();

	public class MyBinder extends Binder {
		public BindMusicService getService() {
			return BindMusicService.this;
		}
	}
 
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Log.d(TAG, "onCreate");
		Toast.makeText(this, "show media player", Toast.LENGTH_SHORT).show();
		
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(this, R.raw.aa);
			mediaPlayer.setLooping(false);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		Toast.makeText(this, "stop media player", Toast.LENGTH_SHORT);
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}

/*	public void play() {
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(this, R.raw.aa);
			mediaPlayer.setLooping(false);
		}
		if (!mediaPlayer.isPlaying()) {
			System.out.println("player is not null but not playing...");
			mediaPlayer.start(); //����֣�Ϊʲô�����²��ţ����ǽ��ŷ��أ�
		}
	}*/
	
	public void play() {
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();  //?����stop��Ч��������pauseЧ��,why?
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
				// �ڵ���stop�������Ҫ�ٴ�ͨ��start���в���,��Ҫ֮ǰ����prepare����
				mediaPlayer.prepare();
				Toast.makeText(this,"stop()",0).show();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}