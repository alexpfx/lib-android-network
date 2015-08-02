package br.com.alexpfx.android.lib.network.model.usecases.wifi.impl;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import br.com.alexpfx.android.lib.network.domain.WifiNetwork;
import br.com.alexpfx.android.lib.network.model.ThreadExecutor;
import br.com.alexpfx.android.lib.network.model.usecases.wifi.WifiConnectUseCase;

/**
 * Created by alexandre on 29/07/15.
 */
public class OpenWifiConnectUseCaseImpl implements WifiConnectUseCase {


    WifiManager wifiManager;
    private int netId;
    private WifiNetwork wifiNetwork;
    private Callback callback;

    public OpenWifiConnectUseCaseImpl(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    @Override
    public void execute(ThreadExecutor threadExecutor, WifiNetwork wifiNetwork, Callback callback) {
        this.wifiNetwork = wifiNetwork;
        this.callback = callback;
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = wifiNetwork.getSsid();
        wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        netId = wifiManager.addNetwork(wifiConfiguration);

        threadExecutor.execute(this);
    }

    @Override
    public void run() {
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        //TODO: precisa do reconnect?
        //TODO: stackoverflow.com/questions/15755251/is-android-providing-any-listeners-or-callback-methods-when-connected-or-disconn

        /*
        //ta errado, o retorno do metodo nao indica conexao, mas apenas se o metodo foi executado. deve-se usar receiver proprio.
        if (connected) {
            final android.net.wifi.WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            callback.onWifiConnectionSuccess(netId, wifiInfo);
        } else {
            callback.onWifiConnectionFailure(netId, wifiInfo);
        }
        */
    }
}
