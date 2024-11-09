// RegisterActivity.java
package com.example.tiendamusica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText edtNewUsername, edtNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        edtNewUsername = findViewById(R.id.edtNewUsername);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtNewUsername.getText().toString();
                String password = edtNewPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else if (db.addUser(username, password)) {
                    // Si se agregó correctamente, muestra el mensaje y redirige al login
                    Toast.makeText(RegisterActivity.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();

                    // Redirigir al LoginActivity después de registrar la cuenta
                    Intent intent = new Intent(RegisterActivity.this, UserLoginActivity.class);
                    startActivity(intent);
                    finish(); // Finalizar RegisterActivity para no regresar a ella
                } else {
                    Toast.makeText(RegisterActivity.this, "Error al crear la cuenta. El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
