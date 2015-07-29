package br.com.alexpfx.android.lib.network.domain;

import java.net.InetAddress;

/**
 * Created by alexandre on 28/07/15.
 */
public class RangePortScannerUseCaseImpl implements PortScannerUseCase, CheckPortUseCase.Callback {

    private InetAddress inetAddress;
    private int initPort;
    private int finalPort;
    private int timeout;
    private ThreadExecutor threadExecutor = new ThreadExecutor(20);

    public RangePortScannerUseCaseImpl(InetAddress inetAddress, int initPort, int finalPort, int timeout) {
        this.inetAddress = inetAddress;
        if (initPort > finalPort) throw new IllegalArgumentException("initPort > finalport");
        this.initPort = initPort;
        this.finalPort = finalPort;
        this.timeout = timeout;
    }

    @Override
    public void execute() {
        threadExecutor.execute(this);
    }

    @Override
    public void run() {
        for (int i = initPort; i <= finalPort; i++) {
            CheckPortUseCase c = new CheckPortUseCaseImpl(threadExecutor, inetAddress, i, timeout, this);
            c.execute();
        }
    }

    @Override
    public void onStatus(PortStatus status, InetAddress inetAddress, int port) {
        if (PortStatus.OPEN.equals(status)) {
            System.out.println("open: " + port);
        }

    }
}
