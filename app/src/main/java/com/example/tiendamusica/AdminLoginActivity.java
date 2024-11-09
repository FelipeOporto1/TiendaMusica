// AdminLoginActivity.java
package com.example.tiendamusica;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        db = new DatabaseHelper(this);
        edtUsername = findViewById(R.id.edtAdminUsername);
        edtPassword = findViewById(R.id.edtAdminPassword);
        Button btnLogin = findViewById(R.id.btnAdminLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if (username.equals("admin") && password.equals("admin123")) {
                    Toast.makeText(AdminLoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    // Redirigir a la pantalla de administrador
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
