package com.example.first21;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Online extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_online);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<Bitmap> imagini = new ArrayList<>();
        List<ImaginiHusaTelefon> lista = new ArrayList<>();

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://cache.mobiledirect.ro/foto/husa-pentru-iphone-16-pro-max---tech-protect-magsafe-with-lens-black_1704501111227339_large.jpg");
        linkuriImagini.add("https://cache.mobiledirect.ro/foto/husa-pentru-iphone-16-pro-max---cubz-drop-proof-magsafe-with-lens-red_1683621109590540_large.jpg");

        Executor executors = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executors.execute(new Runnable() {
            @Override
            public void run() {
                for(String link : linkuriImagini)
                {
                    HttpURLConnection con = null;
                    try{
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        if(con!=null)
                            con.disconnect();
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(imagini.size()==linkuriImagini.size())
                        {
                            lista.add(new ImaginiHusaTelefon("https://www.mobiledirect.ro/husa-pentru-iphone-16-pro-max---cubz-drop-proof-magsafe-with-lens-red_is2w203t2x2u2.html","Husa1",imagini.get(0)));
                            lista.add(new ImaginiHusaTelefon("https://www.mobiledirect.ro/husa-pentru-iphone-16-pro-max---tech-protect-magsafe-with-lens-black_is2x2s2u2w2s2.html","Husa2",imagini.get(1)));

                            ListView lv = findViewById(R.id.Radulescu_Theodor_lista_imagini);
                            ImagineAdapter adapter = new ImagineAdapter(lista,getApplication(),R.layout.online_modificat);
                            lv.setAdapter(adapter);
                        }
                    }
                });
            }

        });

        ListView lv = findViewById(R.id.Radulescu_Theodor_lista_imagini);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), Web.class);
                it.putExtra("link",lista.get(position).getUrl());
                startActivity(it);
            }
        });
    }

}