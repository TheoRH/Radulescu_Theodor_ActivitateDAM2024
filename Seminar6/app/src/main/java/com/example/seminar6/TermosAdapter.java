package com.example.seminar6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class TermosAdapter extends BaseAdapter {

    private List<Termos> termosuri=null;
    private Context ctx;
    private int resursaLayout;

    public TermosAdapter(List<Termos> termosuri, Context ctx, int resursaLayout)
    {
        this.termosuri=termosuri;
        this.ctx=ctx;
        this.resursaLayout=resursaLayout;
    }
    @Override
    public int getCount() {
        return termosuri.size();
    }

    @Override
    public Object getItem(int position) {
        return termosuri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(ctx);


        View v=inflater.inflate(resursaLayout,parent, false);

        TextView numeTermos = v.findViewById(R.id.numeTermos);
        TextView numar=v.findViewById(R.id.numarulTermosului);
        TextView detalii=v.findViewById(R.id.detalii);
       TextView curat=v.findViewById(R.id.curat);
        TextView grade=v.findViewById(R.id.grade);

        Termos t = (Termos)getItem(position);
        numeTermos.setText(t.getNume());

        numar.setText("Numarul: " + t.getNumar());

        detalii.setText("Descriere: "+t.getDetalii());
        if(t.isCurat()==true)
        {
            curat.setText("Este curat?: Da");
        }
        else
            curat.setText("Este curat?: Nu");
        grade.setText(String.valueOf("Grade: "+t.getGrade()));



        return v;

    }
}
