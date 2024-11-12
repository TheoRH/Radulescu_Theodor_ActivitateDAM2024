package com.example.first;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListareMasini extends AppCompatActivity {

    private ArrayList<Masina>masiniLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listare_masini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv = findViewById(R.id.ListaMasinute);

        masiniLista=getIntent().getParcelableArrayListExtra("masiniList");
        if(masiniLista!=null)
        {
            Log.d("#LISTA", "onCreate: AVEM CEVA AICI"+masiniLista.size());
            ArrayAdapter<Masina> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,masiniLista);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),masiniLista.get(position).toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}