package edu.psu.sweng888.carinventorymanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AddVehicleFragment extends Fragment {

    // Define UI elements for the form inputs and preview
    private EditText vehicleName, vehicleModel, vehicleColor, vehicleYear, vehiclePrice, imageUrl;
    private ImageView vehicleImagePreview;

    // Initialize Firebase Firestore instance for database operations
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for the fragment
        View view = inflater.inflate(R.layout.fragment_add_vehicle, container, false);

        // This initializes the Firebase Firestore instance
        db = FirebaseFirestore.getInstance();

        // This finds and initializes the form input views
        vehicleName = view.findViewById(R.id.vehicleName);
        vehicleModel = view.findViewById(R.id.vehicleModel);
        vehicleColor = view.findViewById(R.id.vehicleColor);
        vehicleYear = view.findViewById(R.id.vehicleYear);
        vehiclePrice = view.findViewById(R.id.vehiclePrice);
        imageUrl = view.findViewById(R.id.imageUrl);
        vehicleImagePreview = view.findViewById(R.id.vehicleImagePreview);
        Button saveVehicleButton = view.findViewById(R.id.saveVehicleButton);

        // Sets up a listener for the image URL input field to load an image preview
        imageUrl.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) { // Only load image when focus leaves the field
                loadImagePreview();
            }
        });

        // Sets up the save button to trigger the saveVehicle() method
        saveVehicleButton.setOnClickListener(v -> saveVehicle());

        return view;
    }

    // This method loads the image preview from the provided URL
    private void loadImagePreview() {
        String url = imageUrl.getText().toString();
        if (TextUtils.isEmpty(url)) {
            // If URL is empty, sets a default placeholder image
            vehicleImagePreview.setImageResource(R.drawable.ic_placeholder);
        } else {
            // Uses Glide to load the image from URL or show a placeholder if the URL is invalid
            Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder) // Shows placeholder if URL is invalid
                    .into(vehicleImagePreview);
        }
    }

    // This method saves the vehicle data entered in the form to Firebase Firestore
    private void saveVehicle() {
        // Retrieves values from input fields
        String name = vehicleName.getText().toString().trim();
        String model = vehicleModel.getText().toString().trim();
        String color = vehicleColor.getText().toString().trim();
        String year = vehicleYear.getText().toString().trim();
        String price = vehiclePrice.getText().toString().trim();
        String imageUrlText = imageUrl.getText().toString().trim();

        // Validates that all required fields are filled in
        if (name.isEmpty() || model.isEmpty() || color.isEmpty() || year.isEmpty() || price.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepares vehicle data to be stored in Firestore
        Map<String, Object> vehicleData = new HashMap<>();
        vehicleData.put("vehicleName", name);
        vehicleData.put("vehicleModel", model);
        vehicleData.put("color", color);
        vehicleData.put("year", Integer.parseInt(year));
        vehicleData.put("price", Double.parseDouble(price));
        vehicleData.put("imageUrl", imageUrlText);

        // Saves the vehicle data to the "vehicles" collection in Firebase Firestore, two collections in firestore ,users and Vehicles
        db.collection("Vehicles")
                .add(vehicleData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Vehicle added successfully", Toast.LENGTH_SHORT).show();
                    clearForm(); // Clears the form after successful save
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to add vehicle", Toast.LENGTH_SHORT).show());
    }

    // This method clears all form inputs and resets the image preview
    private void clearForm() {
        vehicleName.setText("");
        vehicleModel.setText("");
        vehicleColor.setText("");
        vehicleYear.setText("");
        vehiclePrice.setText("");
        imageUrl.setText("");
        vehicleImagePreview.setImageResource(R.drawable.ic_placeholder); // Resets image preview to placeholder
    }
}