package com.example.veterans_code_a_thon_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ProgressBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;

public class newUser extends AppCompatActivity {
    private EditText firstname, lastname,email,password;
    private TextView LogInPage;
    private Button createaccount;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference mdatabase;
    private static final String userr="user"
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        firstname=findViewById(R.id.name);
        lastname=findViewById(R.id.last);
        email=findViewById(R.id.email);
        LogInPage=findViewById(R.id.loginpage);
        createaccount=findViewById(R.id.createAccount);
        password=findViewById(R.id.password);
        firebaseAuth=FirebaseAuth.getInstance();

LogInPage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(newUser.this,MainActivity.class));
    }
});

    }

    public void onClick(View view) {
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(newUser.this,"Registered successfully",Toast.LENGTH_SHORT);
                    Intent activity2Intent = new Intent(getApplicationContext(), BusinessesActivity.class);
                    startActivity(activity2Intent);
                }

                else{
                    Toast.makeText(newUser.this,task.getException().getMessage(),Toast.LENGTH_SHORT);
                }


            }
        });
    }
}

