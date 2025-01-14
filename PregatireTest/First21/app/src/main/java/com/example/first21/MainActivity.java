package com.example.first21;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private List<HusaTelefon> husaTelefonList;
    private BazaDeDateHuse bd;

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



        Button btn1 = findViewById(R.id.actvitateCreareHusa);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(),CreareHusaTelefon.class);
                startActivityForResult(it,201);
            }
        });
        Button btn2 = findViewById(R.id.imgButton);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Online.class);
                startActivity(it);
            }
        });

        Button btn3 = findViewById(R.id.jsonButton);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), JSON.class);
                startActivity(it);
            }
        });
        bd=BazaDeDateHuse.getInstance(getApplicationContext());
        ListView lv = findViewById(R.id.Radulescu_Theodor_list_view);
        Handler handler = new Handler(Looper.myLooper());
        Executors.newSingleThreadExecutor().execute(()->{
            husaTelefonList=bd.husaDAO().getAll();
            handler.post(()->
            {
                ArrayAdapter<HusaTelefon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,husaTelefonList);
                lv.setAdapter(adapter);
            });

        });
        Button btn4 = findViewById(R.id.SHAREDBUTTON);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AfisareSharedPreferences.class);
                startActivity(it);
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==201&&resultCode==RESULT_OK){
            if(data!=null && data.hasExtra("huse"))
            {
                if(husaTelefonList==null)
                    husaTelefonList=new ArrayList<>();
            }
            HusaTelefon husaNoua = data.getParcelableExtra("huse");
            if(husaNoua!=null)
                husaTelefonList.add(husaNoua);
        }
        ListView lv = findViewById(R.id.Radulescu_Theodor_list_view);
        ArrayAdapter<HusaTelefon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,husaTelefonList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HusaTelefon hhh=husaTelefonList.get(position);
                SharedPreferences sd = getSharedPreferences("huseFavorite",MODE_PRIVATE);
                SharedPreferences.Editor edit =sd.edit();
                edit.putString(hhh.getMaterial(),hhh.toString());
                edit.commit();

                Toast.makeText(MainActivity.this, husaTelefonList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}