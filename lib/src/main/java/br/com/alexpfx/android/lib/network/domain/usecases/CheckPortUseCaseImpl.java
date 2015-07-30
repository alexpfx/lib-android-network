package br.com.alexpfx.android.lib.network.domain.usecases;

import br.com.alexpfx.android.lib.network.domain.PortStatus;
import br.com.alexpfx.android.lib.network.domain.ThreadExecutor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by alexandre on 28/07/15.
 */
public class CheckPortUseCaseImpl implements CheckPortUseCase {

    private InetAddress inetAddress;
    private int timeout;
    private int port;
    private Callback callback;
    private ThreadExecutor threadExecutor;

    public CheckPortUseCaseImpl(ThreadExecutor threadExecutor, InetAddress inetAddress, int port, int timeout, Callback callback) {
        this.inetAddress = inetAddress;
        this.timeout = timeout;
        this.port = port;
        this.callback = callback;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public void execute() {
        threadExecutor.execute(this);

    }

    @Override
    public void run() {
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress(inetAddress, port), timeout);
            s.close();
            callback.onResult(PortStatus.OPEN, inetAddress, port);
        } catch (IOException e) {
            callback.onResult(PortStatus.CLOSED, inetAddress, port);
        }
    }
}
