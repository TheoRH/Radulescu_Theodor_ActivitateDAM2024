package com.example.second2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class AdapteriMAGINI extends BaseAdapter {
    private List<ImaginiVesela> img = null;
    private Context ctx;
    private int resursa;

    public List<ImaginiVesela> getImg() {
        return img;
    }

    public void setImg(List<ImaginiVesela> img) {
        this.img = img;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public int getResursa() {
        return resursa;
    }

    public void setResursa(int resursa) {
        this.resursa = resursa;
    }

    public AdapteriMAGINI(List<ImaginiVesela> img, Context ctx, int resursa) {
        this.img = img;
        this.ctx = ctx;
        this.resursa = resursa;
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return img.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(ctx);

        View v = inflater.inflate(resursa,parent,false);

        ImageView img = v.findViewById(R.id.IMAGINE);
        ImaginiVesela imgH=(ImaginiVesela)getItem(position);

        img.setImageBitmap(imgH.get);
        return v;
    }
}
