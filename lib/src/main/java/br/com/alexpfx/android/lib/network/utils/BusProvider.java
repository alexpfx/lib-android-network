package br.com.alexpfx.android.lib.network.utils;

import com.squareup.otto.Bus;

/**
 * Created by alexandre on 04/08/15.
 */
public class BusProvider {
    private static Bus instance = new Bus();

    public static Bus getInstance() {
        return instance;
    }

}
