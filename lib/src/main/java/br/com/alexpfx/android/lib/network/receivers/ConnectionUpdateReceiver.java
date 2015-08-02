package br.com.alexpfx.android.lib.network.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * Created by alexandre on 30/07/15.
 */
public class ConnectionUpdateReceiver extends BroadcastReceiver {


    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.getConnectionInfo();

        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        if (networkInfo != null && networkInfo.isConnected()) {
            if (listener != null) {
                listener.onWifiConnected(networkInfo);
            }
        }
    }

    public interface Listener {
        void onWifiConnected(NetworkInfo networkInfo);
    }
}
