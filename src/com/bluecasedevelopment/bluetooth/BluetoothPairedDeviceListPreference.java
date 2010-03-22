package com.bluecasedevelopment.bluetooth;

import java.util.Set;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;

import com.bluecasedevelopment.bishop.Bluetooth;

public class BluetoothPairedDeviceListPreference extends ListPreference {

	public BluetoothPairedDeviceListPreference(Context context) {
		super(context);
		setValues(Bluetooth.getPairedDevices());
	}

	public BluetoothPairedDeviceListPreference(Context context,
			AttributeSet attrs) {
		super(context, attrs);
		setValues(Bluetooth.getPairedDevices());
	}

	private void setValues(Set<BluetoothDevice> devices) {
		String[] names = new String[devices.size()];
		String[] values = new String[devices.size()];

		int i = 0;
		for (BluetoothDevice d : devices) {
			names[i] = d.getName();
			values[i] = d.getAddress();
			i++;
		}

		this.setEntries(names);
		this.setEntryValues(values);
	}
}
