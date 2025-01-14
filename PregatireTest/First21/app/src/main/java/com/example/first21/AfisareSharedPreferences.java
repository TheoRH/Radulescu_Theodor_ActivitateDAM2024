package com.example.first21;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AfisareSharedPreferences extends AppCompatActivity {

    private List<String> lista=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_shared_preferences);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv = findViewById(R.id.Radulescu_Theodor_list_view_shared);
        SharedPreferences sd = getSharedPreferences("huseFavorite",MODE_PRIVATE);
        Map<String,?> all=sd.getAll();
        for(Map.Entry<String,?> entry:all.entrySet())
        {
            String value = entry.getValue().toString();
            if(value!=null)
                lista.add(value);
        }
        ArrayAdapter adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adapter);

    }
}