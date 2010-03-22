package com.bluecasedevelopment.bluetoothreconnect;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MediaPlaybackReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!intent.hasExtra(BluetoothDevice.EXTRA_DEVICE))
			return;

		BluetoothDevice device = (BluetoothDevice) intent.getExtras().get(
				BluetoothDevice.EXTRA_DEVICE);
		if (device == null)
			return;

		String targetId = getId(context, intent);

		if (targetId != null && device.getAddress().equals(targetId)) {
			Intent i = new Intent(context, MediaPlaybackService.class);
			context.startService(i);
		}
	}

	private static String getId(Context context, Intent intent) {
		SharedPreferences p = PreferenceManager
				.getDefaultSharedPreferences(context);
		return p.getString(
				context.getString(R.string.preference_key_device_id), null);
	}
}
