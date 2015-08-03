package br.com.alexpfx.android.lib.network.domain;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.Build;
import br.com.alexpfx.android.lib.network.model.WifiSecurityMode;

/**
 * Created by alexandre on 29/07/15.
 */
public class WifiNetwork {

    private final String capabilities;
    private final String ssid;
    private final String bssid;
    private final int frequency;
    private final int level;
    private long timestamp = 0L;

    public WifiNetwork(String capabilities, String ssid, String bssid, int frequency, int level, long timestamp) {
        this.capabilities = capabilities;
        this.ssid = ssid;
        this.bssid = bssid;
        this.frequency = frequency;
        this.level = level;
        this.timestamp = timestamp;
    }


    public static WifiNetwork fromWifiInfo(WifiInfo wifiInfo) {
        return new WifiNetwork(null, wifiInfo.getSSID(), wifiInfo.getBSSID(), wifiInfo.getFrequency(), wifiInfo.getRssi(), 0L);
    }

    public static WifiNetwork fromScanResult(ScanResult scanResult) {
        String capabilities = scanResult.capabilities;
        String ssid = scanResult.SSID;
        String bssid = scanResult.BSSID;
        int frequency = scanResult.frequency;
        int level = scanResult.level;
        long timestamp = 0L;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            timestamp = scanResult.timestamp;
        }
        return new WifiNetwork(capabilities, ssid, bssid, frequency, level, timestamp);
    }

    public String getCapabilities() {
        return capabilities;
    }

    public String getSsid() {
        return ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getLevel() {
        return level;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public WifiSecurityMode testAgainstSecurityModes(WifiSecurityMode... securities) {
        for (WifiSecurityMode security : securities) {
            if (this.capabilities.toUpperCase().contains(security.getName().toUpperCase())) {
                return security;
            }
        }
        return WifiSecurityMode.UNKNOWN;
    }

    @Override
    public String toString() {
        return ssid;
    }
}
