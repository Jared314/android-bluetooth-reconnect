package com.bluecasedevelopment.bluetoothreconnect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.android.music.MediaPlayback;

public class MediaPlaybackService extends Service {

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		MediaPlayback.play(this);
		this.stopSelf();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
