package edu.psu.sweng888.carinventorymanager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class VehicleListFragment extends Fragment {

    // RecyclerView for display the list of vehicles
    private RecyclerView recyclerView;

    // Firebase Firestore instance for database access
    private FirebaseFirestore db;

    // List to store vehicles retrieved from Firestore
    private List<Vehicle> vehicleList;

    // Adapter for managing the data displayed in the RecyclerView
    private VehicleAdapter adapter;

    // Log tag for debugging purposes
    private static final String TAG = "VehicleListFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_vehicle_list_fragment, container, false);

        // Find the RecyclerView and set up its layout manager
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the vehicle list and set up the adapter for the RecyclerView
        vehicleList = new ArrayList<>();
        adapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(adapter);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Load vehicles from Firestore
        loadVehicles();

        return view;
    }

    // Method to load vehicles from Firestore and update the RecyclerView
    private void loadVehicles() {
        db.collection("Vehicles") // Reference to the "Vehicles" collection in Firestore
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    vehicleList.clear(); // Clear existing data to avoid duplicates
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Convert each document to a Vehicle object and add it to the list
                        Vehicle vehicle = document.toObject(Vehicle.class);
                        vehicleList.add(vehicle);
                    }
                    // Notify the adapter that data has changed
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "Vehicles loaded successfully.");
                })
                .addOnFailureListener(e -> {
                    // Display error message if loading fails
                    Toast.makeText(getContext(), "Error loading vehicles", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error loading vehicles", e);
                });
    }
}