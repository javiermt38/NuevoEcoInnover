package com.example.ecoinnovate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class opciones extends AppCompatActivity {


    private Button categorias, estadisticas,concejos,btnActualizarDatos,cerrarSesion;
    private TextView textViewdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);

        categorias= findViewById(R.id.btn_catagorias);
        estadisticas= findViewById(R.id.btn_estadisticas);
        concejos= findViewById(R.id.btn_concejos);
        btnActualizarDatos = findViewById(R.id.Btn_actualizar_datos);
        cerrarSesion = findViewById(R.id.btnCerrarSesion);
        textViewdate = findViewById(R.id.date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","col"));
        //almacena la cadena  de la fecha formayeada
        String currenetDate = sdf.format(new Date());
        textViewdate.setText(currenetDate);

        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opciones.this, categorias.class);
                startActivity(intent);
            }
        });
        estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opciones.this, estadisticas.class);
                startActivity(intent);
            }
        });
        concejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opciones.this, concejos.class);
                startActivity(intent);
            }
        });
        btnActualizarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opciones.this, datos_personales.class);
                startActivity(intent);
                finish();
            }
        });
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(opciones.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}