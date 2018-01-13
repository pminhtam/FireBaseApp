package com.example.minhtam.firebasekhoapham.appli;

import android.app.Activity;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.minhtam.firebasekhoapham.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Minh Tam on 1/12/2018.
 */

public class HienThiAdapter extends BaseAdapter{
    Activity activity;
    ArrayList<ItemImage> imgs;

    public HienThiAdapter(Activity activity, ArrayList<ItemImage> imgs){
        this.activity = activity;
        this.imgs = imgs;
    }
    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public Object getItem(int position) {
        return imgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.itemimg,null);
        ImageView imgItem = (ImageView) convertView.findViewById(R.id.imgItem);
        Log.e("HienThiAdapter",imgs.get(position).link);
        Picasso.with(activity).load(imgs.get(position).link).into(imgItem);

        return convertView;
    }
}
