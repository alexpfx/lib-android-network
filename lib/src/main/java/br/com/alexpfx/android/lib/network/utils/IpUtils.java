package br.com.alexpfx.android.lib.network.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 28/07/15.
 */
public class IpUtils {

    public static int inetAddressToInt(Inet4Address inetAddr)
            throws IllegalArgumentException {
        byte[] addr = inetAddr.getAddress();
        return ((addr[3] & 0xff) << 24) | ((addr[2] & 0xff) << 16) |
                ((addr[1] & 0xff) << 8) | (addr[0] & 0xff);
    }

    public static InetAddress intToInetAddress(int hostAddress) {
        byte[] addressBytes = {(byte) (0xff & hostAddress),
                (byte) (0xff & (hostAddress >> 8)),
                (byte) (0xff & (hostAddress >> 16)),
                (byte) (0xff & (hostAddress >> 24))};

        try {
            return InetAddress.getByAddress(addressBytes);
        } catch (UnknownHostException e) {
            //TODO
        }
        return null;
    }

    /**
     * Obtem todos ips da rede.
     *
     * @param inetAddress
     * @return
     */
    public static List<InetAddress> getSubNetIpRange(final InetAddress inetAddress) {
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
