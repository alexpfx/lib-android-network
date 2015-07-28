package br.com.alexpfx.android.lib.network.domain;

import java.net.InetAddress;

/**
 * Created by alexandre on 28/07/15.
 */
public interface CheckPortUseCase extends Interactor{
    void execute (ThreadExecutor threadExecutor, InetAddress inetAddress, int timeout, int port, Callback callback);

    interface Callback {
        void onStatus (String s);
        void onError (Throwable t);
    }
}

