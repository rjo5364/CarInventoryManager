package edu.psu.sweng888.carinventorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // This initializes Firebase Authentication instance
        mAuth = FirebaseAuth.getInstance();

        // This finds the email and password input fields
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        // This sets up the click listener for the login button
        findViewById(R.id.loginButton).setOnClickListener(v -> loginUser());

        // This sets up the click listener for the signup text view
        // Redirects the user to SignupActivity if they want to register
        findViewById(R.id.signupText).setOnClickListener(v -> startActivity(new Intent(this, SignupActivity.class)));
    }


    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // This attempts to sign in the user with Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // If login is successful, navigates to MainActivity with the user's display name
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", mAuth.getCurrentUser().getDisplayName());
                startActivity(intent);
                finish(); // Closes LoginActivity after successful login
            } else {
                // Shows a message if login fails
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}