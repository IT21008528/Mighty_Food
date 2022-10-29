package com.example.mighty_food.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mighty_food.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    //create objects of database reference class to access firebase's real time database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mightyfood-62f38-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText userId = findViewById(R.id.userID);
        final EditText mobileID = findViewById(R.id.mobileID);
        final EditText passwordID = findViewById(R.id.passwordID);
        final EditText RePasswordID = findViewById(R.id.RePasswordID);

        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView loginNowId = findViewById(R.id.loginNowId);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from EditTexts into Strings
                final String userNameTxt = userId.getText().toString();
                final String mobileIDTxt = mobileID.getText().toString();
                final String passwordIDTxt = passwordID.getText().toString();
                final String RePasswordIDTxt = RePasswordID.getText().toString();

                if(userNameTxt.isEmpty() || mobileIDTxt.isEmpty() || passwordIDTxt.isEmpty() || RePasswordIDTxt.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if (!passwordIDTxt.equals(RePasswordIDTxt)){
                    Toast.makeText(RegisterActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(userNameTxt)){
                                Toast.makeText(RegisterActivity.this, "This User Name already registered to system", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                // sending data to firebase realtime database

                                databaseReference.child("Users").child(userNameTxt).child("mobileID").setValue(mobileIDTxt);
                                databaseReference.child("Users").child(userNameTxt).child("passwordID").setValue(passwordIDTxt);

                                Toast.makeText(RegisterActivity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        loginNowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}