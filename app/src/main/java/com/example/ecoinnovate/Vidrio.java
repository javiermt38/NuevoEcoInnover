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

public class Vidrio extends AppCompatActivity {
    private EditText editTextRecoleccion, editTextMesRecoleccion, editTextGanancias;
    private ImageView btn_atras;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vidrio);

        editTextRecoleccion = findViewById(R.id.RecoleccioVidrio);
        editTextMesRecoleccion = findViewById(R.id.MesVidrio);
        editTextGanancias = findViewById(R.id.PrecioVidrio);
        btn_atras = findViewById(R.id.btn_atrasVidrio);
        btnGuardar = findViewById(R.id.guardarVidrio);

        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vidrio.this, categorias.class);
                startActivity(intent);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatosVidrio();
            }
        });
    }

    private void guardarDatosVidrio() {
        String recoleccion = editTextRecoleccion.getText().toString();
        String mes = editTextMesRecoleccion.getText().toString();
        String ganancias = editTextGanancias.getText().toString();

        SharedPreferences preferences = getSharedPreferences("DatosVidrio", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Verificar si ya existe un dato con el índice actual
        int index = preferences.getInt("indexVidrio", 0);
        while (preferences.contains("recoleccionVidrio" + index)) {
            index++;
        }

        // Guardar los datos con el nuevo índice
        editor.putString("recoleccionVidrio" + index, recoleccion);
        editor.putString("mesVidrio" + index, mes);
        editor.putString("gananciasVidrio" + index, ganancias);

        editor.putInt("indexVidrio", index + 1);
        editor.apply();

        Toast.makeText(this, "Datos de Vidrio Guardados", Toast.LENGTH_SHORT).show();
        // Limpiar los campos de texto después de guardar
        editTextRecoleccion.setText("");
        editTextMesRecoleccion.setText("");
        editTextGanancias.setText("");
    }
}