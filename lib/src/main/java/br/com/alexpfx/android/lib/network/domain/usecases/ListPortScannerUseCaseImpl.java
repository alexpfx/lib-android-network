package br.com.alexpfx.android.lib.network.domain.usecases;

import br.com.alexpfx.android.lib.network.domain.PortStatus;
import br.com.alexpfx.android.lib.network.domain.ThreadExecutor;

import java.net.InetAddress;

/**
 * Created by alexandre on 29/07/15.
 */
public class ListPortScannerUseCaseImpl implements PortScannerUseCase, CheckPortUseCase.Callback {

    private final InetAddress inetAddress;
    private final int[] ports;
    private final int timeout;
    private final int threads;

    //TODO injetar
    private ThreadExecutor threadExecutor;
    private Callback callback;

    private ListPortScannerUseCaseImpl (InetAddress inetAddress, int [] ports, int timeout, int threads){
        this.inetAddress = inetAddress;
        this.ports = ports;
        this.timeout = timeout;
        this.threads = threads;
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
        for (int port:ports) {
            CheckPortUseCase c = new CheckPortUseCaseImpl(threadExecutor, inetAddress, port, timeout, this);
            c.execute();
        }
    }

    @Override
    public void onResult(PortStatus status, InetAddress inetAddress, int port) {

    }
}
