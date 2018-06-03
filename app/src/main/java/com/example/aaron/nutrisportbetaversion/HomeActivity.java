package com.example.aaron.nutrisportbetaversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button btnEntrenamiento, btnNutricion;
    ImageButton btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnEntrenamiento = findViewById(R.id.btnEntrenamiento);
        btnNutricion = findViewById(R.id.btnNutricion);
        btnExit = findViewById(R.id.imageButton);

        btnEntrenamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EntrenamientoActivity.class);
                startActivity(intent);
            }
        });

        btnNutricion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NutricionActivity.class);
                startActivity(intent);

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, getString(R.string.home_sesion_close), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
