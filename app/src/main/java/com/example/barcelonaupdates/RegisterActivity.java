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

public class RegisterActivity extends AppCompatActivity {

    EditText userNameEt, emailEt, passEt, confirmPassEt;
    Button regisBtn, loginBtn;
    UserHelper userHelper = new UserHelper(this);
    ArrayList<User>users;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEt = findViewById(R.id.name_et);
        emailEt = findViewById(R.id.email_et);
        passEt = findViewById(R.id.password_et);
        confirmPassEt = findViewById(R.id.confirm_pass_et);

        regisBtn = findViewById(R.id.regis_btn);
        loginBtn = findViewById(R.id.login_btn);

        users = userHelper.getAllUsers();

        regisBtn.setOnClickListener(e->{
            String name = userNameEt.getText().toString();
            String email = emailEt.getText().toString();
            String pass = passEt.getText().toString();
            String confirmPass = confirmPassEt.getText().toString();
            if(valid(pass, confirmPass)) {
                for (User user: users) {
                    if(email.equals(user.getEmail())){
                        Toast.makeText(this, "Email is already taken!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                User user = new User(users.size()+1, name, email,pass);
                userHelper.regisUser(user);
                Toast.makeText(this, "You have been registered!", Toast.LENGTH_SHORT).show();
                // shared preferences
                sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("UserID", user.getUserId());
                editor.apply();
                // to home
                Intent toHome = new Intent(this, HomeActivity.class);
                startActivity(toHome);
            }
        });

        loginBtn.setOnClickListener(e->{
            // buat liat user yg udah register di console
            users = userHelper.getAllUsers();
            for (User user: users) {
                Log.e("User", String.valueOf(user.getUserId()));
                Log.e("User", user.getUserName());
                Log.e("User", user.getEmail());
                Log.e("User", user.getPassword());
            }
            Intent toLogin = new Intent(this, MainActivity.class);
            startActivity(toLogin);
        });

        userHelper.close();
    }

    public boolean valid(String pass, String confirmPass){
        if(!pass.equals(confirmPass)){
            Toast.makeText(this, "Password and Confirm Password should be the same!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}