package com.example.first21;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private List<ImaginiHusaTelefon> img =null;
    private Context ctx;
    private int resursaLayout;

    public ImagineAdapter(List<ImaginiHusaTelefon> img, Context ctx, int resursaLayout) {
        this.img = img;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
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

        LayoutInflater inflater = LayoutInflater.from(ctx);

        View v = inflater.inflate(resursaLayout,parent,false);

        ImageView imageView = v.findViewById(R.id.Radulescu_Theodor_imagine);
        TextView textView = v.findViewById(R.id.Radulescu_Theodor_text_imagine);

        ImaginiHusaTelefon imgH = (ImaginiHusaTelefon) getItem(position);
        textView.setText(imgH.getText());

        imageView.setImageBitmap(imgH.getIamgine());

        return v;
    }
}
