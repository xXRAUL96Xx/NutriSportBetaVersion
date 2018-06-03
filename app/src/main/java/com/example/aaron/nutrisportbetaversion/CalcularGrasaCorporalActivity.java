package com.example.aaron.nutrisportbetaversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalcularGrasaCorporalActivity extends AppCompatActivity {
    Button buttonGrasa;
    EditText textEdad, textSexo, textIMC;
    private boolean open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_grasa_corporal);
        buttonGrasa = findViewById(R.id.btn_calcGrasaCorporea);
        textEdad = findViewById(R.id.editText_Edad);
        textIMC = findViewById(R.id.editText_IMC);
        textSexo = findViewById(R.id.editText_Sexo);
        open = true;

        buttonGrasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular_Grasa(view);
            }
        });
    }

    public void calcular_Grasa(View view){
        if(open){
            try{
                if(textEdad.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.grasaCorporea_data), Toast.LENGTH_SHORT).show();
                }else if(textIMC.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.grasaCorporea_data), Toast.LENGTH_SHORT).show();
                }else if(textSexo.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.grasaCorporea_data), Toast.LENGTH_SHORT).show();
                }else{
                    int int_sexo = Integer.parseInt(textSexo.getText().toString());
                    int int_edad = Integer.parseInt(textEdad.getText().toString());
                    float int_imc = Float.parseFloat(textIMC.getText().toString());
                    double calculo_de_grasa = 1.2 * int_imc + 0.23 * int_edad - 10.8 * int_sexo - 5.4;
                    Toast.makeText(this, getString(R.string.grasa_result1) + " " + calculo_de_grasa + " " + getString(R.string.grasa_result2), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CalcularGrasaCorporalActivity.this, NutricionActivity.class);
                    startActivity(intent);
                }
            }catch (NumberFormatException e){
                Toast.makeText(this, getString(R.string.grasa_no_data_validate), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
