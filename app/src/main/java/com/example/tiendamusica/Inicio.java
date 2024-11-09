// Inicio.java
package com.example.tiendamusica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Referencias a los botones
        Button btnAdmin = findViewById(R.id.btnAdmin);
        Button btnUser = findViewById(R.id.btnUser);

        // Configura el listener para el botón de administrador
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        // Configura el listener para el botón de usuario
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
