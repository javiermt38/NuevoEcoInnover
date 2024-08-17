package com.example.ecoinnovate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class estadisticas extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button buttonClear;
    private ImageView buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        tableLayout = findViewById(R.id.myTableLayout);
        buttonClear = findViewById(R.id.buttonClear);
        buttonBack = findViewById(R.id.btn_atrasEstadisticas);

        loadData();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y vuelve a la anterior
            }
        });
    }

    private void loadData() {
        cargarDatos("DatosHierro", "Hierro");
        cargarDatos("DatosVidrio", "Vidrio");
        cargarDatos("DatosPapel", "Papel");
    }

    private void cargarDatos(String sharedPrefsName, String tipoMaterial) {
        SharedPreferences preferences = getSharedPreferences(sharedPrefsName, MODE_PRIVATE);
        int index = preferences.getInt("index" + tipoMaterial, 0);

        for (int i = 0; i < index; i++) {
            String recoleccion = preferences.getString("recoleccion"+ tipoMaterial + i, "");
            String mes = preferences.getString("mes" + tipoMaterial + i, "");
            String ganancias = preferences.getString("ganancias" + tipoMaterial + i, "");

            TableRow tableRow = new TableRow(this);

            TextView textViewMes = new TextView(this);
            textViewMes.setText(mes);
            textViewMes.setBackgroundResource(R.color.white);
            tableRow.addView(textViewMes);

            TextView textViewMaterial = new TextView(this);
            textViewMaterial.setText(tipoMaterial);
            textViewMaterial.setBackgroundResource(R.color.white);
            tableRow.addView(textViewMaterial);

            TextView textViewRecoleccion = new TextView(this);
            textViewRecoleccion.setText(recoleccion);
            textViewRecoleccion.setBackgroundResource(R.color.white);
            tableRow.addView(textViewRecoleccion);

            TextView textViewGanancias = new TextView(this);
            textViewGanancias.setText(ganancias);
            textViewGanancias.setBackgroundResource(R.color.white);
            tableRow.addView(textViewGanancias);

            tableLayout.addView(tableRow);
        }
    }

    private void clearData() {
        clearDataForMaterial("DatosHierro", "Hierro");
        clearDataForMaterial("DatosVidrio", "Vidrio");
        clearDataForMaterial("DatosPapel", "Papel");

        tableLayout.removeAllViews();
        Toast.makeText(this, "Datos borrados", Toast.LENGTH_SHORT).show();
    }

    private void clearDataForMaterial(String sharedPrefsName, String tipoMaterial) {
        SharedPreferences preferences = getSharedPreferences(sharedPrefsName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}