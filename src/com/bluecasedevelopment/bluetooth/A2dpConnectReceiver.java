package com.bluecasedevelopment.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bluecasedevelopment.bishop.Bluetooth;
import com.bluecasedevelopment.bluetoothreconnect.MediaPlaybackService;
import com.bluecasedevelopment.bluetoothreconnect.R;

public class A2dpConnectReceiver extends BroadcastReceiver {
	public static final String ACTION_CONNECT = "com.bluecasedevelopment.bluetooth.device.action.CONNECT";
	public static final String EXTRA_ID = "id";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!BluetoothAdapter.getDefaultAdapter().isEnabled())
			return;

		String id = intent.getStringExtra(EXTRA_ID);
		if (id == null || id.length() < 1) {
			showUserMessage(context, R.string.device_id_not_set_msg);
			return;
		}

		if (Bluetooth.isConnected(context, id))
			playMusic(context);
		else
			connectInternal(context, intent, id);
	}

	private static void playMusic(Context context) {
		Intent i = new Intent(context, MediaPlaybackService.class);
		context.startService(i);
	}

	private static boolean connectInternal(Context context, Intent intent,
			String id) {
		boolean success = Bluetooth.connectA2dpDevice(context, id);

		if (!success) {
			showUserMessage(context, R.string.connect_failure_msg);
		}

		return success;
	}

	private static void showUserMessage(Context context, int message) {
		Toast.makeText(context, context.getString(message), Toast.LENGTH_LONG)
				.show();
	}
}
