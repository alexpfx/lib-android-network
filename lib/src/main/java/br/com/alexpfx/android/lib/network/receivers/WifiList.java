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

}
