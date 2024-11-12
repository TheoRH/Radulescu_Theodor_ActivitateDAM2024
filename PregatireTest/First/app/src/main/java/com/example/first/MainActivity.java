package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Masina>masiniLista=new ArrayList<>();
    private boolean isAscending = true;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==210 && resultCode==RESULT_OK&&data !=null)
        {
            Masina m = data.getParcelableExtra("masini");
            if(m!=null)
            {
                masiniLista.add(m);
                Toast.makeText(this,"Obiectul a fost adaugat:"+m.toString(),Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn1 = findViewById(R.id.buttonAdauga);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(getApplicationContext(), AdaugareMasina.class);
                startActivityForResult(it,210);
            }
        });

        Button btn2=findViewById(R.id.buttonListare);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListareMasini.class);
                Log.d("#totcenuaifacut", "onClick: lista masini"+masiniLista.toString());
                it.putParcelableArrayListExtra("masiniList",masiniLista);
                startActivity(it);
            }
        });

        Button btn3 = findViewById(R.id.Sortare);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortListMasini();
                String ordine = isAscending?"crescator":"descrescator";
                Toast.makeText(getApplicationContext(),"Ordine: "+ordine,Toast.LENGTH_LONG).show();
                isAscending=!isAscending;
            }
        });

    }
    private void sortListMasini(){
        if(isAscending){
            Collections.sort(masiniLista, Comparator.comparingInt(Masina::getKm));
        }
        else {
            Collections.sort(masiniLista, (m1,m2)->Integer.compare(m2.getKm(), m1.getKm()));
        }
    }


}