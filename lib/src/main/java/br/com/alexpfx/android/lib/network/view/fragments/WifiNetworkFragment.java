package br.com.alexpfx.android.lib.network.view.fragments;

import android.app.Fragment;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.alexpfx.android.lib.network.R;
import br.com.alexpfx.android.lib.network.data_objects.wifi.WifiNetwork;
import br.com.alexpfx.android.lib.network.receivers.WifiConnectionUpdateReceiver;
import br.com.alexpfx.android.lib.network.receivers.WifiScanResultBroadcastReceiver;
import br.com.alexpfx.android.lib.network.view.fragments.adapters.RecyclerViewAdapter;
import br.com.alexpfx.android.lib.network.view.fragments.adapters.WifiNetworkAdapter;
import br.com.alexpfx.android.lib.network.view.utils.Views;

public class WifiNetworkFragment extends Fragment implements WifiConnectionUpdateReceiver.Listener {

    private RecyclerView rvWifiNetworks;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter<WifiNetwork> adapter;

    private Button btnPress;

    private WifiConnectionUpdateReceiver wifiConnectionUpdateReceiver;
    private WifiScanResultBroadcastReceiver wifiScanResultBroadcastReceiver;


    public WifiNetworkFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wifi_networks, container, false);
        setupRecyclerView(view);
        wifiConnectionUpdateReceiver = new WifiConnectionUpdateReceiver();
        wifiConnectionUpdateReceiver.setListener(this);

        setupButton(view);

        return view;
    }

    private void setupButton(View view) {
        btnPress = Views.getView(view, R.id.btnPress, Button.class);
        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
    public void onStop() {
        getActivity().getApplicationContext().unregisterReceiver(wifiConnectionUpdateReceiver);
        super.onStop();
    }

    @Override
    public void onStart() {
        getActivity().getApplicationContext().registerReceiver(wifiConnectionUpdateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        super.onStart();
    }

    @Override
    public void onWifiConnected(NetworkInfo networkInfo, WifiInfo wifiInfo) {
        final WifiNetwork wifiNetwork = WifiNetwork.fromWifiInfo(wifiInfo);
        adapter.add(wifiNetwork);
        adapter.notityDataChanged();
    }
}
