package br.com.alexpfx.android.lib.network.view.fragments.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.alexpfx.android.lib.network.R;
import br.com.alexpfx.android.lib.network.wifi.WifiNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 02/08/2015.
 */
public class WifiNetworkAdapter extends RecyclerView.Adapter<WifiNetworkAdapter.ViewHolder> implements RecyclerViewAdapter<WifiNetwork> {

    private List<ViewModel> list = new ArrayList<>();


    @Override
    public void notityDataChanged() {
        notifyDataSetChanged();
    }

    public void add(WifiNetwork info) {
        ViewModel viewModel = new ViewModel();
        viewModel.authType = info.getCapabilities();
        viewModel.mac = info.getBssid();
        viewModel.ssdi = info.getSsid();
        list.add(viewModel);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wifi_network_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvAuthType.setText(list.get(i).authType);
        viewHolder.tvMac.setText(list.get(i).mac);
        viewHolder.tvSsid.setText(list.get(i).ssdi);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSsid;
        public TextView tvMac;
        public TextView tvAuthType;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSsid = (TextView) itemView.findViewById(R.id.tvSsid);
            tvMac = (TextView) itemView.findViewById(R.id.tvMac);
            tvAuthType = (TextView) itemView.findViewById(R.id.tvAuthType);

        }
    }

    class ViewModel {
        String ssdi;
        String mac;
        String authType;
    }

}
