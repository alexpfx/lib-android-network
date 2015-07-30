package br.com.alexpfx.android.lib.network.domain.usecases;

import br.com.alexpfx.android.lib.network.domain.PortStatus;

import java.net.InetAddress;

/**
 * Created by alexandre on 29/07/15.
 */
public class PortScanResultImpl implements PortScanResult {

    private final AddressPort addressPort;
    private final PortStatus status;

    public PortScanResultImpl(AddressPort addressPort, PortStatus status) {
        this.addressPort = addressPort;
        this.status = status;
    }


    @Override
    public PortStatus getPortStatus() {
        return status;
    }

    @Override
    public InetAddress getInetAddress() {
        return addressPort.getAddress();
    }

    @Override
    public int getPort() {
        return addressPort.getPort();
    }

    @Override
    public boolean isOpen() {
        return PortStatus.OPEN.equals(status);
    }
}
