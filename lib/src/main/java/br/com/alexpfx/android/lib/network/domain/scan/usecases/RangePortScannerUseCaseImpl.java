package br.com.alexpfx.android.lib.network.domain.scan.usecases;

import br.com.alexpfx.android.lib.network.domain.PortStatus;
import br.com.alexpfx.android.lib.network.domain.ThreadExecutor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 28/07/15.
 */
public class RangePortScannerUseCaseImpl implements PortScannerUseCase, CheckPortUseCase.Callback {

    private InetAddress inetAddress;
    private int initPort;
    private int finalPort;
    private int timeout;

    private List<Integer> openPorts = new ArrayList<>();
    private Callback callback;
    private int numberOfPorts;
    private int totalScanned = 0;

    private ThreadExecutor threadExecutor;

    public RangePortScannerUseCaseImpl(InetAddress inetAddress, int initPort, int finalPort, int timeout, int threads) {
        this.inetAddress = inetAddress;
        if (initPort > finalPort) throw new IllegalArgumentException("initPort > finalport");
        this.initPort = initPort;
        this.finalPort = finalPort;
        this.timeout = timeout;
        numberOfPorts = finalPort - initPort;
        threadExecutor = new ThreadExecutor(threads);

    }

    @Override
    public void execute(Callback callback) {
        this.callback = callback;
        threadExecutor.execute(this);

    }

    @Override
    public void run() {
        callback.onPortScanStart();
        for (int i = initPort; i <= finalPort; i++) {
            CheckPortUseCase c = new CheckPortUseCaseImpl(threadExecutor, inetAddress, i, timeout, this);
            c.execute();
        }
    }

    @Override
    public void onResult(PortScanResult scanResult) {
        synchronized (this) {
            totalScanned++;
            callback.onUpdateStatus((double) totalScanned * 100d / numberOfPorts);
            if (PortStatus.OPEN.equals(scanResult.getPortStatus())) {
                openPorts.add(scanResult.getPort());
            }
            if (totalScanned == numberOfPorts) {
                callback.onPortScanFinish(openPorts);
            }
        }
    }
}
