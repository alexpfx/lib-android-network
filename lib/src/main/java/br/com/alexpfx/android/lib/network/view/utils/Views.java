package br.com.alexpfx.android.lib.network.view.utils;

import android.view.View;

/**
 * Created by alexandre on 02/08/15.
 */
public class Views {

    public static <T> T getView(View parent, int resId, Class<T> type) {
        return (T)
                parent.findViewById(resId);
    }

}
