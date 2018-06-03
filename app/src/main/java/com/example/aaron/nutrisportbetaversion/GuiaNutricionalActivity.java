package com.example.aaron.nutrisportbetaversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aaron.nutrisportbetaversion.Objetos.AdapterAlimento;
import com.example.aaron.nutrisportbetaversion.Objetos.Alimento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class GuiaNutricionalActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Alimento> alimentos = new ArrayList<>();
    AdapterAlimento adapterAlimento;
    Query query;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    ChildEventListener childEventListener;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia_nutricional);
        recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getAdapter();
        query = firebaseDatabase.getReference("alimentos").orderByChild("creadoBy").equalTo(user.getEmail());
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Alimento alimento = dataSnapshot.getValue(Alimento.class);
                alimentos.add(alimento);
                adapterAlimento = new AdapterAlimento(alimentos);
                recyclerView.setAdapter(adapterAlimento);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        query.addChildEventListener(childEventListener);
        AdapterAlimento adapterAlimentoSenior = new AdapterAlimento(alimentos);
        recyclerView.setAdapter(adapterAlimentoSenior);
    }
}
