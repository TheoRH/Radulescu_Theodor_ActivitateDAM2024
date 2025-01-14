package com.example.second2;

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

public class AdaugareVesela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_vesela);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button bt = findViewById(R.id.creeazaVESELA);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText first = findViewById(R.id.denumireTEXT);
                EditText second = findViewById(R.id.numarTEXT);
                if(first.getText().toString().isEmpty())
                    first.setError("PROBLEM");
                if(second.getText().toString().isEmpty())
                    second.setError("PROBLEM");

                String d;
                int n;
                d=first.getText().toString();
                n= Integer.parseInt(second.getText().toString());

                Vesela v1 = new Vesela(d,n);
                Intent it=new Intent();
                it.putExtra("vesele",v1);
                setResult(RESULT_OK,it);
                finish();

            }
        });
    }
}