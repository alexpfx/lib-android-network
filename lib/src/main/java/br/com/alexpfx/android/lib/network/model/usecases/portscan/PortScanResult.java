package br.com.alexpfx.android.lib.network.model.usecases.portscan;

import br.com.alexpfx.android.lib.network.model.AddressPort;
import br.com.alexpfx.android.lib.network.model.PortStatus;

import java.net.InetAddress;

/**
 * Created by alexandre on 29/07/15.
 */
public class PortScanResult implements ScanResult {

    private final AddressPort addressPort;
    private final PortStatus status;

    public PortScanResult(AddressPort addressPort, PortStatus status) {
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
