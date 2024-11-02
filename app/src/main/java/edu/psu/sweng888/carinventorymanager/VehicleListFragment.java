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
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private List<Vehicle> vehicleList;
    private VehicleAdapter adapter;

    private static final String TAG = "VehicleListFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_vehicle_list_fragment, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        vehicleList = new ArrayList<>();
        adapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        loadVehicles();

        return view;
    }

    private void loadVehicles() {
        db.collection("Vehicles")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    vehicleList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Vehicle vehicle = document.toObject(Vehicle.class);
                        vehicleList.add(vehicle);
                    }
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "Vehicles loaded successfully.");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error loading vehicles", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error loading vehicles", e);
                });
    }
}