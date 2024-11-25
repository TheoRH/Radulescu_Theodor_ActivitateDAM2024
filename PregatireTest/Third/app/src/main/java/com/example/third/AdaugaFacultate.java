package com.example.third;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaFacultate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_facultate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn1 = findViewById(R.id.createUniversity);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText test = findViewById(R.id.university);
                EditText numar = findViewById(R.id.number);
                boolean verificare=true;

                if(test.getText().toString().isEmpty())
                {
                    test.setError("Err");
                    verificare=false;
                }
                if(numar.getText().toString().isEmpty())
                {
                    numar.setError("Err");
                    verificare=false;
                }

                if(verificare)
                {
                    String name = test.getText().toString();
                    int nr = Integer.parseInt(numar.getText().toString());

                    Facultate f = new Facultate(name,nr);
                    Intent it = new Intent();
                    it.putExtra("facultate",f);
                    setResult(RESULT_OK,it);
                    finish();
                }
            }
        });
    }
}