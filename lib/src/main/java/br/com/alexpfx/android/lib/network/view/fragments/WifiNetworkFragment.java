package br.com.alexpfx.android.lib.network.view.fragments;

import android.app.Fragment;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.alexpfx.android.lib.network.R;
import br.com.alexpfx.android.lib.network.domain.WifiNetwork;
import br.com.alexpfx.android.lib.network.receivers.ConnectionUpdateReceiver;
import br.com.alexpfx.android.lib.network.view.common.Views;
import br.com.alexpfx.android.lib.network.view.fragments.adapters.RecyclerViewAdapter;
import br.com.alexpfx.android.lib.network.view.fragments.adapters.WifiNetworkAdapter;

public class WifiNetworkFragment extends Fragment implements ConnectionUpdateReceiver.Listener {

    private RecyclerView rvWifiNetworks;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter<WifiNetwork> adapter;
//    private RecyclerViewAdapter <WifiInfo> ad;

    private ConnectionUpdateReceiver connectionUpdateReceiver;


    public WifiNetworkFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wifi_networks, container, false);
        setupRecyclerView(view);
        connectionUpdateReceiver = new ConnectionUpdateReceiver();
        connectionUpdateReceiver.setListener(this);


        return view;
    }

    private void setupRecyclerView(View view) {
        rvWifiNetworks = Views.getView(view, R.id.rvWifiNetworks, RecyclerView.class);
        layoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        rvWifiNetworks.setLayoutManager(layoutManager);
        adapter = new WifiNetworkAdapter();
        rvWifiNetworks.setAdapter((RecyclerView.Adapter) adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onWifiConnected(NetworkInfo networkInfo) {


    }
}
