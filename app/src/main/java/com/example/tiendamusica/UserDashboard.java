// WelcomeActivity.java
package com.example.tiendamusica;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class UserDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Bienvenido a la tienda de Metal");
    }
}
