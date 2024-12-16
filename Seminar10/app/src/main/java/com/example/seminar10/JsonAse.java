package com.example.seminar10;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JsonAse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json_ase);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String link = "https://pdm.ase.ro/situatii.json";
        List<Situatie> situatiiList = new ArrayList<>();

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                try{
                    URL url = new URL(link);
                    con = (HttpURLConnection) url.openConnection();
                    InputStream is = con.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    StringBuilder jsonBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        jsonBuilder.append(line);
                    }
                    String json = jsonBuilder.toString();

                    String d, a, data, desc;
                    int valoare;
                    double pondere;

                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("situatii");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonOBject = jsonArray.getJSONObject(i);
                        d=jsonOBject.getString("disciplina");
                        a=jsonOBject.getString("activitate");
                        valoare=jsonOBject.getInt("valoare");
                        pondere= jsonOBject.getDouble("pondere");
                        data=jsonOBject.getString("data");
                        desc=jsonOBject.getString("descriere");

                        Situatie s1=new Situatie(d,a,valoare,pondere,data,desc);
                        situatiiList.add(s1);

                    }


                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } finally {
                    if(con!=null)
                        con.disconnect();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(situatiiList!=null)
                        {
                            for (Situatie s: situatiiList) {
                                Log.d("#situaite",s.toString());

                            }
                        }

                    }
                });

            }
        });

        ListView lv = findViewById(R.id.lista_situatii);

        if(situatiiList!=null)
        {
            ArrayAdapter<Situatie>adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,situatiiList);
            lv.setAdapter(adapter);
        }


    }

}
