package br.com.alexpfx.android.lib.network.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 28/07/15.
 */
public class Network {

    /**
     * Obtem todos ips da rede.
     *
     * @param inetAddress
     * @return
     */
    public List<InetAddress> getIpAddressRange(final InetAddress inetAddress) {
        List<InetAddress> hosts = new ArrayList<>();
        String baseIpAddress = inetAddress.getHostAddress();
        String subnet = baseIpAddress.substring(0, baseIpAddress.lastIndexOf(".") + 1);

        for (int i = 1; i < 256; i++) {
            final String host = subnet.concat(String.valueOf(i));
            try {
                final InetAddress inetByHostName = InetAddress.getByName(host);
                hosts.add(inetByHostName);
            } catch (UnknownHostException e) {

            }
        }
        return hosts;
    }


}
