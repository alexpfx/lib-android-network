package br.com.alexpfx.android.lib.network.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import br.com.alexpfx.android.lib.base.events.BusEvent;
import br.com.alexpfx.android.lib.network.utils.BusProvider;
import com.squareup.otto.Bus;

/**
 * Created by alexandre on 30/07/15.
 */
public class WifiConnectionUpdateReceiver extends BroadcastReceiver {

    private Bus bus = BusProvider.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (networkInfo != null && networkInfo.isConnected()) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            bus.post(new WifiConnectionInfoEvent(wifiInfo, networkInfo));
        }
    }

    public class WifiConnectionInfoEvent implements BusEvent {

        private WifiInfo wifiInfo;
        private NetworkInfo networkInfo;

        public WifiConnectionInfoEvent(WifiInfo wifiInfo, NetworkInfo networkInfo) {
            this.wifiInfo = wifiInfo;
            this.networkInfo = networkInfo;
        }

        public WifiInfo getWifiInfo() {
            return wifiInfo;
        }

        public NetworkInfo getNetworkInfo() {
            return networkInfo;
        }
    }

}
