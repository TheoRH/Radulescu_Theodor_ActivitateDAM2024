package com.example.seminar11;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Termos>termosList=new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.button)
        {Intent it = new Intent(getApplicationContext(), ActivitateaTermos.class);
                    startActivityForResult(it,200);
                }
        if(id==R.id.button3)
        {
            Intent it= new Intent(getApplicationContext(), ActivityLista.class);
            it.putParcelableArrayListExtra("termosList",termosList);
            startActivity(it);
        }
        if(id==R.id.button4)
        {
            Intent it= new Intent(getApplicationContext(), VizualizareImaginiiTermos.class);
            startActivity(it);
        }
        if(id==R.id.button5)
        {
            Intent it = new Intent(getApplicationContext(), JsonAse.class);
            startActivity(it);
        }

        if(id==R.id.button6)
        {
            Intent it = new Intent(getApplicationContext(), AccuWeather.class);
            startActivity(it);
        }
        return  true;

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

        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);


        Button btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ActivitateaTermos.class);
                startActivityForResult(it,200);
            }
        });

        Button btn2=findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(getApplicationContext(), ActivityLista.class);
                it.putParcelableArrayListExtra("termosList",termosList);
                startActivity(it);
            }
        });

        Button btn3=findViewById(R.id.button4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(getApplicationContext(), VizualizareImaginiiTermos.class);
                startActivity(it);
            }
        });

        Button btn4 = findViewById(R.id.button5);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), JsonAse.class);
                startActivity(it);
            }
        });

        Button btn5 = findViewById(R.id.button8);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),VizualizareSharedPreferences.class);
                startActivity(it);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == RESULT_OK && data != null)
        {
            Termos t = data.getParcelableExtra("termos");
            if(t!=null)
            {
                termosList.add(t);
                Toast.makeText(this, "Obiectul a fost adauta:"+ t.toString(), Toast.LENGTH_SHORT).show();

            }

        }
    }
}