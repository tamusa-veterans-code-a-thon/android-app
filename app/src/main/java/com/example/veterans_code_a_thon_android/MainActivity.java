package com.example.veterans_code_a_thon_android;
// hello, world

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
private EditText email, password;
private Button loginButton, newuser;
private TextView forgotPasswordButton;
    private FirebaseAuth firebaseAuth;
    private final String TAG="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.LoginEmail);
        password = findViewById(R.id.LoginPassword);
        loginButton = findViewById(R.id.LOGINButton);
        newuser = findViewById(R.id.newUser);
        forgotPasswordButton = findViewById(R.id.forgotPassword);
        firebaseAuth=FirebaseAuth.getInstance();
//user will be taken to the new user signup page to create an account
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, newUser.class));
            }
        });
        //user will be taken to a different activity to reset password
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgotPassword.class));
            }
        });
    }
    //log the user in the email and password fields are not empty
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, TempActivity.class);
        startActivity(intent);

        String mail=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if (mail.isEmpty()|| pass.isEmpty()){
            Toast.makeText(getApplicationContext(), "All fields required to log In", Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.w(TAG,"sign in with email Successful");
                    Toast.makeText(MainActivity.this,"LogIn successful",Toast.LENGTH_SHORT).show();
                    FirebaseUser user= firebaseAuth.getCurrentUser();
                    updateUI(user);
                    startActivity(new Intent(getApplicationContext(),ForgotPassword.class));

                }
                else{
                    Log.w(TAG,"sign in with email failed", task.getException());
                    Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser Userr=firebaseAuth.getCurrentUser();
        if (Userr!=null){
            updateUI(Userr);
        };
    }
public void updateUI(FirebaseUser currentUser){
        Intent profile=new Intent(this,CategoriesActivity.class);
        profile.putExtra("email",currentUser.getEmail());
        startActivity(profile);

}
//       FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");

        //In Flixter, we get database/json update
        //in the mainactivity




}