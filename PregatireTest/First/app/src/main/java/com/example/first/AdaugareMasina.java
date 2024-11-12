package com.example.first;

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

public class AdaugareMasina extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_masina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn1 = findViewById(R.id.buttondCreeazaMasina);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText denumire = findViewById(R.id.marca);
                EditText km = findViewById(R.id.km);
                EditText consum = findViewById(R.id.consum);
                EditText detalii = findViewById(R.id.detalii);
                RadioGroup rdg = findViewById(R.id.busit);
                Log.d("#click", "AM DAT CLICK");

                boolean verificare = true;

                if(denumire.getText().toString().trim().isEmpty())
                {
                    denumire.setError("Campul este obligatoriu");
                    verificare=false;
                }

                if(km.getText().toString().trim().isEmpty())
                {
                    km.setError("Campul este obligatoriu");
                    verificare=false;
                }

                if(consum.getText().toString().trim().isEmpty())
                {
                    consum.setError("Campul este obligatoriu");
                    verificare=false;

                }
                if(detalii.getText().toString().trim().isEmpty())
                {
                    detalii.setError("Campul este obligatoriu");
                    verificare=false;
                }

                int selectID = rdg.getCheckedRadioButtonId();

                if(selectID==-1)
                {
                    Toast.makeText(getApplicationContext(),"Trebuie sa selectezi o varianta",Toast.LENGTH_LONG).show();
                    verificare=false;
                }



                if (verificare) {

                    String marca = denumire.getText().toString();
                    int kilometraj = Integer.parseInt(km.getText().toString());
                    float consumption = Float.parseFloat(consum.getText().toString());
                    String description = detalii.getText().toString();


                    boolean accident = (selectID == R.id.busitDA);


                    Masina m1 = new Masina(kilometraj, marca, consumption, accident, description);

                    Log.d("#obiect",m1.toString());
                    Intent intent = new Intent();
                    intent.putExtra("masini", m1);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Ceva nu a mers bine",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}