package com.example.aaron.nutrisportbetaversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aaron.nutrisportbetaversion.Objetos.Alimento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarAlimentoActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    EditText textProducto, textHidratos, textProteinas, textGrasas, textEnergia, textFibra;
    TextView textInfo;
    Button buttonAddFood;
    private Alimento alimento = new Alimento();
    private boolean open;
    String uid;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alimento);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        textProducto = findViewById(R.id.textAddProducto);
        textHidratos = findViewById(R.id.textAddHidratos);
        textProteinas = findViewById(R.id.textAddProteinas);
        textGrasas = findViewById(R.id.textAddGrasas);
        textEnergia = findViewById(R.id.textAddEnergia);
        textFibra = findViewById(R.id.textAddFibra);
        textInfo = findViewById(R.id.textView_infoFood);
        buttonAddFood = findViewById(R.id.btn_addFood);
        open = true;
        if(user != null){
            uid = user.getUid();
        }
        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load_data(view);
            }
        });
    }

    public void load_data(View view){
        if(open){
            try{
                if(textProducto.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarAlimento_data), Toast.LENGTH_SHORT).show();
                }else if(textHidratos.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarAlimento_data), Toast.LENGTH_SHORT).show();
                }else if(textProteinas.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarAlimento_data), Toast.LENGTH_SHORT).show();
                }else if(textGrasas.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarAlimento_data), Toast.LENGTH_SHORT).show();
                }else if(textEnergia.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarAlimento_data), Toast.LENGTH_SHORT).show();
                }else if(textFibra.getText().toString().equals("")){
                    Toast.makeText(this, getString(R.string.agregarAlimento_data), Toast.LENGTH_SHORT).show();
                }else{
                    String producto = textProducto.getText().toString().trim();
                    float hidratos = Float.parseFloat(textHidratos.getText().toString().trim());
                    float proteinas = Float.parseFloat(textProteinas.getText().toString().trim());
                    float grasas = Float.parseFloat(textGrasas.getText().toString().trim());
                    float energia = Float.parseFloat(textEnergia.getText().toString().trim());
                    float fibra = Float.parseFloat(textFibra.getText().toString().trim());
                    alimento.setProducto(producto);
                    alimento.setHidratos(hidratos);
                    alimento.setProteinas(proteinas);
                    alimento.setGrasas(grasas);
                    alimento.setEnergia(energia);
                    alimento.setFibra(fibra);
                    String key = databaseReference.child("usuario").push().getKey();
                    alimento.setKey(key);
                    alimento.setUid(uid);
                    alimento.setCreadoBy(user.getEmail());
                    databaseReference.child("alimentos").child(key).setValue(alimento);
                    Toast.makeText(this, getString(R.string.agregarAlimento_ok), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AgregarAlimentoActivity.this, NutricionActivity.class);
                    startActivity(intent);
                }
            }catch (NumberFormatException e){
                Toast.makeText(this, getString(R.string.agregarAlimento_no_data_validate), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
