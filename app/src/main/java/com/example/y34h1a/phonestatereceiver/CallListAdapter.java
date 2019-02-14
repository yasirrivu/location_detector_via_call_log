package com.example.y34h1a.phonestatereceiver;

/**
 * Created by tareq on 8/17/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class CallListAdapter extends RecyclerView.Adapter<CallListAdapter.RecyclerViewHolders> {

    private List<CallInfo> callInfos;
    private Context context;

    public CallListAdapter(Context context, List<CallInfo> callInfos) {
        this.callInfos = callInfos;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_call_list, parent,false);
        return new RecyclerViewHolders(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

        String latitute = callInfos.get(position).getLatitute();
        String longitute = callInfos.get(position).getLongitude();
        holder.phnNumber.setText(callInfos.get(position).getPhnNumber());
        holder.location.setText("Lat: " + latitute + " " + " Long: " + longitute);

    }

    @Override
    public int getItemCount() {
        return this.callInfos.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        private TextView phnNumber;
        private TextView location;


        private RecyclerViewHolders(View itemView) {
            super(itemView);

            phnNumber = (TextView) itemView.findViewById(R.id.tvPhnNumber);
            location = (TextView) itemView.findViewById(R.id.tvLocation);
        }


    }
}
