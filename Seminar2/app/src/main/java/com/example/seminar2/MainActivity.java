package com.example.seminar2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Log.wtf("#activitate","S-a apelat onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("#activitate","S-a apelat onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("#activitate","S-a apelat onResume");

        Toast.makeText(this, R.string.mesaj,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("#activitate","S-a apelat onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("#activitate","S-a apelat onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("#activitate","S-a apelat onDestroy");
    }

}