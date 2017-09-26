package com.example.aswanabidin.penjadwalanmandiri.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aswanabidin.penjadwalanmandiri.HalamanDaftar;
import com.example.aswanabidin.penjadwalanmandiri.Model.ItemOrtuModel;
import com.example.aswanabidin.penjadwalanmandiri.R;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by aswanabidin on 9/19/17.
 */

public class SpinnerAdapter extends ArrayAdapter<ItemOrtuModel> {

    int groupid;
    Activity context;
    ArrayList<ItemOrtuModel> list;
    LayoutInflater inflater;

    public SpinnerAdapter(Activity context, int groupid, int id, ArrayList<ItemOrtuModel>list) {
        super(context, id, list);
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = groupid;
    }



    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = inflater.inflate(groupid,parent,false);
        TextView textView = (TextView) itemView.findViewById(R.id.txtspinner);
        textView.setText(list.get(position).getText());
        return itemView;
    }

    public View getDropDownView (int position, View convertView, ViewGroup parent){
        return getView(position,convertView,parent);
    }
}
