package com.example.WiFiScanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

class WifiReceiver extends BroadcastReceiver {

    WifiManager wifiManager;
    StringBuilder sb;
    ListView wifiDeviceList;

    public WifiReceiver(WifiManager wifiManager, ListView wifiDeviceList) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            sb = new StringBuilder();
            List<ScanResult> wifiList = wifiManager.getScanResults();

            ArrayList<String> deviceList = new ArrayList<>();
            for (ScanResult scanResult : wifiList) {
//                Log.d(TAG, scanResult.BSSID);
                sb.append("\n").append(scanResult.SSID).append("\nMac : ").append(scanResult.BSSID).append("\nSignal Strenth : ").append(scanResult.level);
                deviceList.add(scanResult.SSID + "\nMac : " + scanResult.BSSID + "\nSignal Strenth : " + scanResult.level +
                        "\nFrequency : " + (scanResult.frequency*0.001) + " GHz");
            }
//            Toast.makeText(context, sb, Toast.LENGTH_SHORT).show();

            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, deviceList.toArray());

            wifiDeviceList.setAdapter(arrayAdapter);
        }
    }
}
