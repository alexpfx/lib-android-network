package br.com.alexpfx.android.lib.network.model.usecases.wifi.impl;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import br.com.alexpfx.android.lib.network.model.ThreadExecutor;
import br.com.alexpfx.android.lib.network.model.usecases.wifi.WifiConnectUseCase;
import br.com.alexpfx.android.lib.network.receivers.WifiInfo;

/**
 * Created by alexandre on 29/07/15.
 */
public class OpenWifiConnectUseCaseImpl implements WifiConnectUseCase {


    private int netId;
    private WifiInfo wifiInfo;
    private Callback callback;

    public OpenWifiConnectUseCaseImpl(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    WifiManager wifiManager;


    @Override
    public void execute(ThreadExecutor threadExecutor, WifiInfo wifiInfo, Callback callback) {
        this.wifiInfo = wifiInfo;
        this.callback = callback;
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.BSSID = wifiInfo.getBssid();
        wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        netId = wifiManager.addNetwork(wifiConfiguration);

        threadExecutor.execute(this);
    }

    @Override
    public void run() {
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.enableNetwork(netId, true);
        final boolean connected = wifiManager.reconnect();
        if (connected) {
            callback.onWifiConnectionSuccess(netId, wifiInfo);
        } else {
            callback.onWifiConnectionFailure(netId, wifiInfo);
        }
    }
}
