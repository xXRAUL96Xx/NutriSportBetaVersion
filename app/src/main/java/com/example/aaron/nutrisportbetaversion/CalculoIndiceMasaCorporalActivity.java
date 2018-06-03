package com.example.aaron.nutrisportbetaversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculoIndiceMasaCorporalActivity extends AppCompatActivity {
    Button buttonCalcImc;
    EditText textPeso, textAltura;
    private boolean open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_indice_masa_corporal);
        buttonCalcImc = findViewById(R.id.btn_calculoimc);
        textPeso = findViewById(R.id.editText_pesoIMC);
        textAltura = findViewById(R.id.editText_alturaIMC);
        open = true;
        buttonCalcImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculo_del_IMC(view);
            }
        });
    }
    public void calculo_del_IMC(View view){
        if(open){
            try{
                if(textPeso.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.imc_data), Toast.LENGTH_SHORT).show();
                }else if(textAltura.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.imc_data), Toast.LENGTH_SHORT).show();
                }else{
                    float float_peso = Float.parseFloat(textPeso.getText().toString());
                    float float_altura = Float.parseFloat(textAltura.getText().toString());
                    float float_imc = float_peso / (float_altura * float_altura);
                    if(float_imc < 18){
                        Toast.makeText(this, getString(R.string.imc_result_intro) + " " + float_imc + " " + getString(R.string.imc_result1), Toast.LENGTH_LONG).show();
                    }else if(float_imc >= 18 && float_imc <= 24.9){
                        Toast.makeText(this, getString(R.string.imc_result_intro) + " " + float_imc + " " + getString(R.string.imc_result2), Toast.LENGTH_LONG).show();
                    }else if(float_imc >= 25 && float_imc <= 26.9){
                        Toast.makeText(this, getString(R.string.imc_result_intro) + " " + float_imc + " " + getString(R.string.imc_result3), Toast.LENGTH_LONG).show();
                    }else if(float_imc >= 27 && float_imc <= 29.9){
                        Toast.makeText(this, getString(R.string.imc_result_intro) + " " + float_imc + " " + getString(R.string.imc_result4), Toast.LENGTH_LONG).show();
                    }else if(float_imc >= 30 && float_imc <= 39.9){
                        Toast.makeText(this, getString(R.string.imc_result_intro) + " " + float_imc + " " + getString(R.string.imc_result5), Toast.LENGTH_LONG).show();
                    }else if(float_imc >= 40){
                        Toast.makeText(this, getString(R.string.imc_result_intro) + float_imc + getString(R.string.imc_result6), Toast.LENGTH_LONG).show();
                    }
                    Intent intent = new Intent(CalculoIndiceMasaCorporalActivity.this,NutricionActivity.class);
                    startActivity(intent);
                }
            }catch (NumberFormatException e){
                Toast.makeText(this, getString(R.string.imc_no_data_validate), Toast.LENGTH_LONG).show();
            }
        }
    }
}
