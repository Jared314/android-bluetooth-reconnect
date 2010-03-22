package com.bluecasedevelopment.bluetoothreconnect;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ConnectPreferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.device_preferences);
	}
}
