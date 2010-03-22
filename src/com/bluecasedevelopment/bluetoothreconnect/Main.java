package com.bluecasedevelopment.bluetoothreconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.bluecasedevelopment.bluetooth.A2dpConnectReceiver;

public class Main extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences p = PreferenceManager
				.getDefaultSharedPreferences(this);
		String id = p.getString(this
				.getString(R.string.preference_key_device_id), null);
		if (id == null) {
			showUserMessage(this, R.string.preference_key_device_id_not_found);
		}

		Intent i = new Intent(A2dpConnectReceiver.ACTION_CONNECT).putExtra(
				A2dpConnectReceiver.EXTRA_ID, id);

		this.sendBroadcast(i);

		this.finish();
	}

	private static void showUserMessage(Context context, int message) {
		Toast.makeText(context, context.getString(message), Toast.LENGTH_LONG)
				.show();
	}
}