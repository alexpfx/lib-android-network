package br.com.alexpfx.android.lib.network.domain;

import android.util.Log;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 28/07/15.
 */
public class NetworkScannerUseCaseImpl implements NetworkScannerUseCase, CheckPortUseCase.Callback {

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
        for (InetAddress inetAddress:inetAddressList){
            CheckPortUseCase c = new CheckPortUseCaseImpl();
            c.execute(threadExecutor, inetAddress, 220, 8008, this);
        }
        System.out.println("end");

    }

    @Override
    public void onStatus(String s) {
        System.out.println(s);

    }

    @Override
    public void onError(Throwable t) {

    }
}
