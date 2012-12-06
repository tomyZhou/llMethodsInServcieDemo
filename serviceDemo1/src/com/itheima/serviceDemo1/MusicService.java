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
 * ����ʹ����ϵͳ�Դ�MediaPlayer�������ֵĲ��ſ��ơ� 
 * ��������startService�������ȵ���onCreate������������
 * ��MediaPlayer���г�ʼ�������Ż����onStart�����Կ�������
 * ��startService()��Intent����ᴫ�ݸ�onStart()����������
 * ���ǾͿ��Եõ�intent����Ĳ����룺 
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
				// �ڵ���stop�������Ҫ�ٴ�ͨ��start���в���,��Ҫ֮ǰ����prepare����
				mediaPlayer.prepare();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}