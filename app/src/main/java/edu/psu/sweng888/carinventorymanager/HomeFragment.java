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
        // This inflates the layout for this fragment and assigns it to the 'view' variable.
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // This retrieves the username from the bundle arguments, if provided.
        Bundle arguments = getArguments();
        if (arguments != null) {
            String username = arguments.getString("username");

            // This finds the TextView to display the username and sets the welcome message.
            TextView usernameTextView = view.findViewById(R.id.usernameTextView);
            usernameTextView.setText("Welcome, " + username + "!");
        }

        // This returns the inflated view for rendering in the UI.
        return view;
    }
}