package com.example.ecoinnovate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class categorias extends AppCompatActivity {

    private Button btnPapel, btnVidrio, btnHierro;
    ImageView atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorias);

        btnPapel = findViewById(R.id.btn_papel);
        btnVidrio = findViewById(R.id.btn_vidrio);
        btnHierro = findViewById(R.id.btn_hierro);

        btnPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categorias.this, Papel.class);
                startActivity(intent);
            }
        });

        btnVidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categorias.this, Vidrio.class);
                startActivity(intent);
            }
        });

        btnHierro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categorias.this, Hierro.class);
                startActivity(intent);
            }
        });

        atras=(ImageView) findViewById(R.id.btn_atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categorias.this, opciones.class);
                startActivity(intent);
            }
        });

    }
}