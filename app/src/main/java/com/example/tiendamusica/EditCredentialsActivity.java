// EditCredentialsActivity.java
package com.example.tiendamusica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditCredentialsActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText edtCurrentUsername, edtCurrentPassword, edtNewUsername, edtNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_credentials);

        db = new DatabaseHelper(this);
        edtCurrentUsername = findViewById(R.id.edtCurrentUsername);
        edtCurrentPassword = findViewById(R.id.edtCurrentPassword);
        edtNewUsername = findViewById(R.id.edtNewUsername);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        Button btnUpdateCredentials = findViewById(R.id.btnUpdateCredentials);

        btnUpdateCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentUsername = edtCurrentUsername.getText().toString().trim();
                String currentPassword = edtCurrentPassword.getText().toString().trim();
                String newUsername = edtNewUsername.getText().toString().trim();
                String newPassword = edtNewPassword.getText().toString().trim();

                // Validación de campos vacíos
                if (currentUsername.isEmpty() || currentPassword.isEmpty() || newUsername.isEmpty() || newPassword.isEmpty()) {
                    Toast.makeText(EditCredentialsActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificación de credenciales actuales
                if (db.verifyUser(currentUsername, currentPassword)) {
                    // Obtener el ID del usuario usando el nombre de usuario
                    int userId = db.getUserId(currentUsername);

                    if (userId != -1) {
                        // Actualización de credenciales usando el ID del usuario
                        if (db.updateUser(userId, newUsername, newPassword)) {
                            Toast.makeText(EditCredentialsActivity.this, "Credenciales actualizadas", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(EditCredentialsActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditCredentialsActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditCredentialsActivity.this, "Credenciales actuales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
