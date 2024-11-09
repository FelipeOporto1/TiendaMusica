// DeleteAccountActivity.java
package com.example.tiendamusica;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteAccountActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText edtUsernameToDelete, edtPasswordToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        db = new DatabaseHelper(this);
        edtUsernameToDelete = findViewById(R.id.edtUsernameToDelete);
        edtPasswordToDelete = findViewById(R.id.edtPasswordToDelete);
        Button btnDeleteAccount = findViewById(R.id.btnDeleteAccount);

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsernameToDelete.getText().toString().trim();
                String password = edtPasswordToDelete.getText().toString().trim();

                // Validación de campos vacíos
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(DeleteAccountActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificación de credenciales
                if (db.verifyUser(username, password)) {
                    // Obtener el ID del usuario usando el nombre de usuario
                    int userId = db.getUserId(username);

                    if (userId != -1) {
                        // Mostrar diálogo de confirmación antes de eliminar
                        new AlertDialog.Builder(DeleteAccountActivity.this)
                                .setTitle("Confirmar eliminación")
                                .setMessage("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.")
                                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Eliminación de usuario usando el ID del usuario
                                        if (db.deleteUser(userId)) {
                                            Toast.makeText(DeleteAccountActivity.this, "Cuenta eliminada exitosamente", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(DeleteAccountActivity.this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Cancelar", null)
                                .show();
                    } else {
                        Toast.makeText(DeleteAccountActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DeleteAccountActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
