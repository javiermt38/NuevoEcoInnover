package com.example.ecoinnovate;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {

//crea archivos en el  preferencia compartidas
    private static final String PREF_NAME= "UserPrefs";

    //clave que se usan para recuperar datos
    private  static final String KEY_EMAIL = "email";
    private  static final String KEY_PASSWORD = "password";

// variables a instanciar
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;

    //Constructor
    public UserManager (Context context){
        sharedPreferences =context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        //obtener editor para midicar los datos que se ingresaron por primera ves
        editor = sharedPreferences.edit();
    }

    public void RegistreUser(String email, String password){
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public boolean loginUser(String email, String password){
        String RegisteredEmail = sharedPreferences.getString(KEY_EMAIL,null);
        String RegisteredPassword = sharedPreferences.getString(KEY_PASSWORD,null);
        return email.equals(RegisteredEmail) && password.equals(RegisteredPassword);

    }

    public String getRegisteredEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }
}
