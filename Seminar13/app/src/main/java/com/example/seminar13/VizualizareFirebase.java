package com.example.seminar13;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class VizualizareFirebase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizare_firebase);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://seminar13-934c8-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myref = firebaseDatabase.getReference("termosuri");



        Log.d("#firebase", );
//        List<Termos> listaTermosuri = (List<Termos>)  myref.get();

//        Log.d("#firebase", listaTermosuri.toString());

    }




}