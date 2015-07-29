package br.com.alexpfx.android.lib.network.domain;

/**
 * Created by alexandre on 28/07/15.
 */
public interface PortScannerUseCase extends Interactor {


    void execute();

    interface Callback {
        void onStatus ();
    }


}
