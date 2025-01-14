package com.example.seminar13;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class VizualizareImaginiiTermos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizare_imaginii_termos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<Bitmap> imagini = new ArrayList<>();

        List<ImaginiTermos> lista = new ArrayList<>();

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://1.bonami.ro/images/fit-crop-fill/f4/48/f4489ce17fd57532a6a0656d6461ac9f2f712d31-600x600.jpeg");
        linkuriImagini.add("https://contents.mediadecathlon.com/p2579201/k$3e3faf4e1f24a66fd47eeb967ceabb6e/sq/termos-inox-drumetie-1l-cu-pahar-albastru.jpg?format=auto&f=1200x1200");
        linkuriImagini.add("https://gymbeam.ro/_next/image?url=https%3A%2F%2Fgymbeam.ro%2Fmedia%2Fcatalog%2Fproduct%2Fcache%2F70f742f66feec18cb83790f14444a3d1%2Ft%2Fh%2Fthermo_tumbler_1_2_l_black_gymbeam_1.jpg&w=1200&q=75");
        linkuriImagini.add("https://lcdn.altex.ro/resize/media/catalog/product/c/a/2bd48d28d1c32adea0e55139a4e6434a/can48035sz_45ee70a7.jpg");
        linkuriImagini.add("https://waterdrop.ro/cdn/shop/files/ClassicSteel-1000ml-BlackMatt-Standing-Studio_39e10351-702e-49db-ae34-be2de114fbd8_900x.png?v=1718825792");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (con != null)
                            con.disconnect();
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imagini.size() == linkuriImagini.size()) {
                            lista.add(new ImaginiTermos("Stanley Mov", imagini.get(0), "https://www.bonami.ro/p/termos-mov-1-18-l-quencher-h2o-flowstate-stanley?utm_source=google&utm_medium=cpc&utm_campaign=PMax%20-%20Promo&utm_id=16979544940&gad_source=1&gclid=Cj0KCQiA6Ou5BhCrARIsAPoTxrD5bUePAeMHiUgDr8Y1CfQEBvtUof-GPhLa8sfP9b36KxoeXIO7EqcaAumLEALw_wcB"));
                            lista.add(new ImaginiTermos("Termos inox Drumeție", imagini.get(1), "https://www.decathlon.ro/p/termos-inox-drumetie-1l-cu-pahar-albastru/_/R-p-108985?mc=8734560&gad_source=1&gclid=Cj0KCQiA6Ou5BhCrARIsAPoTxrAN5OxZltXW7cMV6lpHftjYkk8VcGKUa1tTeibMaWEeAo9GiWEpRLgaAiZIEALw_wcB&utm_campaign=ro_t-perf_ct-pmax_n-g-pmax-veg_ts-gen_f-cv_o-conv_spd-cyc_spu-cy_pt-all_l-ro_pp-gads_bm-mcn_sg-na_fo-dpa_DKT&utm_medium=cpc&utm_source=google"));
                            lista.add(new ImaginiTermos("Termos 1.2 l Black", imagini.get(2), "https://gymbeam.ro/termos-1-2-l-black-gymbeam.html?utm_source=google&utm_medium=cpc&utm_campaign=PMax%20-%20All%20products&utm_id=19689279064&gad_source=1&gclid=Cj0KCQiA6Ou5BhCrARIsAPoTxrBZYt-Ri07_7eGcGc5WAPFukOBd9bVl-l-8EUPSAcNwG1TKy0JRA0QaAiOLEALw_wcB#90373"));
                            lista.add(new ImaginiTermos("Termos BANQUET", imagini.get(3), "https://altex.ro/termos-banquet-48035s-z-0-35l-argintiu/cpd/CAN48035SZ/?cq_src=google_ads&cq_cmp=17487725420&cq_con=&cq_term=&cq_med=pla&cq_plac=&cq_net=x&cq_pos=&cq_plt=gp&gad_source=1&gclid=Cj0KCQiA6Ou5BhCrARIsAPoTxrCLN1sQ_hT9etbuAQ0e9TMcn-a5PxyrfRZSBsJmAgTpOs378XnZXrgaAvcCEALw_wcB"));
                            lista.add(new ImaginiTermos("Termos din Oțel Inoxidabil", imagini.get(4), "https://waterdrop.ro/products/sticla-termo-din-otel-inoxidabil?currency=RON&variant=49902039564619&utm_source=google&utm_medium=cpc&utm_campaign=Google%20Shopping&stkn=1fe048f3ac58&utm_source=google&utm_medium=&utm_campaign=19694391773&utm_term=&utm_content=&utm_id=&gclid=Cj0KCQiA6Ou5BhCrARIsAPoTxrAMyhgf01kxvc5oO2NTGma1bq1Foi-f9Ex7KBLFW1akjE-23HqDFc4aAszYEALw_wcB&gad_source=1"));

                            ListView lv = findViewById(R.id.lista_imaigni_termos);
                            ImagineAdapter adapter = new ImagineAdapter(lista, getApplicationContext(), R.layout.layout_imagini_modificat);
                            lv.setAdapter(adapter);

                        } else {
                            Log.d("#imagini", "Eroare la incarcarea imaginilor");
                        }
                    }
                });

            }

        });
        ListView lv = findViewById(R.id.lista_imaigni_termos);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebAcitivty.class);
                it.putExtra("link",lista.get(position).getLink());
                startActivity(it);
            }
        });

    }

}