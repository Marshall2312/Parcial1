package com.example.parcial1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    private TextView textViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        textViewWelcome = findViewById(R.id.textViewSuccess);
        textViewWelcome.setText("¡Ingreso Exitoso!");
    }
}