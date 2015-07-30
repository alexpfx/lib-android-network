package br.com.alexpfx.android.lib.network.model.usecases.portscan;

import br.com.alexpfx.android.lib.network.model.Interactor;

/**
 * Created by alexandre on 28/07/15.
 */
public interface CheckPortUseCase extends Interactor {

    void execute();

    interface Callback {
        void onResult(ScanResult scanResult);
    }
}

