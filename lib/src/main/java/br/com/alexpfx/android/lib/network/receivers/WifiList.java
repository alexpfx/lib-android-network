package br.com.alexpfx.android.lib.network.receivers;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alexandre on 29/07/15.
 */
public class WifiList {

    private List<WifiInfo> wifiInfoList = new ArrayList<>();

    public void add(WifiInfo wifiInfo) {
        wifiInfoList.add(wifiInfo);
    }

    public Iterator<WifiInfo> iterator() {
        return wifiInfoList.iterator();
    }

    public List<WifiInfo> getOpenWifis() {
        List<WifiInfo> open = new ArrayList<>();
        for (WifiInfo w : wifiInfoList) {
            if (w.testAgainstSecurityModes("WEP", "WPA").equals("open")) {
                open.add(w);
            }
        }
        return open;
    }


}
