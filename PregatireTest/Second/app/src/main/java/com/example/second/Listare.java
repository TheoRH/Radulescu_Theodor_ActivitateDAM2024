package com.example.second;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Listare extends AppCompatActivity {

    private ArrayList<Telefon>telefoaneLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv = findViewById(R.id.listaTelefoane);
        telefoaneLista=getIntent().getParcelableArrayListExtra("telefon");
        if(telefoaneLista!=null)
        {
            ArrayAdapter<Telefon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,telefoaneLista);
            lv.setAdapter(adapter);

        }
    }
}