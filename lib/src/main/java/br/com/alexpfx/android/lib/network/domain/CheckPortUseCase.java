package br.com.alexpfx.android.lib.network.domain;

import java.net.InetAddress;

/**
 * Created by alexandre on 28/07/15.
 */
public interface CheckPortUseCase extends Interactor {

    void execute();

    interface Callback {
        void onStatus(PortStatus status, InetAddress inetAddress, int port);
    }
}

