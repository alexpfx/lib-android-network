package br.com.alexpfx.android.lib.network.domain;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by alexandre on 27/07/15.
 */
public class WifiService {

    private Context context;
    private WifiManager wifiManager;

    public WifiService(Context context) {
        this.context = context;
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }


    public WifiInfo getWifiInfo (){
        return wifiManager.getConnectionInfo();
    }









}
