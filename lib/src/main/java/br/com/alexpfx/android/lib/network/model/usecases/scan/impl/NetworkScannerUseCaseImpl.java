package br.com.alexpfx.android.lib.network.model.usecases.scan.impl;

import br.com.alexpfx.android.lib.network.model.PortStatus;
import br.com.alexpfx.android.lib.network.model.ThreadExecutor;
import br.com.alexpfx.android.lib.network.model.usecases.scan.CheckPortUseCase;
import br.com.alexpfx.android.lib.network.model.usecases.scan.NetworkScannerUseCase;
import br.com.alexpfx.android.lib.network.model.usecases.scan.ScanResult;

import java.net.InetAddress;
import java.util.List;

/**
 * Created by alexandre on 28/07/15.
 */
/*Faz scan em um range de ips para verificar se determinada porta esta aberta */
public class NetworkScannerUseCaseImpl implements NetworkScannerUseCase, CheckPortUseCase.Callback {

    private List<InetAddress> inetAddressList;
    private ThreadExecutor threadExecutor = new ThreadExecutor(20);
    private int timeout;
    private int port;
    private Callback callback;


    @Override
    public void run() {
        for (InetAddress inetAddress : inetAddressList) {
            CheckPortUseCase c = new CheckPortUseCaseImpl(threadExecutor, inetAddress, port, timeout, this);
            c.execute();
        }
    }

    @Override
    public void onResult(ScanResult scanResult) {
        if (PortStatus.OPEN.equals(scanResult.getPortStatus())) {
            System.out.println(scanResult.getPortStatus());
            System.out.println(port);
        }
    }

    @Override
    public void execute(List<InetAddress> inetAddressList, int port, int timeout, Callback callback) {
        this.inetAddressList = inetAddressList;
        this.port = port;
        this.timeout = timeout;
        this.callback = callback;
        threadExecutor.execute(this);
    }
}
