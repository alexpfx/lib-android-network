package br.com.alexpfx.android.lib.network.domain;

import java.util.List;

/**
 * Created by alexandre on 28/07/15.
 */
public interface PortScannerUseCase extends Interactor {


    void execute(Callback callback);

    interface Callback {
        void onPortScanStart ();
        void onUpdateStatus (double status);
        void onPortScanFinish (List<Integer> openPorts);
    }


}
