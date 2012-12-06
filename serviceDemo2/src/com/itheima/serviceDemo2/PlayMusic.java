package com.itheima.serviceDemo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlayMusic extends Activity implements OnClickListener{
	
	private static final String TAG = "PlayMusic";
	private Button playBtn;
	private Button stopBtn;
	private Button pauseBtn;
	private Button closeBtn;
	private Button exitBtn;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		findViewById(R.id.play).setOnClickListener(this);
		findViewById(R.id.stop).setOnClickListener(this);
		findViewById(R.id.pause).setOnClickListener(this);
		findViewById(R.id.close).setOnClickListener(this);
		findViewById(R.id.exit).setOnClickListener(this);
	}



	public void onClick(View v) {
		int op = -1;
		Intent intent = new Intent("bbb.musicReceiver");
		switch (v.getId()) {
		case R.id.play:
			Log.d(TAG, "onClick: playing muic");
			op = 1;
			break;
		case R.id.stop:
			Log.d(TAG, "onClick: stoping music");
			op = 2;
			break;
		case R.id.pause:
			Log.d(TAG, "onClick: pausing music");
			op = 3;
			break;
		case R.id.close:
			Log.d(TAG, "onClick: close");
			this.finish();
			break;
		case R.id.exit:
			Log.d(TAG, "onClick: exit");
			op = 4;
			this.finish();
			break;
		}

		Bundle bundle = new Bundle();
		bundle.putInt("op", op);
		intent.putExtras(bundle);
		sendBroadcast(intent);
	}

}