// UserLoginActivity.java
package com.example.tiendamusica;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserLoginActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        db = new DatabaseHelper(this);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        Button btnLoginUser = findViewById(R.id.btnLoginUser);
        Button btnEditCredentials = findViewById(R.id.btnEditCredentials);
        Button btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        Button btnRegisterUser = findViewById(R.id.btnRegisterUser);

        // Botón para iniciar sesión
        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                Cursor cursor = db.getUser(username, password);
                if (cursor != null && cursor.moveToFirst()) {
                    Toast.makeText(UserLoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(UserLoginActivity.this, UserDashboard.class);
                    startActivity(intent); // Inicia la actividad de destino
                    finish();
                    // Redirigir a la pantalla de usuario o mostrar opciones adicionales
                } else {
                    Toast.makeText(UserLoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón para crear una nueva cuenta
        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir al RegisterActivity para registrar el nuevo usuario
                Intent intent = new Intent(UserLoginActivity.this, RegisterActivity.class);
                startActivity(intent); // Iniciar la actividad de registro
            }
        });


        // Botón para editar credenciales
        btnEditCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir al EditCredentialsActivity
                Intent intent = new Intent(UserLoginActivity.this, EditCredentialsActivity.class);
                startActivity(intent); // Iniciar la actividad para editar credenciales
            }
        });



        // Botón para eliminar la cuenta
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir al DeleteAccountActivity
                Intent intent = new Intent(UserLoginActivity.this, DeleteAccountActivity.class);
                startActivity(intent); // Iniciar la actividad de eliminación de cuenta
            }
        });


    }
}
