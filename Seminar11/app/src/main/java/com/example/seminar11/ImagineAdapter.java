package com.example.seminar11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {

    private List<ImaginiTermos> imgs=null;
    private Context ctx;
    private int resursaLayout;


    public ImagineAdapter(List<ImaginiTermos> imgs, Context ctx, int resursaLayout) {
        this.imgs = imgs;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
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

        ;
        LayoutInflater inflater = LayoutInflater.from(ctx);

        View v = inflater.inflate(resursaLayout,parent,false);

        ImageView imagine = v.findViewById(R.id.imagineMod);
        TextView textView = v.findViewById(R.id.textImagine);

        ImaginiTermos imgT = (ImaginiTermos) getItem(position);
        textView.setText(imgT.getTextAfisat());

        imagine.setImageBitmap(imgT.getImagine());

        return v;
    }
}
