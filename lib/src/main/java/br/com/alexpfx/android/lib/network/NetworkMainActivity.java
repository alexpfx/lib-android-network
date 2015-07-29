package br.com.alexpfx.android.lib.network;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import br.com.alexpfx.android.lib.network.domain.*;
import br.com.alexpfx.android.lib.network.domain.usecases.NetworkScannerUseCase;
import br.com.alexpfx.android.lib.network.domain.usecases.NetworkScannerUseCaseImpl;
import br.com.alexpfx.android.lib.network.domain.usecases.PortScannerUseCase;
import br.com.alexpfx.android.lib.network.domain.usecases.RangePortScannerUseCaseImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class NetworkMainActivity extends ActionBarActivity implements NetworkScannerUseCase.Callback, PortScannerUseCase.Callback {

    private Button btnNetworkscan;
    private Button btnPortScanRange;
    private long beforeScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_main);

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
        final WifiNetwork wifiNetwork = new WifiNetwork((WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE));
        final InetAddress inetAddress = wifiNetwork.getInetAddress();
        Network n = new Network();
        final List<InetAddress> networkInetAddresses = n.getIpAddressRange(inetAddress);
        new NetworkScannerUseCaseImpl().execute(networkInetAddresses, 8008, 250, this);
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
}
