package com.example.ecoinnovate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Hierro extends AppCompatActivity {
    private EditText editTextRecoleccion, editTextMesRecoleccion, editTextGanancias;
    private Button btnGuardar;
    private ImageView btnatras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hierro);

        editTextRecoleccion = findViewById(R.id.RecoleccionPapel);
        editTextMesRecoleccion = findViewById(R.id.MesPapel);
        editTextGanancias = findViewById(R.id.PrecioPapel);
        btnGuardar = findViewById(R.id.guardarPapel);
        btnatras = findViewById(R.id.btn_atrasPapel);

        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hierro.this, categorias.class);
                startActivity(intent);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatosHierro();
            }
        });
    }

    private void guardarDatosHierro() {
        String recoleccion = editTextRecoleccion.getText().toString();
        String mes = editTextMesRecoleccion.getText().toString();String ganancias = editTextGanancias.getText().toString();

        SharedPreferences preferences = getSharedPreferences("DatosHierro", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Verificar si ya existe un dato con el índice actual
        int index = preferences.getInt("indexHierro", 0);
        while (preferences.contains("recoleccionHierro" + index)) {
            index++;
        }

        // Guardar los datos con el nuevo índice
        editor.putString("recoleccionHierro" + index, recoleccion);
        editor.putString("mesHierro" + index, mes);
        editor.putString("gananciasHierro" + index, ganancias);

        editor.putInt("indexHierro", index + 1);
        editor.apply();

        Toast.makeText(this, "Datos de Hierro Guardados", Toast.LENGTH_SHORT).show();
        // Limpiar los campos de texto después de guardar
        editTextRecoleccion.setText("");
        editTextMesRecoleccion.setText("");
        editTextGanancias.setText("");
    }
}