package com.itheima.serviceDemo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MusicReceiver extends BroadcastReceiver {

	private static final String TAG = "MusicReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		Intent intents = new Intent("aaa.musicService");
		Bundle bundle = intent.getExtras();
		intents.putExtras(bundle);

		if (bundle != null) {
			int op = bundle.getInt("op");
			if (op == 4) {
				context.stopService(intents);
			} else {
				context.startService(intents);
			}
		}

	}

}