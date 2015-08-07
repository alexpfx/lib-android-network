package br.com.alexpfx.android.lib.network.view.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.alexpfx.android.lib.base.tag.Tags;
import br.com.alexpfx.android.lib.network.R;
import br.com.alexpfx.android.lib.network.data_objects.wifi.WifiNetwork;
import br.com.alexpfx.android.lib.network.receivers.WifiConnectionUpdateReceiver;
import br.com.alexpfx.android.lib.network.receivers.WifiScanResultBroadcastReceiver;
import br.com.alexpfx.android.lib.network.utils.BusProvider;
import br.com.alexpfx.android.lib.network.view.fragments.adapters.RecyclerViewAdapter;
import br.com.alexpfx.android.lib.network.view.fragments.adapters.WifiNetworkAdapter;
import br.com.alexpfx.android.lib.network.view.utils.Views;
import com.squareup.otto.Subscribe;

public class WifiNetworkFragment extends Fragment {

    public static final String ANDROID_NET_CONN_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    private RecyclerView rvWifiNetworks;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter<WifiNetwork> adapter;

    private Button btnWifiScan;

    private WifiConnectionUpdateReceiver wifiConnectionUpdateReceiver;
    private WifiScanResultBroadcastReceiver wifiScanResultBroadcastReceiver;
    private FragmentInteractorListener listener;

    @Override
    public void onAttach(Activity activity) {
        try {
            listener = (FragmentInteractorListener) activity;
        } catch (ClassCastException e) {
            Log.e(Tags.className(), Tags.methodName());
        }
        super.onAttach(activity);
    }

    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        BusProvider.getInstance().register(this);

        View view = inflater.inflate(R.layout.fragment_wifi_networks, container, false);
        setupRecyclerView(view);
        wifiConnectionUpdateReceiver = new WifiConnectionUpdateReceiver();

        setupButton(view);

        return view;
    }

    private void setupButton(View view) {
        btnWifiScan = Views.getView(view, R.id.btnStartWifiScan, Button.class);
        btnWifiScan.setOnClickListener(new View.OnClickListener() {
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
    public void onStop() {
        getActivity().getApplicationContext().unregisterReceiver(wifiConnectionUpdateReceiver);
        super.onStop();
    }

    @Override
    public void onStart() {
        getActivity().getApplicationContext().registerReceiver(wifiConnectionUpdateReceiver, new IntentFilter(ANDROID_NET_CONN_CONNECTIVITY_CHANGE));
        super.onStart();
    }


    @Subscribe
    public void receiveConnectionInfo(WifiConnectionUpdateReceiver.WifiConnectionInfoEvent wifiConnectionInfoEvent) {
        final NetworkInfo networkInfo = wifiConnectionInfoEvent.getNetworkInfo();
        final WifiInfo wifiInfo = wifiConnectionInfoEvent.getWifiInfo();
        if (listener != null) {
            listener.onWifiReceiver(networkInfo, wifiInfo);
        }
        WifiNetwork wifiNetwork = WifiNetwork.fromWifiInfo(wifiConnectionInfoEvent.getWifiInfo());
        adapter.add(wifiNetwork);
        adapter.notityDataChanged();

    }


    public interface FragmentInteractorListener {
        void onWifiReceiver(NetworkInfo networkInfo, WifiInfo info);
    }


}
