package br.com.alexpfx.android.lib.network.domain;

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
        } catch (IOException e) {
            callback.onStatus(PortStatus.CLOSED, inetAddress, port);
        }
        callback.onStatus(PortStatus.OPEN, inetAddress, port);

    }
}
