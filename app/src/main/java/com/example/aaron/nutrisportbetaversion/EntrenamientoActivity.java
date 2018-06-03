package com.example.aaron.nutrisportbetaversion;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class EntrenamientoActivity extends AppCompatActivity {
    GridView gridView;
    String [] values = {"Crossfit", "Volumen", "Fuerza", "Resistencia" , "Descargas", "Añadir Rutina", "Seguimiento", "Orientación Deportiva", "Novedades Deportiva"};
    int [] images = {R.drawable.ic_action_crossfit, R.drawable.ic_action_volumn, R.drawable.ic_action_back, R.drawable.ic_action_crossfit, R.drawable.ic_action_volumn, R.drawable.ic_action_back, R.drawable.ic_action_crossfit, R.drawable.ic_action_volumn, R.drawable.ic_action_back};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento);
        gridView = findViewById(R.id.grid_view);
        final GridAdapter gridAdapter = new GridAdapter(EntrenamientoActivity.this, values, images);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.BLUE);
                    EntrenamientoActivity.this.recreate();
                    Intent intent = new Intent(EntrenamientoActivity.this,CrossFitActivity.class);
                    startActivity(intent);
                }else if(i==1){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.RED);
                    EntrenamientoActivity.this.recreate();
                    Intent intent = new Intent(EntrenamientoActivity.this, VolumenActivity.class);
                    startActivity(intent);
                }else if(i==2){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.GREEN);
                    EntrenamientoActivity.this.recreate();
                    Intent intent = new Intent(EntrenamientoActivity.this, FuerzaActivity.class);
                    startActivity(intent);
                }else if(i==3){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.YELLOW);
                    EntrenamientoActivity.this.recreate();
                    Intent intent = new Intent(EntrenamientoActivity.this, ResistenciaActivity.class);
                    startActivity(intent);
                }else if(i==4){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.CYAN);
                    EntrenamientoActivity.this.recreate();
                    Intent intent = new Intent(EntrenamientoActivity.this, DescargasActivity.class);
                    startActivity(intent);
                }else if(i==5){
                    EntrenamientoActivity.this.recreate();
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.LTGRAY);
                    Intent intent = new Intent(EntrenamientoActivity.this, AgregarEntrenamientoActivity.class);
                    startActivity(intent);
                }else if(i==6){
                    EntrenamientoActivity.this.recreate();
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.MAGENTA);
                    Intent intent = new Intent(EntrenamientoActivity.this, SeguimientoDeportivoActivity.class);
                    startActivity(intent);
                }else if(i==7){
                    EntrenamientoActivity.this.recreate();
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.MAGENTA);
                    Intent intent = new Intent(EntrenamientoActivity.this, OrientacionDeportivaActivity.class);
                    startActivity(intent);
                }else if(i==8){
                    EntrenamientoActivity.this.recreate();
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.MAGENTA);
                    Intent intent = new Intent(EntrenamientoActivity.this, NovedadesDeportivasActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
