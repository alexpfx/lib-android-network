package br.com.alexpfx.android.lib.network.domain;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 28/07/15.
 */
public class NetworkScannerUseCaseImpl implements NetworkScannerUseCase {

    private List<InetAddress> inetAddressList;


    private ThreadExecutor threadExecutor = new ThreadExecutor(40);

    public NetworkScannerUseCaseImpl() {

    }

    @Override
    public void run() {


    }

    @Override
    public void execute(List<InetAddress> inetAddressList, Callback callback) {
        this.inetAddressList = inetAddressList;
        threadExecutor.execute(this);
        for (InetAddress inetAddress:inetAddressList){


        }

    }
}
