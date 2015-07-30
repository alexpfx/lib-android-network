package br.com.alexpfx.android.lib.network.domain.scan.usecases;

import java.net.InetAddress;

/**
 * Created by alexandre on 29/07/15.
 */
public class AddressPort {

    private final InetAddress address;
    private final int port;


    public AddressPort(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
