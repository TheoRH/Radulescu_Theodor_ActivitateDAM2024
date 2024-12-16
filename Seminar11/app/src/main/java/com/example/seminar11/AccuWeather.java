package com.example.seminar11;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class AccuWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accu_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ListView lv=findViewById(R.id.vizualizareAcc);

List<AccuAPI> acc=new ArrayList<>();
        ArrayAdapter<AccuAPI>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,acc);
lv.setAdapter(adapter);
        Button btn = findViewById(R.id.buttonCautareAccu);
        btn.setOnClickListener(b -> {
            String linkDeBaza="http://dataservice.accuweather.com/locations/v1/cities/search?apikey=fKKJElApYbep6hJpjvYh1Fl4PHYHfc8l&q=";
            EditText text = findViewById(R.id.textAccuOras);
            if(!text.getText().toString().isEmpty()){
                linkDeBaza+=text.getText().toString();
                Toast.makeText(this, linkDeBaza.toString(), Toast.LENGTH_SHORT).show();


                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());

                String finalLinkDeBaza = linkDeBaza;
                String keyy;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection con = null;
                        try{
                            URL url = new URL(finalLinkDeBaza);
                            con = (HttpURLConnection) url.openConnection();
                            InputStream is = con.getInputStream();


                            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                            StringBuilder jsonBuilder = new StringBuilder();
                            String line;
                            while((line= reader.readLine())!=null){
                                jsonBuilder.append(line);
                            }
                            String json = jsonBuilder.toString();

                            JSONArray vector = new JSONArray(jsonBuilder.toString());
                            JSONObject obiect = vector.getJSONObject(0);
                            String key=obiect.getString("Key");
                            AccuAPI a1=new AccuAPI(key);
                            acc.add(a1);



                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(acc!=null){
                                    for (AccuAPI a:acc) {
                                        Log.d("#accu",a.getKey());
                                        adapter.notifyDataSetChanged();

                                    }
                                }

                            }
                        });
                    }
                });


            }
            else {
                Toast.makeText(this, "SCRIE CEVA", Toast.LENGTH_SHORT).show();
            }


        });

    }



}