package com.example.aaron.nutrisportbetaversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aaron.nutrisportbetaversion.Objetos.Entrenamiento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarEntrenamientoActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    EditText textDia, textTipoEntrenamiento, textDuracion, textObservaciones;
    Button btn_save_training;
    private Entrenamiento entrenamiento = new Entrenamiento();
    private boolean open;
    String uid;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_entrenamiento);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        textDia = findViewById(R.id.inputTextDia);
        textTipoEntrenamiento = findViewById(R.id.inputTextTipo);
        textDuracion = findViewById(R.id.inputTextDuration);
        textObservaciones = findViewById(R.id.inputTextObservaciones);
        btn_save_training = findViewById(R.id.btn_addTraining);
        open = true;
        if(user != null){
            uid = user.getUid();
        }
        btn_save_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load_dataTraining(view);
            }
        });
    }
    public void load_dataTraining(View view){
        if(open){
            try{
                if(textDia.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarEntrenamiento_data), Toast.LENGTH_SHORT).show();
                }else if(textTipoEntrenamiento.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarEntrenamiento_data), Toast.LENGTH_SHORT).show();
                }else if(textDuracion.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarEntrenamiento_data), Toast.LENGTH_SHORT).show();
                }else if(textObservaciones.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarEntrenamiento_data), Toast.LENGTH_SHORT).show();
                }else{
                    String dia = textDia.getText().toString().trim();
                    String tipo = textTipoEntrenamiento.getText().toString().trim();
                    int duracion = Integer.parseInt(textDuracion.getText().toString().trim());
                    String observaciones = textObservaciones.getText().toString().trim();
                    entrenamiento.setDia(dia);
                    entrenamiento.setTipo_entrenamiento(tipo);
                    entrenamiento.setTime_duration(duracion);
                    entrenamiento.setObservaciones(observaciones);
                    String key = databaseReference.child("usuario").push().getKey();
                    entrenamiento.setKey(key);
                    entrenamiento.setUid(uid);
                    entrenamiento.setCreadoBy(user.getEmail());
                    databaseReference.child("entrenamientos").child(key).setValue(entrenamiento);
                    Toast.makeText(this, getString(R.string.agregarEntrenamiento_ok), Toast.LENGTH_SHORT).show();
                    Intent intentTraining = new Intent(AgregarEntrenamientoActivity.this, EntrenamientoActivity.class);
                    startActivity(intentTraining);
                }
            }catch (NumberFormatException e){
                Toast.makeText(this, getString(R.string.agregarEntrenamiento_no_data_validate), Toast.LENGTH_SHORT).show();
            }
        }
    }
}