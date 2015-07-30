package br.com.alexpfx.android.lib.network.domain.usecases;

import br.com.alexpfx.android.lib.network.domain.Interactor;
import br.com.alexpfx.android.lib.network.domain.PortStatus;

import java.net.InetAddress;

/**
 * Created by alexandre on 28/07/15.
 */
public interface CheckPortUseCase extends Interactor {

    void execute();

    interface Callback {
        void onResult(PortStatus status, InetAddress inetAddress, int port);
    }
}

