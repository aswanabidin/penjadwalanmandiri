package com.example.aswanabidin.penjadwalanmandiri.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.HalamanDetailJadwalOrtu;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahJadwalOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 9/23/17.
 */

public class TambahJadwalAnakAdapter extends RecyclerView.Adapter<TambahJadwalAnakAdapter.MyViewHolder>{

    AdapterView.OnItemClickListener itemClickListener;
    StorageReference mStorageRef;
    private ArrayList<TambahJadwalOrtuModel> tambahJadwalOrtuModels = new ArrayList<>();
    private Activity activity;
    private Context context;

    public TambahJadwalAnakAdapter(ArrayList<TambahJadwalOrtuModel> tambahJadwalOrtuModels){
        this.tambahJadwalOrtuModels = tambahJadwalOrtuModels;
    }

    public TambahJadwalAnakAdapter(Context context){
        this.context = context;
    }

    @Override
    public TambahJadwalAnakAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_jadwal_anak, parent, false); //punya layout cardview
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TambahJadwalOrtuModel tambahJadwalOrtuModel = tambahJadwalOrtuModels.get(position);

        holder.namakegiatan.setText(tambahJadwalOrtuModel.getNamakegiatanortu());
        holder.poinkegiatan.setText(tambahJadwalOrtuModel.getPoinortu());
        holder.tglkegiatan.setText(tambahJadwalOrtuModel.getTglortu());
        holder.waktukegiatan.setText(tambahJadwalOrtuModel.getWaktuortu());
        Picasso.with(context).load(tambahJadwalOrtuModel.getUrl()).fit().centerCrop().into(holder.imageView);
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanDetailJadwalOrtu.class);
                intent.putExtra("tambahjadwaldariortu", tambahJadwalOrtuModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //tadi error nih
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return (tambahJadwalOrtuModels == null) ? 0 : tambahJadwalOrtuModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //viewholder akan mendiskripsikan item view yang ditempatkan di dalam recyclerview
        private TextView namakegiatan, poinkegiatan, tglkegiatan, waktukegiatan, catatankegiatan;
        private ImageView imageView;
        private CardView cardView;
        private View itemCard;
        ArrayList<TambahJadwalOrtuModel> tambahJadwalOrtuModels = new ArrayList<>();

        public MyViewHolder(View itemView) {
            super(itemView);

            namakegiatan = (TextView) itemView.findViewById(R.id.tvNamaKegiatanJadwal);
            poinkegiatan = (TextView) itemView.findViewById(R.id.tvPoinJadwal);
            tglkegiatan = (TextView) itemView.findViewById(R.id.tvTanggalJadwal);
            waktukegiatan = (TextView) itemView.findViewById(R.id.tvWaktuJadwal);
            imageView = (ImageView) itemView.findViewById(R.id.imgprofilanakJadwal);
            itemCard = (View) itemView.findViewById(R.id.item_card);
        }
    }

    public void addData(TambahJadwalOrtuModel im) {
        tambahJadwalOrtuModels.add(im);
        notifyDataSetChanged();
    }
}
