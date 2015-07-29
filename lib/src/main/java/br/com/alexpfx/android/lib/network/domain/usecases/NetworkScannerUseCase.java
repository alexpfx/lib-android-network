package br.com.alexpfx.android.lib.network.domain.usecases;

import br.com.alexpfx.android.lib.network.domain.Interactor;

import java.net.InetAddress;
import java.util.List;

/**
 * Created by alexandre on 28/07/15.
 */
public interface NetworkScannerUseCase extends Interactor {

    void execute(List<InetAddress> inetAddressList, int port, int timeout, Callback callback);

    interface Callback {
        void onResultReceived(String result);
    }
}
