package com.example.ecoinnovate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class    registro extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button btnCrearCuenta;
    private UserManager userManager;
    private CheckBox checkBoxterms;
    private ImageView imageViewAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        editTextEmail = findViewById(R.id.IngresarEmail);
        editTextPassword = findViewById(R.id.IngresarPassword);
        btnCrearCuenta = findViewById(R.id.CrearCuenta);
        checkBoxterms = findViewById(R.id.checkBoxDatos);
        imageViewAtras = findViewById(R.id.btn_atrasRegistro);

        userManager = new UserManager(this);

        imageViewAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registro.this, opciones.class);
                startActivity(intent);
                finish();
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(registro.this, "Ingrese un email", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(registro.this, "Ingrese un email valido", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(registro.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                } else if (!checkBoxterms.isChecked()) {
                    Toast.makeText(registro.this, "Acepte los terminos y condiciones", Toast.LENGTH_SHORT).show();
                } else {
                    registrarUsuario(email, password);
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void registrarUsuario(String email, String password) {
        userManager.RegistreUser(email, password);
        Toast.makeText(registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        finish();
    }
}