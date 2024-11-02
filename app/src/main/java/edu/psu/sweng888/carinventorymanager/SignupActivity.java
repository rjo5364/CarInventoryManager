package edu.psu.sweng888.carinventorymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    // Declare Firebase authentication and Firestore instances
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    // Declare UI elements for user input fields
    private EditText emailEditText, passwordEditText, usernameEditText, firstNameEditText, lastNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase instances
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Find views from layout for input fields
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        usernameEditText = findViewById(R.id.username);
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);

        // Set click listener for signup button to trigger registration
        findViewById(R.id.signupButton).setOnClickListener(v -> registerUser());
    }

    // This method registers a new user with Firebase Authentication
    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();

        // Check if any field is empty
        if (email.isEmpty() || password.isEmpty() || username.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(SignupActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new user in Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    // Update the user profile with the display name (username)
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .build();

                    user.updateProfile(profileUpdates).addOnCompleteListener(profileUpdateTask -> {
                        if (profileUpdateTask.isSuccessful()) {
                            // After updating profile, save additional details in Firestore
                            saveUserDetails(user.getUid(), username, firstName, lastName, email);
                        } else {
                            Toast.makeText(SignupActivity.this, "Profile update failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // This method saves user details to Firestore
    private void saveUserDetails(String uid, String username, String firstName, String lastName, String email) {
        // Prepare a map with user details
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", username);
        userMap.put("firstName", firstName);
        userMap.put("lastName", lastName);
        userMap.put("email", email);

        // Store user data in Firestore
        db.collection("users").document(uid)
                .set(userMap)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(SignupActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    // Start MainActivity and pass the username in the Intent
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(SignupActivity.this, "Failed to save user details", Toast.LENGTH_SHORT).show());
    }
}