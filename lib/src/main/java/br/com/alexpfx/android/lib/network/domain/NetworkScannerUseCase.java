package br.com.alexpfx.android.lib.network.domain;

import java.net.InetAddress;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by alexandre on 28/07/15.
 */
public interface NetworkScannerUseCase extends Interactor {

    void execute (List <InetAddress> inetAddressList, int port, int timeout, Callback callback);



    public interface Callback {
        void onResultReceived(String result);
    }
}
