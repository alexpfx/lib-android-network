package br.com.alexpfx.android.lib.network.data_objects.wifi;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 29/07/15.
 */
public class WifiList {

    private List<WifiNetwork> wifiNetworkList = new ArrayList<>();

    public void add(WifiNetwork wifiNetwork) {
        wifiNetworkList.add(wifiNetwork);
    }

    public Iterator<WifiNetwork> iterator() {
        return wifiNetworkList.iterator();
    }

    public List<WifiNetwork> getOpenWifis() {
        List<WifiNetwork> open = new ArrayList<>();
        for (WifiNetwork wifiNetwork : wifiNetworkList) {
            if (wifiNetwork.testAgainstSecurityModes(
                    WifiSecurityMode.WEP,
                    WifiSecurityMode.WPA,
                    WifiSecurityMode.WPA2)
                    .equals(WifiSecurityMode.UNKNOWN)) {
                open.add(wifiNetwork);
            }
        }
        return open;
    }


}
