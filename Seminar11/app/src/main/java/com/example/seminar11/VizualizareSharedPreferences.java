package com.example.seminar11;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class VizualizareSharedPreferences extends AppCompatActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizare_shared_preferences);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView lv = findViewById(R.id.listViewShared);
        sp=getSharedPreferences("termosuriFavorite",MODE_PRIVATE);
        List<String> termosList = getTermosListFromPreferences();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,termosList);
          lv.setAdapter(adapter);
    }

   private List<String> getTermosListFromPreferences(){
       List<String> termosList = new ArrayList<>();

       Map<String, ?> all=sp.getAll();

       for(Map.Entry<String,?> entry : all.entrySet())
       {
           String value = entry.getValue().toString();

           String termosDetalii = parseTermosDetalii(value);


           if(termosDetalii!=null)
               termosList.add(termosDetalii);
       }
       return termosList;
    }

    private String parseTermosDetalii(String t)
    {
        if(t.startsWith("Termos{")&& t.endsWith("}"))
        {
            return t.substring(t.indexOf("{")+1,t.lastIndexOf("}"));
        }
        return null;

    }




}