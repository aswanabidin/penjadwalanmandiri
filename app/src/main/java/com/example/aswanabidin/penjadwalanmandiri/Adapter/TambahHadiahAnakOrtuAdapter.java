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

import com.example.aswanabidin.penjadwalanmandiri.Fragments.HadiahFragment;
import com.example.aswanabidin.penjadwalanmandiri.HalamanDetailHadiahOrtu;
import com.example.aswanabidin.penjadwalanmandiri.Model.TambahHadiahOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by aswanabidin on 9/20/17.
 */

public class TambahHadiahAnakOrtuAdapter extends RecyclerView.Adapter<TambahHadiahAnakOrtuAdapter.MyViewHolder> {

    AdapterView.OnItemClickListener itemClickListener;
    StorageReference mStorageRef;
    private ArrayList<TambahHadiahOrtuModel> tambahHadiahOrtuModels = new ArrayList<>();
    private Activity activity;
    private Context context;

    public TambahHadiahAnakOrtuAdapter(ArrayList<TambahHadiahOrtuModel> tambahHadiahOrtuModels) {
        this.tambahHadiahOrtuModels = tambahHadiahOrtuModels;
    }

    public TambahHadiahAnakOrtuAdapter(Context context) {
        this.context = context;
    }


    @Override
    public TambahHadiahAnakOrtuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_hadiah_ortu, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TambahHadiahAnakOrtuAdapter.MyViewHolder holder, int position) {

        final TambahHadiahOrtuModel tambahHadiahOrtuModel = tambahHadiahOrtuModels.get(position);

        holder.tvnamahadiah.setText(tambahHadiahOrtuModel.getNamahadiahortu());
        holder.tvpoinhadiah.setText(tambahHadiahOrtuModel.getPoinhadiahortu());
//        holder.tvdeskripsihadiah.setText(tambahHadiahOrtuModel.getDeskripsihadiahortu());
        Picasso.with(context).load(tambahHadiahOrtuModel.getUrl()).fit().centerCrop().into(holder.imageView);
        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HalamanDetailHadiahOrtu.class);
                intent.putExtra("tambahhadiahdariortu", tambahHadiahOrtuModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (tambahHadiahOrtuModels == null) ? 0 : tambahHadiahOrtuModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //viewholder akan mendiskripsikan item view yang ditempatkan di dalam recyclerview
        private TextView tvnamahadiah, tvpoinhadiah, tvdeskripsihadiah;
        private ImageView imageView;
        private CardView cardView;
        private View itemCard;
        ArrayList<TambahHadiahOrtuModel> tambahHadiahOrtuModels = new ArrayList<>();

        public MyViewHolder(View itemView) {
            super(itemView);

            tvnamahadiah = (TextView) itemView.findViewById(R.id.tvNamaHadiah);
            tvpoinhadiah = (TextView) itemView.findViewById(R.id.tvPoinHadiah);
//            tvdeskripsihadiah = (TextView) itemView.findViewById(R.id.tvdeskripsihadiah);
            imageView = (ImageView) itemView.findViewById(R.id.imgprofilhadiah);
            itemCard = (View) itemView.findViewById(R.id.item_card);
        }
    }

    public void addData(TambahHadiahOrtuModel im){
        tambahHadiahOrtuModels.add(im);
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
