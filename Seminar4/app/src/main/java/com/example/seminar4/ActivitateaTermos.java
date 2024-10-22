package com.example.seminar4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateaTermos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitatea_termos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnTermos = findViewById(R.id.button2);
        btnTermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText denumire = findViewById(R.id.editTextText2);
                Spinner spin=findViewById(R.id.spinner2);
                EditText detalii = findViewById(R.id.editTextText3);
                RadioGroup rdg=findViewById(R.id.rGROUP);
                RatingBar rtg=findViewById(R.id.ratingBar);

                int alesSpin= Integer.parseInt(spin.getSelectedItem().toString());
                String denumireString=denumire.getText().toString();
                String detaliiString=detalii.getText().toString();
                boolean curat = rdg.isSelected();
                float grade=rtg.getNumStars();

                Termos t1 = new Termos(denumireString,alesSpin,detaliiString,curat,grade);

                Toast.makeText(this,t1.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }


}