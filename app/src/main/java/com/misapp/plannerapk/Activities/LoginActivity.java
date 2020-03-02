package com.misapp.plannerapk.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.misapp.plannerapk.R;
import com.misapp.plannerapk.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private TextInputEditText input_email;
    private TextInputEditText input_pass;
    private CheckBox cb_remember_me;
    private Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // enlazamos los datos con las varibles de la vista .xml
        bindUI();

        // si el checkbox esta activo y tiene datos el sharedpreferences


        // crea el sharedpreferences en modo privado
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        // verfica si hay datos en el sharedpreference
        setCredentialsIfExits();

        // boton de login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString();
                String password = input_pass.getText().toString();


                // logica si esto regresa verdadero
                if (login(email, password)) {
                    goToMain();
                    saveOnPreferences(email, password);
                    if (!cb_remember_me.isChecked()) {
                        prefs.edit().clear().apply();
                    }
                }
            }
        });
    }


    // login con las credenciales
    private boolean login(String email, String password) {
        if (!isValiEmail(email)) {
            Toast.makeText(this,
                    "Email is not valid, please try again",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isValiPassword(password)) {
            Toast.makeText(this,
                    "Password is not valid, 8 characters or more, please try again",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    // enlazamos los datos con las varibles de la vista .xml
    private void bindUI() {
        input_email = findViewById(R.id.input_email);
        input_pass = findViewById(R.id.input_pass);
        cb_remember_me = findViewById(R.id.cb_remember_me);
        btn_login = findViewById(R.id.btn_login);
    }

    // validaciones
    private boolean isValiEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValiPassword(String passwrod) {
        return passwrod.length() > 8;
    }


    // cambiar de activity
    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        // para no virar hacia el login
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // guardar las valores en preferencias
    private void saveOnPreferences(String email, String password) {
        if (cb_remember_me.isChecked()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();
        }
    }

    /*
     * si esta logueado y guardaste las credenciales
     * */

    // setCredentialsIfExits
    private void setCredentialsIfExits() {
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserMailPassword(prefs);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            input_email.setText(email);
            input_pass.setText(password);
            cb_remember_me.setChecked(true); // si hay valores en el sharedpreferences cambia a estado check
        }
    }
}
