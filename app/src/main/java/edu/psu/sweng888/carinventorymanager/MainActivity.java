package edu.psu.sweng888.carinventorymanager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("username");

        // Pass username to HomeFragment
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        // Load the HomeFragment with the username argument when the activity starts
        loadFragment(homeFragment);

        // Set up click listeners for drawer items
        TextView homeItem = findViewById(R.id.homeFragment);
        TextView vehicleListItem = findViewById(R.id.vehicleListFragment);
        TextView addVehicleItem = findViewById(R.id.addVehicleFragment);

        homeItem.setOnClickListener(v -> {
            HomeFragment newHomeFragment = new HomeFragment();
            newHomeFragment.setArguments(bundle);
            loadFragment(newHomeFragment);
            drawerLayout.closeDrawers();
        });

        vehicleListItem.setOnClickListener(v -> {
            loadFragment(new VehicleListFragment());
            drawerLayout.closeDrawers();
        });

        addVehicleItem.setOnClickListener(v -> {
            loadFragment(new AddVehicleFragment());
            drawerLayout.closeDrawers();
        });

        // Set up click listeners for bottom navigation icons
        ImageButton bottomHome = findViewById(R.id.bottom_home);
        ImageButton bottomVehicleList = findViewById(R.id.bottom_vehicle_list);
        ImageButton bottomAddVehicle = findViewById(R.id.bottom_add_vehicle);

        bottomHome.setOnClickListener(v -> {
            HomeFragment newHomeFragment = new HomeFragment();
            newHomeFragment.setArguments(bundle);
            loadFragment(newHomeFragment);
        });

        bottomVehicleList.setOnClickListener(v -> loadFragment(new VehicleListFragment()));

        bottomAddVehicle.setOnClickListener(v -> loadFragment(new AddVehicleFragment()));
    }

    // Helper method to load fragments
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}