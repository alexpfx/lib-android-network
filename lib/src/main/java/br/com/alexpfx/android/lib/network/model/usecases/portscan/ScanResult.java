package br.com.alexpfx.android.lib.network.model.usecases.portscan;

import br.com.alexpfx.android.lib.network.data_objects.PortStatus;

import java.net.InetAddress;

/**
 * Created by alexandre on 29/07/15.
 */
public interface ScanResult {

    PortStatus getPortStatus();

    InetAddress getInetAddress();

    int getPort();

    boolean isOpen();

}
