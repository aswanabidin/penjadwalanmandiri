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

import com.example.aswanabidin.penjadwalanmandiri.Fragments.AnakFragment;
import com.example.aswanabidin.penjadwalanmandiri.HalamanDetailProfilAnakOrtu;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahAnakOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 9/21/17.
 */

public class TambahAnakAnakOrtuAdapter extends RecyclerView.Adapter<TambahAnakAnakOrtuAdapter.MyViewHolder> {

    AdapterView.OnItemClickListener itemClickListener;
    StorageReference mStorageRef;
    private ArrayList<TambahAnakOrtuModel> tambahAnakOrtuModels = new ArrayList<>();
    private Activity activity;
    private Context context;

    public TambahAnakAnakOrtuAdapter(ArrayList<TambahAnakOrtuModel> tambahAnakOrtuModels) {
        this.tambahAnakOrtuModels = tambahAnakOrtuModels;
    }

    public TambahAnakAnakOrtuAdapter(Context context) {
        this.context = context;
    }


    @Override
    public TambahAnakAnakOrtuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_anak_ortu, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TambahAnakAnakOrtuAdapter.MyViewHolder holder, int position) {

        final TambahAnakOrtuModel tambahAnakOrtuModel = tambahAnakOrtuModels.get(position);

        holder.tvnamaanak.setText(tambahAnakOrtuModel.getNamaanakortu());
        holder.tvtmplahiranak.setText(tambahAnakOrtuModel.getTempatlahiranakortu());
        holder.tvtgllahiranak.setText(tambahAnakOrtuModel.getTgllahiranakortu());
        holder.tvemailanak.setText(tambahAnakOrtuModel.getEmailanakortu());
        holder.tvnmapenggunaanak.setText(tambahAnakOrtuModel.getNamapenggunaanakortu());
//        holder.tvkatasandi.setText(tambahAnakOrtuModel.getKatasandianakortu());
        Picasso.with(context).load(tambahAnakOrtuModel.getUrl()).fit().centerCrop().into(holder.imageView);
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanDetailProfilAnakOrtu.class);
                intent.putExtra("tambahanakdariortu", tambahAnakOrtuModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return (tambahAnakOrtuModels == null) ? 0 : tambahAnakOrtuModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //viewholder akan mendiskripsikan item view yang ditempatkan di dalam recyclerview
        private TextView tvnamaanak, tvtmplahiranak, tvtgllahiranak, tvemailanak, tvnmapenggunaanak, tvkatasandi;
        private ImageView imageView;
        private CardView cardView;
        private View itemCard;
        ArrayList<TambahAnakOrtuModel> tambahAnakOrtuModels = new ArrayList<>();

        public MyViewHolder(View itemView) {
            super(itemView);

            tvnamaanak = (TextView) itemView.findViewById(R.id.tvNamaAnak);
            tvtmplahiranak = (TextView) itemView.findViewById(R.id.tvTempatLahirAnak);
            tvtgllahiranak = (TextView) itemView.findViewById(R.id.tvTanggalLahirAnak);
            tvemailanak = (TextView) itemView.findViewById(R.id.tvAlamatEmailAnak);
            tvnmapenggunaanak = (TextView) itemView.findViewById(R.id.tvUsernameAnak);
//            tvkatasandi = (TextView) itemView.findViewById(R.id.tvkatansandi);
            imageView = (ImageView)itemView.findViewById(R.id.imgprofilAnak);
            itemCard = (View) itemView.findViewById(R.id.item_card);
        }
    }

    public void addData(TambahAnakOrtuModel im){
        tambahAnakOrtuModels.add(im);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void SetOnItemClickListener(final TambahJadwalAnakOrtuAdapeter.OnItemClickListener mItemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
