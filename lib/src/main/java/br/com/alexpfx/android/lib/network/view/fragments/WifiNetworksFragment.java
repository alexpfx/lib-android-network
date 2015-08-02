package br.com.alexpfx.android.lib.network.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.alexpfx.android.lib.network.R;
import br.com.alexpfx.android.lib.network.receivers.WifiInfo;
import br.com.alexpfx.android.lib.network.view.fragments.adapters.WifiNetworkAdapter;

public class WifiNetworksFragment extends Fragment {

    RecyclerView rvWifiNetworks;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public WifiNetworksFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wifi_networks, container, false);
        rvWifiNetworks = (RecyclerView) getView(view, R.id.rvWifiNetworks);

        layoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        rvWifiNetworks.setLayoutManager(layoutManager);

        adapter = new WifiNetworkAdapter();
        ((WifiNetworkAdapter) adapter).addWifi(new WifiInfo("Wep", "teste", "sfa", 10, 10, 1L));
        ((WifiNetworkAdapter) adapter).addWifi(new WifiInfo("Wep", "teste", "sfa", 10, 10, 1L));

        rvWifiNetworks.setAdapter(adapter);
        return view;
    }

    private View getView(View base, int id) {
        return base.findViewById(id);
    }

}
