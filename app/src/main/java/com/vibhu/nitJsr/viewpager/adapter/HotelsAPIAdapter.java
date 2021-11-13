package com.vibhu.nitJsr.viewpager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vibhu.nitJsr.viewpager.APIActivity;
import com.vibhu.nitJsr.viewpager.R;
import com.vibhu.nitJsr.viewpager.models.HotelsApiModel;

import java.util.ArrayList;

public class HotelsAPIAdapter extends RecyclerView.Adapter<HotelsAPIAdapter.ViewHolder> {
    private ArrayList<HotelsApiModel> list;

    public HotelsAPIAdapter(ArrayList<HotelsApiModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t1,t2,t3,t4,t5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.group);
            t2=itemView.findViewById(R.id.geoId);
            t3=itemView.findViewById(R.id.longitude);
            t4=itemView.findViewById(R.id.latitude);
            t5=itemView.findViewById(R.id.name);
        }

        public void setData(int position) {
            t1.setText(list.get(position).getGroup());
            t2.setText(list.get(position).getGeoId());
            t3.setText(list.get(position).getLongitude());
            t4.setText(list.get(position).getLatitude());
            t5.setText(list.get(position).getName());
        }
    }
}
