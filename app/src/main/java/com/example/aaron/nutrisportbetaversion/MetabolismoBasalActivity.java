package com.example.aaron.nutrisportbetaversion;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MetabolismoBasalActivity extends AppCompatActivity {
    Button buttonBasal;
    EditText textSexo, textPeso;
    private boolean open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolismo_basal);
        buttonBasal = findViewById(R.id.btn_calcBasal);
        textPeso = findViewById(R.id.editText_PesoBasal);
        textSexo = findViewById(R.id.editText_SexoBasal);
        open = true;
        buttonBasal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*buttonBasal.setBackgroundColor(Color.parseColor("#9E9E9E"));*/
                calculo_meta_basal(view);
            }
        });
    }

    public void calculo_meta_basal(View view){
        if(open){
            try{
                if(textSexo.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.meta_valor), Toast.LENGTH_SHORT).show();
                }else if(textPeso.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.meta_valor), Toast.LENGTH_SHORT).show();
                }else{
                    float float_peso = Float.parseFloat(textPeso.getText().toString());
                    float float_sexo_mb = Float.parseFloat(textSexo.getText().toString());
                    float float_meta_basal = float_peso * 24 * float_sexo_mb;
                    Toast.makeText(this, getString(R.string.meta_valorCalorifico1) + " " + float_meta_basal + " "+ getString(R.string.meta_valorCalorifico2), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MetabolismoBasalActivity.this, NutricionActivity.class);
                    startActivity(intent);
                }
            }catch (NumberFormatException e){
                Toast.makeText(this, getString(R.string.meta_no_data_validate), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
