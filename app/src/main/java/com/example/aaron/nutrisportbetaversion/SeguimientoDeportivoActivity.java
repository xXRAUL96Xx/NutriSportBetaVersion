package com.example.aaron.nutrisportbetaversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aaron.nutrisportbetaversion.Objetos.AdapterEntrenamiento;
import com.example.aaron.nutrisportbetaversion.Objetos.Entrenamiento;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SeguimientoDeportivoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Entrenamiento> entrenamientos;
    AdapterEntrenamiento adapterEntrenamiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_deportivo);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerEntrenamiento);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        entrenamientos = new ArrayList<>();
        adapterEntrenamiento = new AdapterEntrenamiento(entrenamientos);
        recyclerView.setAdapter(adapterEntrenamiento);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("entrenamientos");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Entrenamiento entrenamiento = dataSnapshot.getValue(Entrenamiento.class);
                entrenamientos.add(entrenamiento);
                adapterEntrenamiento = new AdapterEntrenamiento(entrenamientos);
                recyclerView.setAdapter(adapterEntrenamiento);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}