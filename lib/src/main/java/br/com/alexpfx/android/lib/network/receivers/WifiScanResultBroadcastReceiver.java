package br.com.alexpfx.android.lib.network.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by alexandre on 29/07/15.
 */
public class WifiScanResultBroadcastReceiver extends BroadcastReceiver {
    private final Bus bus;

    public WifiScanResultBroadcastReceiver(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> scanResults = wifiManager.getScanResults();

        WifiList wifiList = new WifiList();
        for (ScanResult result : scanResults) {
            WifiInfo wifiInfo = WifiInfo.fromScanResult(result);
            wifiList.add(wifiInfo);
        }
        post(wifiList);
    }

    private void post(WifiList wifiList) {
        bus.post(wifiList);
    }
}