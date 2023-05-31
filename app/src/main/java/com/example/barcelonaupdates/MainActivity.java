package com.example.barcelonaupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barcelonaupdates.database.UserHelper;
import com.example.barcelonaupdates.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText emailEt, passEt;
    Button loginBtn, regisBtn;
    UserHelper userHelper = new UserHelper(this);
    ArrayList<User> users;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEt = findViewById(R.id.email_et);
        passEt = findViewById(R.id.password_et);

        loginBtn = findViewById(R.id.login_btn);
        regisBtn = findViewById(R.id.regis_btn);

        loginBtn.setOnClickListener(e->{
            String email = emailEt.getText().toString();
            String pass = passEt.getText().toString();

            if(validUser(email, pass)!=null){
                User user = validUser(email, pass);
                // shared preferences
                sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("UserID", user.getUserId());
                editor.apply();
                // toHome
                Intent toHome = new Intent(this, HomeActivity.class);
                startActivity(toHome);
            }

        });

        regisBtn.setOnClickListener(e->{
            Intent toRegis = new Intent(this, RegisterActivity.class);
            startActivity(toRegis);
        });

    }

    public User validUser(String email, String pass){
        users = userHelper.getAllUsers();
        for (User user:users) {
            if(email.equals(user.getEmail())){
                if(pass.equals(user.getPassword())){
                    return user;
                }
            }
        }
        Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
        return null;
    }
}