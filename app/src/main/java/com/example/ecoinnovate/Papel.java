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

public class Papel extends AppCompatActivity {
    private EditText editTextRecoPapel, EditTextMesPapel, EditTextPrecioPapel;
    private Button btnGuardar;
    private ImageView btn_atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_papel);

        editTextRecoPapel = findViewById(R.id.RecoleccionPapel);EditTextMesPapel = findViewById(R.id.MesPapel);
        EditTextPrecioPapel = findViewById(R.id.PrecioPapel);
        btnGuardar = findViewById(R.id.btn_guardarPapel);
        btn_atras = findViewById(R.id.btn_atrasPapel);

        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Papel.this, categorias.class);
                startActivity(intent);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatosPapel();
            }
        });
    }

    private void guardarDatosPapel() {
        String recoleccion = editTextRecoPapel.getText().toString();
        String mes = EditTextMesPapel.getText().toString();
        String ganancias = EditTextPrecioPapel.getText().toString();

        SharedPreferences preferences = getSharedPreferences("DatosPapel", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Verificar si ya existe un dato conel índice actual
        int index = preferences.getInt("indexPapel", 0);
        while (preferences.contains("recoleccionPapel" + index)) {
            index++;
        }

        // Guardar los datos con el nuevo índice
        editor.putString("recoleccionPapel" + index, recoleccion);
        editor.putString("mesPapel" + index, mes);
        editor.putString("gananciasPapel" + index, ganancias);

        editor.putInt("indexPapel", index + 1);
        editor.apply();

        Toast.makeText(this, "Datos de Papel Guardados", Toast.LENGTH_SHORT).show();
        // Limpiar los campos de texto después de guardar
        editTextRecoPapel.setText("");
        EditTextMesPapel.setText("");
        EditTextPrecioPapel.setText("");
    }
}