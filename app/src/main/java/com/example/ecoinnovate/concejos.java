package com.example.ecoinnovate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class concejos extends AppCompatActivity {

    private ImageView atras;
    private TextView textViewTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concejos);

        atras= findViewById(R.id.btn_atras);
        textViewTips= findViewById(R.id.textViewTips);

        LoadDailyTips();

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(concejos.this, opciones.class);
                startActivity(intent);
            }
        });
    }

    private void LoadDailyTips(){
        SharedPreferences preferences = getSharedPreferences("savedDate", MODE_PRIVATE);
        String dailyTips = preferences.getString("CurrentTip", "Consejo no Disponible");
        textViewTips.setText(dailyTips);
    }
}