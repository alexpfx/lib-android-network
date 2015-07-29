package br.com.alexpfx.android.lib.network.domain;

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
    private ThreadExecutor threadExecutor = new ThreadExecutor(20);
    private List<Integer> openPorts = new ArrayList<>();
    private Callback callback;
    private int numberOfPorts;
    private int totalScanned = 0;

    public RangePortScannerUseCaseImpl(InetAddress inetAddress, int initPort, int finalPort, int timeout) {
        this.inetAddress = inetAddress;
        if (initPort > finalPort) throw new IllegalArgumentException("initPort > finalport");
        this.initPort = initPort;
        this.finalPort = finalPort;
        this.timeout = timeout;
        numberOfPorts = finalPort - initPort;
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
    public void onStatus(PortStatus status, InetAddress inetAddress, int port) {
        synchronized (this) {
            totalScanned++;
            callback.onUpdateStatus((double) totalScanned * 100d / numberOfPorts);
            if (PortStatus.OPEN.equals(status)) {
                openPorts.add(port);
            }
            if (totalScanned == numberOfPorts) {
                callback.onPortScanFinish(openPorts);
            }
        }
    }
}
