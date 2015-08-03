package br.com.alexpfx.android.lib.network.view.fragments.adapters;

/**
 * Created by alexandre on 02/08/15.
 */
public interface RecyclerViewAdapter<T> {

    void notityDataChanged();
    void add(T value);
}
