package br.com.alexpfx.android.lib.network;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import br.com.alexpfx.android.lib.network.domain.Network;
import br.com.alexpfx.android.lib.network.domain.WifiNetwork;

import java.net.InetAddress;
import java.util.List;


public class NetworkMainActivity extends ActionBarActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_main);

        button = (Button) getView(R.id.btnScan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScanClick();
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
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;

       }

        return super.onOptionsItemSelected(item);
    }

    //Test
    public void onScanClick (){
        final WifiNetwork wifiNetwork = new WifiNetwork((WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE));
        final InetAddress inetAddress = wifiNetwork.getInetAddress();
        System.out.println(inetAddress.getHostAddress());
        Network n = new Network();
        final List<InetAddress> networkInetAddresses = n.getIpAddressRange(inetAddress);
    }

    public View getView (int resId){
        return findViewById(resId);
    }
}
