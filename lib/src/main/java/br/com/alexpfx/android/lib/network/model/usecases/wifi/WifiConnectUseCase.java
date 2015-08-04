package br.com.alexpfx.android.lib.network.model.usecases.wifi;

import br.com.alexpfx.android.lib.network.data_objects.wifi.WifiNetwork;
import br.com.alexpfx.android.lib.network.model.Interactor;
import br.com.alexpfx.android.lib.network.utils.ThreadExecutor;

/**
 * Created by alexandre on 29/07/15.
 */
public interface WifiConnectUseCase extends Interactor {

    void execute(ThreadExecutor threadExecutor, WifiNetwork wifiNetwork, Callback callback);

    interface Callback {
        void onWifiConnectionSuccess(int netId, WifiNetwork wifiNetwork);

        void onWifiConnectionFailure(int netId, WifiNetwork wifiNetwork);

    }

}
