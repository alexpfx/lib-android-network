package br.com.alexpfx.android.lib.network.model;

/**
 * Created by alexandre on 30/07/15.
 */
    public enum WifiSecurityMode {

        WEP("WEP"), WPA("WPA"), WPA2("WPA2"),//....
        UNKNOWN ("UNKNOWN");

        private String name;

        WifiSecurityMode (String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
