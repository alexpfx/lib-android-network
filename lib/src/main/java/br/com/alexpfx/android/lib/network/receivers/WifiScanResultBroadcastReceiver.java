package br.com.alexpfx.android.lib.network.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import br.com.alexpfx.android.lib.network.data_objects.wifi.WifiList;
import br.com.alexpfx.android.lib.network.data_objects.wifi.WifiNetwork;
import br.com.alexpfx.android.lib.network.utils.BusProvider;
import com.squareup.otto.Bus;

import java.util.List;

/**
 * Created by alexandre on 29/07/15.
 */
public class WifiScanResultBroadcastReceiver extends BroadcastReceiver {
    private final Bus bus = BusProvider.getInstance();

    public WifiScanResultBroadcastReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> scanResults = wifiManager.getScanResults();

        WifiList wifiList = new WifiList();
        for (ScanResult result : scanResults) {
            WifiNetwork wifiNetwork = WifiNetwork.fromScanResult(result);
            wifiList.add(wifiNetwork);
        }
        post(new ScanResultHolder(wifiList));
    }

    private void post(ScanResultHolder wifiList) {
        bus.post(wifiList);
    }

    public class ScanResultHolder {

        private WifiList wifiList;

        public ScanResultHolder(WifiList wifiList) {
            this.wifiList = wifiList;
        }

        public WifiList getWifiList() {
            return wifiList;
        }
    }

}
