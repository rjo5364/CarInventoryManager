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

        // This initializes the DrawerLayout for navigation drawer functionality
        drawerLayout = findViewById(R.id.drawer_layout);

        // This retrieves the username from the Intent passed from Login or Signup activity
        String username = getIntent().getStringExtra("username");

        // This creates a bundle to pass the username to HomeFragment
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);

        // This loads the HomeFragment as the initial screen, displaying the username
        loadFragment(homeFragment);

        // This sets up TextView click listeners for each navigation drawer item
        TextView homeItem = findViewById(R.id.homeFragment);
        TextView vehicleListItem = findViewById(R.id.vehicleListFragment);
        TextView addVehicleItem = findViewById(R.id.addVehicleFragment);

        // Sets click listener to load HomeFragment with username when the drawer's "Home" item is clicked
        homeItem.setOnClickListener(v -> {
            HomeFragment newHomeFragment = new HomeFragment();
            newHomeFragment.setArguments(bundle);
            loadFragment(newHomeFragment);
            drawerLayout.closeDrawers(); // Closes the drawer after selection
        });

        // Sets click listener to load VehicleListFragment when the drawer's "Vehicle List" item is clicked
        vehicleListItem.setOnClickListener(v -> {
            loadFragment(new VehicleListFragment());
            drawerLayout.closeDrawers();
        });

        // Sets click listener to load AddVehicleFragment when the drawer's "Add Vehicle" item is clicked
        addVehicleItem.setOnClickListener(v -> {
            loadFragment(new AddVehicleFragment());
            drawerLayout.closeDrawers();
        });

        // Sets up click listeners for bottom navigation icons, allowing quick access to fragments
        ImageButton bottomHome = findViewById(R.id.bottom_home);
        ImageButton bottomVehicleList = findViewById(R.id.bottom_vehicle_list);
        ImageButton bottomAddVehicle = findViewById(R.id.bottom_add_vehicle);

        // Sets click listener to load HomeFragment with username when the bottom "Home" icon is clicked
        bottomHome.setOnClickListener(v -> {
            HomeFragment newHomeFragment = new HomeFragment();
            newHomeFragment.setArguments(bundle);
            loadFragment(newHomeFragment);
        });

        // Sets click listener to load VehicleListFragment when the bottom "Vehicle List" icon is clicked
        bottomVehicleList.setOnClickListener(v -> loadFragment(new VehicleListFragment()));

        // Sets click listener to load AddVehicleFragment when the bottom "Add Vehicle" icon is clicked
        bottomAddVehicle.setOnClickListener(v -> loadFragment(new AddVehicleFragment()));
    }

    //This helper method replaces the current fragment in the container with the provided fragment.
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}