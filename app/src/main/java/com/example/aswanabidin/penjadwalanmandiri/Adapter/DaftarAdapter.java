package com.example.aswanabidin.penjadwalanmandiri.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 9/28/17.
 */

public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.MyViewHolder> {

    AdapterView.OnItemClickListener itemClickListener;
    StorageReference mStorageRef;
    private ArrayList<DaftarAdapter> daftarAdapters = new ArrayList<>();
    private Activity activity;
    private Context context;

    public DaftarAdapter (ArrayList<DaftarAdapter> daftarAdapters){
        this.daftarAdapters = daftarAdapters;
    }

    public DaftarAdapter (Context context){
        this.context = context;
    }

    @Override
    public DaftarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DaftarAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addData(DaftarAdapter im) {
        daftarAdapters.add(im);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
