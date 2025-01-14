package com.example.first21;

import android.content.Intent;
import android.media.MediaTimestamp;
import android.os.Bundle;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public class CreareHusaTelefon extends AppCompatActivity {

    private BazaDeDateHuse bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_creare_husa_telefon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bd=BazaDeDateHuse.getInstance(this);

        Button btn1 = findViewById(R.id.Radulescu_Theodor_creeaza_husa);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText material = findViewById(R.id.Radulescu_Theodor_material_husa);
                EditText lungime = findViewById(R.id.Radulescu_Theodor_lungime_husa);
                RadioGroup rdg = findViewById(R.id.Radulescu_Theodor_avariat_grup);
                if(material.getText().toString().isEmpty())
                    material.setError("Trebuie completat");
                if(lungime.getText().toString().isEmpty())
                    lungime.setError("Trebioe");
                if (rdg.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(CreareHusaTelefon.this, "PROBLEM", Toast.LENGTH_SHORT).show();
                }
                String m = material.getText().toString();
                double l = Double.parseDouble(lungime.getText().toString());
                boolean av = false;
                if(rdg.getCheckedRadioButtonId()==R.id.Radulescu_Theodor_avariat_da)
                    av=true;
                HusaTelefon h1 = new HusaTelefon(m,l,av);
                salveazaFisier(h1);
                Toast.makeText(CreareHusaTelefon.this, h1.toString(), Toast.LENGTH_SHORT).show();
                Intent it = new Intent();
                it.putExtra("huse",h1);
                Executors.newSingleThreadExecutor().execute(()->{
                    bd.husaDAO().insert(h1);
                });
                setResult(RESULT_OK,it);
                finish();
            }
        });
    }
    private void salveazaFisier(HusaTelefon h) {
        File file = new File(getFilesDir(), "huse.txt");
        String husadata = h.toString() + "\n";
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(husadata.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }