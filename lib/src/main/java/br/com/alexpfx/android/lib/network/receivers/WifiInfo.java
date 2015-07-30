package br.com.alexpfx.android.lib.network.receivers;

import android.net.wifi.ScanResult;
import android.os.Build;

/**
 * Created by alexandre on 29/07/15.
 */
public class WifiInfo {

    private final String capabilities;
    private final String ssid;
    private final String bssid;
    private final int frequency;
    private final int level;
    private long timestamp = 0L;

    public static WifiInfo fromScanResult(ScanResult scanResult) {
        String capabilities = scanResult.capabilities;
        String ssid = scanResult.SSID;
        String bssid = scanResult.BSSID;
        int frequency = scanResult.frequency;
        int level = scanResult.level;
        long timestamp = 0L;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            timestamp = scanResult.timestamp;
        }
        return new WifiInfo(capabilities, ssid, bssid, frequency, level, timestamp);
    }

    public WifiInfo(String capabilities, String ssid, String bssid, int frequency, int level, long timestamp) {
        this.capabilities = capabilities;
        this.ssid = ssid;
        this.bssid = bssid;
        this.frequency = frequency;
        this.level = level;
        this.timestamp = timestamp;
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

    public String testAgainstSecurityModes(String... securities) {
        if (capabilities.equals("")) {
            return "open";
        }
        for (String security : securities) {
            if (capabilities.toLowerCase().contains(security)) {
                return security;
            }
        }
        return "none_of_these";
    }
}
