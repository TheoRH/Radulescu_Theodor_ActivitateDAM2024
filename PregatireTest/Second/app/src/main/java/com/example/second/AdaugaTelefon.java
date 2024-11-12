package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaTelefon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_telefon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn1 = findViewById(R.id.creeazaTelefon);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText marca = findViewById(R.id.marca);
                EditText dimensiune = findViewById(R.id.dimensiune);
                EditText baterie = findViewById(R.id.baterie);
                RadioGroup rdg = findViewById(R.id.spart);
                int selectedID=rdg.getCheckedRadioButtonId();

                boolean verificare=true;

                if(marca.getText().toString().trim().isEmpty())
                {
                    marca.setError("Camp oblig");
                    verificare=false;
                }
                if(dimensiune.getText().toString().trim().isEmpty())
                {
                    dimensiune.setError("Camp oblig");
                    verificare=false;
                }
                if(baterie.getText().toString().trim().isEmpty())
                {
                    baterie.setError("Camp oblig");
                    verificare=false;
                }
                if(selectedID==-1)
                {
                    Toast.makeText(AdaugaTelefon.this, "Eroare", Toast.LENGTH_SHORT).show();
                    verificare=false;
                }

                if(verificare)
                {
                    String name = marca.getText().toString();
                    int size = Integer.parseInt(dimensiune.getText().toString());
                    float battery = Float.parseFloat(baterie.getText().toString());
                    boolean spart =false;
                    if(selectedID==R.id.spartDA)
                        spart=true;

                    Telefon t = new Telefon(name,size,battery,spart);
                    Log.d("#telefon",t.toString());
                    Intent it = new Intent();
                    it.putExtra("telefon",t);
                    setResult(RESULT_OK,it);
                    finish();


                }

            }
        });
    }
}