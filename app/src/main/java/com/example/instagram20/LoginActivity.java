package com.example.instagram20;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

//A login screen that offers login via username/password
public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //First check if a user is logged in
        if (ParseUser.getCurrentUser() != null){
            goToMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "OnClick sign up button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signUpUser(username, password);
            }
        });
    }
    //Registers a new user
    private void signUpUser(String username, String password){
        //Create the ParseUser
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        Log.i(TAG, "Attempting to register new user " + username);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    //TODO:Better error handling; Inform user
                    Log.e(TAG, "Issue with sign up", e);
                    Toast.makeText(LoginActivity.this, "Issue with sign up!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Navigate to the main activity if user has been successfully registered
                goToMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    //Logs User in
    private void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user " + username);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            //We are using logIn in background because we do not want it to intefere with the ui
            //We just want the user to be logged in, and see the result of the login(main activity)
            // without actually seeing the process
            @Override
            public void done(ParseUser user, ParseException e) {
                //Check if the log in was unsuccessful (i.e e != null)
                if (e != null){
                    //TODO:Better error handling; Inform user
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with login!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Navigate to the main activity if user has signed in properly
                goToMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Navigates to MainActivity on Successful Login
    private void goToMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    //Logs user out
    public static void logOut(boolean exit){
        if (exit == true) {
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser();//this will now be null
        }
    }
}