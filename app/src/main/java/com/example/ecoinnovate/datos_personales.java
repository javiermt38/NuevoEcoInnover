package com.example.ecoinnovate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class datos_personales extends AppCompatActivity {
    private EditText editTextNombre, editTextApellidos, editTextDocumento, editTextTelefono, editTextDireccion, editTextEmail;
    private Button btnActualizar;
    private ImageView btnAtras_datosPersonales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datos_personales);

        editTextNombre = findViewById(R.id.EditNombre);
        editTextApellidos = findViewById(R.id.EditApellidos);
        editTextDocumento = findViewById(R.id.EditDocumento);
        editTextTelefono = findViewById(R.id.EditTelefono);
        editTextDireccion = findViewById(R.id.EditDireccion);
        editTextEmail = findViewById(R.id.EditEmail);
        btnActualizar = findViewById(R.id.GuardarCambios);
        btnAtras_datosPersonales = findViewById(R.id.btnAtrasDatosPersonales);
        btnActualizar.setOnClickListener(v -> PersonalData());

        btnAtras_datosPersonales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(datos_personales.this,opciones.class);
                startActivity(intet);
            }
        });
    }
    private void PersonalData() {
        String nombre = editTextNombre.getText().toString();
        String apellidos = editTextApellidos.getText().toString();
        String documento = editTextDocumento.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String direccion = editTextDireccion.getText().toString();
        String email = editTextEmail.getText().toString();

        // Validar si algún campo ha sido modificado
        if (algunCampoModificado(nombre, apellidos, documento, telefono, direccion, email)) {
            // Guardar Datos En SharedPreferences
            SharedPreferences preferences = getSharedPreferences("DataP", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            int index= preferences.getInt("index", 0);
            editor.putString("nombre" + index, nombre);
            editor.putString("apellidos" + index, apellidos);
            editor.putString("documento" + index, documento);
            editor.putString("telefono" + index, telefono);
            editor.putString("direccion" + index, direccion);
            editor.putString("email" + index, email);

            editor.putInt("index", index + 1);
            editor.apply();
            Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Debes elegir un campo para actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean algunCampoModificado(String nombre, String apellidos, String documento, String telefono, String direccion, String email) {

        return !nombre.isEmpty() || !apellidos.isEmpty() || !documento.isEmpty() || !telefono.isEmpty() || !direccion.isEmpty() || !email.isEmpty();
    }
}