package br.com.alexpfx.android.lib.network.model.usecases.scan;

import br.com.alexpfx.android.lib.network.model.PortStatus;

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
