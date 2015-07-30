package br.com.alexpfx.android.lib.network.domain.usecases;

import br.com.alexpfx.android.lib.network.domain.PortStatus;

import java.net.InetAddress;

/**
 * Created by alexandre on 29/07/15.
 */
public interface PortScanResult {

    PortStatus getPortStatus();

    InetAddress getInetAddress();

    int getPort();

    boolean isOpen();

}
