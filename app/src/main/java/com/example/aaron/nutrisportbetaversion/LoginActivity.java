package com.example.aaron.nutrisportbetaversion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aaron.nutrisportbetaversion.Objetos.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    // Declaramos los componentes de dicha ventana
    private DatabaseReference databaseReference;
    Button buttonRegister, buttonSingIn;
    EditText editTextEmail, editTextPassword;
    CheckBox checkBox;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    String uid;
    private User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Inicializamos dichos elementos
        buttonRegister = findViewById(R.id.btnReg);
        buttonSingIn = findViewById(R.id.btnIniSe);
        editTextEmail =  findViewById(R.id.txtEmail);
        editTextPassword =  findViewById(R.id.txtPassword);
        checkBox = findViewById(R.id.checkbox);
        checkBox.setChecked(true);
        if(checkBox.isChecked() == true){
            SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
            editTextEmail.setText(preferences.getString(getString(R.string.login_userEmail),""));
            editTextPassword.setText(preferences.getString(getString(R.string.login_pass), ""));
        }

        buttonRegister.setOnClickListener(this);
        buttonSingIn.setOnClickListener(this);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                // Comprobamos si el usuario existe o no
                if(user != null){
                    // Podremoslanzar el comienzo de activitys con start
                    Log.i("SESION", "sesion iniciada con email " + user.getEmail());
                }else{
                    Log.i("SESION", "sesion cerrada");
                }
            }
        };

        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            uid = user.getUid();
        }
    }

    // Creamos un void para el clickeo de registrar
    public void registrar(String email,String pass){
        if(editTextEmail.getText().toString().equals("")){
            Toast.makeText(this, getString(R.string.login_validateEmail), Toast.LENGTH_SHORT).show();
        }else if(editTextPassword.getText().toString().equals("")){
            Toast.makeText(this, getString(R.string.login_validatePass), Toast.LENGTH_SHORT).show();
        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String email = editTextEmail.getText().toString();
                        String password = editTextPassword.getText().toString();
                        String key = databaseReference.push().getKey();
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setKey(key);
                        user.setUid(uid);
                        databaseReference.child("users").child(key).setValue(user);
                        Log.i("SESION", "usuario creado correctamente");
                        Toast.makeText(LoginActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Log.e("SESION", task.getException().getMessage() + "");
                        Toast.makeText(LoginActivity.this, getString(R.string.login_no_data_validate), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void iniciarSesion (final String email, final String pass){
        if(editTextEmail.getText().toString().equals("")){
            Toast.makeText(this, getString(R.string.login_validateEmail), Toast.LENGTH_SHORT).show();
        }else if(editTextPassword.getText().toString().equals("")){
            Toast.makeText(this, getString(R.string.login_validatePass), Toast.LENGTH_SHORT).show();
        }else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String correo = editTextEmail.getText().toString();
                        String pass = editTextPassword.getText().toString();
                        if(checkBox.isChecked() == true){
                            SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(getString(R.string.login_userEmail), correo);
                            editor.putString(getString(R.string.login_pass), pass);
                            editor.commit();
                            editTextEmail.setText("");
                            editTextPassword.setText("");
                            checkBox.setChecked(false);
                        }
                        Log.i("SESION", "inicio de sesion correctamente");
                        Toast.makeText(LoginActivity.this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Log.e("SESION", task.getException().getMessage() + "");
                        Toast.makeText(LoginActivity.this, getString(R.string.login_no_data_validate), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void clean_data(View view){
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(getString(R.string.login_userEmail));
        editor.remove(getString(R.string.login_pass));
        editor.commit();
        //Limpiamoslos campos de la pantalla
        editTextEmail.setText("");
        editTextPassword.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIniSe:
                String emailS = editTextEmail.getText().toString();
                String passS = editTextPassword.getText().toString();
                iniciarSesion(emailS, passS);
                break;
            case R.id.btnReg:
                String emailR = editTextEmail.getText().toString();
                String passR = editTextPassword.getText().toString();
                registrar(emailR,passR);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener != null ){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }
}
