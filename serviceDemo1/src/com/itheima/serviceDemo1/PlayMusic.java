package com.itheima.serviceDemo1;

import com.itheima.serviceDemo1.R;
import com.itheima.serviceDemo1.R.id;
import com.itheima.serviceDemo1.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 		把操作码放在Bundle中
 *		Bundle bundle  = new Bundle();
 *		bundle.putInt("op", op);
 *		intent.putExtras(bundle);
 *		最后使用startService(intent);启动服务。
 *
 */
public class PlayMusic extends Activity implements OnClickListener {
	private static final String TAG = "PlayMusic";
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
		Intent intent = new Intent("aaa.musicService");
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
			stopService(intent);
			this.finish();
			break;
		}

		Bundle bundle = new Bundle();
		bundle.putInt("op", op);
		intent.putExtras(bundle);
		startService(intent);
	}

}