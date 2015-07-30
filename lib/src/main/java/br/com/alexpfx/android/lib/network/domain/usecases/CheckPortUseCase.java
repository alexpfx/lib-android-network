package br.com.alexpfx.android.lib.network.domain.usecases;

import br.com.alexpfx.android.lib.network.domain.Interactor;

/**
 * Created by alexandre on 28/07/15.
 */
public interface CheckPortUseCase extends Interactor {

    void execute();

    interface Callback {
        void onResult(PortScanResult scanResult);
    }
}

