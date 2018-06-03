package com.example.aaron.nutrisportbetaversion;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class NutricionActivity extends AppCompatActivity {
    GridView gridView;
    String [] values = {"Calcular IMC", "Calcular Grasa Corpórea", "Metabolismo Basal", "Agregar Alimento" , "Guía Nutricional", "Orientación Nutricional", "Novedades Nutricionales"};
    int [] images = {R.drawable.ic_action_addfood, R.drawable.ic_action_basal, R.drawable.ic_action_product, R.drawable.ic_action_addfood, R.drawable.ic_action_basal, R.drawable.ic_action_product, R.drawable.ic_action_product};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricion);
        gridView = findViewById(R.id.grid_view);
        final GridAdapter gridAdapter = new GridAdapter(NutricionActivity.this, values, images);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.BLUE);
                    NutricionActivity.this.recreate();
                    Intent intent = new Intent(NutricionActivity.this,CalculoIndiceMasaCorporalActivity.class);
                    startActivity(intent);
                }else if(i==1){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.RED);
                    NutricionActivity.this.recreate();
                    Intent intent = new Intent(NutricionActivity.this, CalcularGrasaCorporalActivity.class);
                    startActivity(intent);
                }else if(i==2){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.GREEN);
                    NutricionActivity.this.recreate();
                    Intent intent = new Intent(NutricionActivity.this, MetabolismoBasalActivity.class);
                    startActivity(intent);
                }else if(i==3){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.YELLOW);
                    NutricionActivity.this.recreate();
                    Intent intent = new Intent(NutricionActivity.this, AgregarAlimentoActivity.class);
                    startActivity(intent);
                }else if(i==4){
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.CYAN);
                    NutricionActivity.this.recreate();
                    Intent intent = new Intent(NutricionActivity.this, GuiaNutricionalActivity.class);
                    startActivity(intent);
                }else if(i==5){
                    NutricionActivity.this.recreate();
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.LTGRAY);
                    Intent intent = new Intent(NutricionActivity.this, OrientacionNutricionalActivity.class);
                    startActivity(intent);
                }else if(i==6){
                    NutricionActivity.this.recreate();
                    view.findViewById(R.id.layoutSingleItem).setBackgroundColor(Color.MAGENTA);
                    Intent intent = new Intent(NutricionActivity.this, NovedadesNutricionalesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
