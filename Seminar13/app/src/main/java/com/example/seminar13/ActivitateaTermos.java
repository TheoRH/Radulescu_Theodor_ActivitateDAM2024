package com.example.seminar13;

import static android.app.PendingIntent.getActivity;
import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public class ActivitateaTermos extends AppCompatActivity {

    private BazaDeDateTermos bd;

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

        bd = BazaDeDateTermos.getInstance(this);

        Intent it=getIntent();
        if(it.hasExtra("termos")){
            Termos termi=it.getParcelableExtra("termos");
            EditText denumire = findViewById(R.id.editTextText2);
            Spinner spin=findViewById(R.id.spinner2);
            EditText detalii = findViewById(R.id.editTextText3);
            RadioGroup rdg=findViewById(R.id.rGROUP);
            RatingBar rtg=findViewById(R.id.ratingBar);

            denumire.setText(termi.getNume());
            spin.setSelection(termi.getNumar()-1);
            detalii.setText(termi.getDetalii());
            if(termi.isCurat()==true)
            {
                rdg.check(R.id.radio_curat);
            }
            else
                rdg.check(R.id.radio_murdar);
            rtg.setRating(termi.getGrade()-30);
        }

        Button btnTermos = findViewById(R.id.button2);
        btnTermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText denumire = findViewById(R.id.editTextText2);
                Spinner spin=findViewById(R.id.spinner2);
                EditText detalii = findViewById(R.id.editTextText3);
                RadioGroup rdg=findViewById(R.id.rGROUP);
                RatingBar rtg=findViewById(R.id.ratingBar);
                int selectedID=rdg.getCheckedRadioButtonId();
                CheckBox chk = findViewById(R.id.checkboxFireBase);

                int alesSpin= Integer.parseInt(spin.getSelectedItem().toString());
                String denumireString=denumire.getText().toString();
                String detaliiString=detalii.getText().toString();
                boolean curat=false;
                if(selectedID==R.id.radio_curat)
                {
                    curat=true;
                }
                float grade=rtg.getRating()+30;

                Termos t1 = new Termos(denumireString,alesSpin,detaliiString,curat,grade);
                salveazaFisier(t1);

                Executors.newSingleThreadExecutor().execute(() -> {
                    bd.termosDao().insert(t1);
                });

                Log.d("#obiect",t1.toString());

                if(chk.isChecked()) {
                    FirebaseDatabase databaseFire = FirebaseDatabase.getInstance("https://seminar13-934c8-default-rtdb.europe-west1.firebasedatabase.app/");
                    DatabaseReference myRef = databaseFire.getReference("termosuri");
                    DatabaseReference child = myRef.child(t1.getKey());
                    child.setValue(t1);
                }

                Intent intent = new Intent();
                intent.putExtra("termos",t1);
                setResult(RESULT_OK,intent);
                finish();

            }
        });


    }
    private void salveazaFisier(Termos t)
    {
        File file=new File(getFilesDir(),"termosuri.txt");
        String termosData = t.toString()+"\n";

        try(FileOutputStream fos = new FileOutputStream(file,true))
        {
            fos.write(termosData.getBytes(StandardCharsets.UTF_8));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}