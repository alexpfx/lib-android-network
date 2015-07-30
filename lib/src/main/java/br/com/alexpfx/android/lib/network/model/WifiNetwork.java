package br.com.alexpfx.android.lib.network.model;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import br.com.alexpfx.android.lib.network.utils.IpUtils;

import java.net.InetAddress;

/**
 * Created by alexandre on 27/07/15.
 */
public class WifiNetwork {

    private Context context;
    private WifiManager wifiManager;


    public WifiNetwork(WifiManager manager) {
        this.wifiManager = manager;
    }


    public InetAddress getInetAddress() {
        final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        return IpUtils.intToInetAddress(connectionInfo.getIpAddress());
    }

}
