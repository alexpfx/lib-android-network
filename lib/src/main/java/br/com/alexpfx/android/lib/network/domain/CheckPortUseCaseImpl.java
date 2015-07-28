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

    @Override
    public void execute(ThreadExecutor threadExecutor, InetAddress inetAddress, int timeout, int port, Callback callback) {
        this.inetAddress = inetAddress;
        this.timeout = timeout;
        this.port = port;
        this.callback = callback;

        threadExecutor.execute(this);
    }

    @Override
    public void run() {
        Socket s = new Socket();
        try {
            s.connect(new InetSocketAddress(inetAddress, port), timeout);
            s.close();
            callback.onStatus("open");
        } catch (IOException e) {
            callback.onError(e);
        }

    }
}
