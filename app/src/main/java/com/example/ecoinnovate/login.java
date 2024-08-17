package com.example.ecoinnovate;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class login extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button ingresar, registrarse;
    private TextView textViewTexto;
    private UserManager userManager;

    private String[] Tips ={
            "Separa el papel: Reúne papel, cartón, revistas y periódicos en un contenedor exclusivo.",
            "Aplasta las cajas de cartón: Ocupa menos espacio y facilita el transporte.",
            "Evita contaminar el papel: Retira grapas, clips y etiquetas antes de reciclar.",
            "Enjuaga bien los envases de vidrio: Elimina restos de comida para facilitar el reciclaje.",
            "Separa el vidrio por color: Algunos centros de reciclaje lo solicitan para optimizar el proceso.",
            "No tires tapas metálicas en el contenedor de vidrio: Recíclalas en el contenedor de hierro.",
            "Aplasta las latas de aluminio: Ocupa menos espacio y facilita el transporte.",
            "Limpia las latas antes de reciclarlas: Retira etiquetas y enjuágalas.",
            "Recicla los envases de hierro: Latas de conserva, aerosoles (vacíos) y otros objetos de hierro pueden reciclarse.",
            "Evita mezclar metales: Separa el hierro de otros metales como el aluminio.",
            "No tires objetos grandes de hierro: Llévalos a un punto limpio o chatarrería.",
            "Informa a tus vecinos: Difunde la importancia de reciclar correctamente estos materiales."
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ingresar = findViewById(R.id.btn_ingresar);
        registrarse = findViewById(R.id.btn_registrarse);
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.pass);
        textViewTexto = findViewById(R.id.rec_pass);

        userManager = new UserManager(this);

        textViewTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "Escribe Tu Correo En Usuario  Para  Enviar un correo Para Recuperar Tu Contraseña", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        SharedPreferences preferences = getSharedPreferences("savedDate",MODE_PRIVATE);
        String saveDate = preferences.getString("savedDate","");
        SharedPreferences.Editor editor = preferences.edit();

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, registro.class);
                startActivity(intent);
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                boolean isInitialized = preferences.getBoolean("Initialized", false);

                if (!isInitialized){

                    // Guarda todos los consejos en SharedPreferences
                    for (int i = 0; i < Tips.length; i++){
                        editor.putString("tip" + i, Tips[i]);
                    }
                    editor.putBoolean("Initialized", true);
                    editor.apply();
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","col"));
                String currentDate = sdf.format(new Date());

                if (!currentDate.equals(saveDate)){
                    Random random = new Random();
                    int randomIndex = random.nextInt(Tips.length);
                    String newTip = Tips[randomIndex];

                    // Guarda el consejo diario y la fecha actual
                    editor.putString("CurrentTip", newTip);
                    editor.putString("savedDate", currentDate);
                    editor.apply();
                }

                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Toast.makeText(login.this, "Completar los campos por favor", Toast.LENGTH_SHORT).show();
                    return; // Salir del método si hay campos vacíos
                }

                if (userManager.loginUser(email, password)) {Toast.makeText(login.this, "Login Exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, concejos.class);
                    startActivity(intent); // Iniciar la actividad concejos
                    finish(); // Cerrar la actividad de login
                } else {
                    String registeredEmail = userManager.getRegisteredEmail();
                    if (registeredEmail == null || !registeredEmail.equals(email)) {
                        Toast.makeText(login.this, "Correo electrónico incorrecto", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(login.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}