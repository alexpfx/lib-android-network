package br.com.alexpfx.android.lib.network.model;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import br.com.alexpfx.android.lib.network.utils.IpUtils;

import java.net.InetAddress;

/**
 * Created by alexandre on 27/07/15.
 */
public class WifiNetworkManager {

    private Context context;
    private WifiManager wifiManager;


    public WifiNetworkManager(WifiManager manager) {
        this.wifiManager = manager;
    }


    public InetAddress getInetAddress() {
        final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        return IpUtils.intToInetAddress(connectionInfo.getIpAddress());
    }

    public void scan(){
        wifiManager.startScan();
    }

}
