package com.example.seminar11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.List;
import java.util.concurrent.Executors;

public class ActivityLista extends AppCompatActivity {

    private List<Termos> termosList;
    private int idModificat=0;
    private TermosAdapter adapter=null;
    private BazaDeDateTermos bd;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==201)
            {


                termosList.set(idModificat,data.getParcelableExtra("termos"));
                Log.d("#verificarelista", "lista noua "+termosList.toString());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = BazaDeDateTermos.getInstance(getApplicationContext());

        ListView listView=findViewById(R.id.lista_termosuri);
        Handler handler = new Handler(Looper.myLooper());
        Executors.newSingleThreadExecutor().execute(() -> {
            termosList = bd.termosDao().getAll();
            Log.d("#testare", termosList.toString());

            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter=new TermosAdapter(termosList,getApplicationContext(),R.layout.layout_modificat);
                    listView.setAdapter(adapter);
                }
            });

        });


        termosList = getIntent().getParcelableArrayListExtra("termosList");
        if(termosList!=null)
        {
            /*ArrayAdapter<Termos> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,termosList);
            listView.setAdapter(adapter);*/
            adapter=new TermosAdapter(termosList,getApplicationContext(),R.layout.layout_modificat);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intentModifica=new Intent(getApplicationContext(), ActivitateaTermos.class);
                    intentModifica.putExtra("termos",termosList.get(position));
                    idModificat=position;
                    startActivityForResult(intentModifica,201);
                    Toast.makeText(ActivityLista.this,termosList.get(position).toString(),Toast.LENGTH_LONG).show();

                }
            });
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Termos tt=termosList.get(position);
                    //ADAUGARE IN SHAREDPREFERENCES
                    SharedPreferences sp = getSharedPreferences("termosuriFavorite",MODE_PRIVATE);
                    SharedPreferences.Editor editor= sp.edit();
                    editor.putString(tt.getKey(),tt.toString());
                    editor.commit();

                    //STERGERE CU BAZA DE DATE
//                    Log.d("#tt", termosList.get(position).toString());
//                    Executors.newSingleThreadExecutor().execute(()->{
//                        bd.termosDao().delete(tt);
//                        handler.post(()->{
//                            termosList.remove(position);
//                            adapter.notifyDataSetChanged();
//                            Toast.makeText(ActivityLista.this, "Termos șters!", Toast.LENGTH_SHORT).show();
//                        });
//                    });
//                    bd.termosDao().delete();
                    /*termosList.remove(position);
                    adapter.notifyDataSetChanged();*/
                    return false;
                }
            });
        }
    }
}