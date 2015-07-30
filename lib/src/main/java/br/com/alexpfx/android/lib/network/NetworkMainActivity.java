package br.com.alexpfx.android.lib.network;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import br.com.alexpfx.android.lib.network.model.ThreadExecutor;
import br.com.alexpfx.android.lib.network.model.WifiNetworkManager;
import br.com.alexpfx.android.lib.network.model.usecases.portscan.NetworkScannerUseCase;
import br.com.alexpfx.android.lib.network.model.usecases.portscan.PortScannerUseCase;
import br.com.alexpfx.android.lib.network.model.usecases.portscan.impl.NetworkScannerUseCaseImpl;
import br.com.alexpfx.android.lib.network.model.usecases.portscan.impl.RangePortScannerUseCaseImpl;
import br.com.alexpfx.android.lib.network.model.usecases.wifi.WifiConnectUseCase;
import br.com.alexpfx.android.lib.network.model.usecases.wifi.impl.OpenWifiConnectUseCaseImpl;
import br.com.alexpfx.android.lib.network.receivers.WifiInfo;
import br.com.alexpfx.android.lib.network.receivers.WifiList;
import br.com.alexpfx.android.lib.network.receivers.WifiScanResultBroadcastReceiver;
import br.com.alexpfx.android.lib.network.utils.IpUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;


//TODO: essa eh uma activity para testes. para implementações oficiais usar fragmentos.
public class NetworkMainActivity extends ActionBarActivity implements NetworkScannerUseCase.Callback, PortScannerUseCase.Callback, WifiConnectUseCase.Callback {

    private Button btnNetworkscan;
    private Button btnPortScanRange;
    private Button btnWifiScan;
    private long beforeScan;
    private WifiScanResultBroadcastReceiver wifiScanResultBroadcastReceiver;
    private WifiNetworkManager wifiNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_main);
        wifiNetworkManager = new WifiNetworkManager(getWifiManagerService());

        btnNetworkscan = (Button) getView(R.id.btnScan);
        btnNetworkscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNetworkScan();
            }
        });

        btnPortScanRange = (Button) getView(R.id.btnPortScanRange);
        btnPortScanRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPortRangeScan();
            }
        });

        btnWifiScan = (Button) getView(R.id.btnWifiScan);
        btnWifiScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnWifiScan();
            }
        });

        final Bus bus = new Bus();
        bus.register(this);
        wifiScanResultBroadcastReceiver = new WifiScanResultBroadcastReceiver(bus);

    }

    private void onBtnWifiScan() {
        wifiNetworkManager.scan();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_network_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up btnNetworkscan, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    //Test
    public void onNetworkScan() {
        final InetAddress inetAddress = wifiNetworkManager.getInetAddress();
        final List<InetAddress> networkInetAddresses = IpUtils.getSubNetIpRange(inetAddress);
        new NetworkScannerUseCaseImpl().execute(networkInetAddresses, 8008, 250, this);
    }

    private WifiManager getWifiManagerService() {
        return (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
    }

    public void onPortRangeScan() {

        try {
            final InetAddress byName = InetAddress.getByName("192.168.25.119");
            PortScannerUseCase u = new RangePortScannerUseCaseImpl(byName, 2, 65123, 40, 50);
            u.execute(this);
        } catch (UnknownHostException e) {


        }
    }


    public View getView(int resId) {
        return findViewById(resId);
    }

    @Override
    public void onResultReceived(String result) {
        System.out.println(result);
    }

    @Override
    public void onPortScanStart() {
        beforeScan = System.currentTimeMillis();
        System.out.println("scan started ");
    }

    @Override
    public void onUpdateStatus(double percent) {
//        System.out.format("%.2f%s\n", percent,"%");
    }

    @Override
    public void onPortScanFinish(List<Integer> openPorts) {
        long afterScan = System.currentTimeMillis();
        for (Integer port : openPorts) {
            System.out.println("open: " + port);
        }
        System.out.println("tempo scan em segundos: " + TimeUnit.MILLISECONDS.toSeconds(afterScan - beforeScan));

    }

    @Override
    protected void onResume() {
        registerReceiver(wifiScanResultBroadcastReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(wifiScanResultBroadcastReceiver);
        super.onPause();
    }


    @Subscribe
    public void onScanResultReceived(WifiList list) {
        final List<WifiInfo> openWifis = list.getOpenWifis();
        for (WifiInfo w : openWifis) {
            WifiConnectUseCase connectUseCase = new OpenWifiConnectUseCaseImpl(getWifiManagerService());
            connectUseCase.execute(new ThreadExecutor(2), w, this);
        }
    }

    @Override
    public void onWifiConnectionSuccess(int netId, WifiInfo wifiInfo) {
        System.out.println(netId);
    }

    @Override
    public void onWifiConnectionFailure(int netId, WifiInfo wifiInfo) {
        System.out.println(netId);
    }
}
