package com.android.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.android.music.IMediaPlaybackService;

public class MediaPlayback {
	private MediaPlayback() {

	}

	public static void play(Context context) {
		Intent i = new Intent();
		i.setClassName("com.android.music",
				"com.android.music.MediaPlaybackService");
		ServiceConnection conn = new MediaPlayerServiceConnection();

		context.bindService(i, conn, 0);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}
		context.unbindService(conn);

	}

	private static class MediaPlayerServiceConnection implements
			ServiceConnection {

		public IMediaPlaybackService mService;

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = IMediaPlaybackService.Stub.asInterface(service);

			try {
				if (mService != null && !mService.isPlaying())
					mService.play();
			} catch (RemoteException e) {

			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	}
}
