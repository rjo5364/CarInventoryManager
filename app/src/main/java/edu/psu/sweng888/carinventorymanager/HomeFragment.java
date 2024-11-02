package edu.psu.sweng888.carinventorymanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Retrieve the username from the bundle
        Bundle arguments = getArguments();
        if (arguments != null) {
            String username = arguments.getString("username");

            // Display the username in the TextView
            TextView usernameTextView = view.findViewById(R.id.usernameTextView);
            usernameTextView.setText("Welcome, " + username + "!");
        }

        return view;
    }
}