package edu.psu.sweng888.carinventorymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.bumptech.glide.Glide;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    // List of vehicles to display in the RecyclerView
    private List<Vehicle> vehicleList;

    // Constructor sets the vehicle list
    public VehicleAdapter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflates the item_vehicle layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        // Binds data to the views for each vehicle in the list
        Vehicle vehicle = vehicleList.get(position);

        // Sets the vehicle attributes in respective TextViews
        holder.vehicleName.setText(vehicle.getVehicleName());
        holder.vehicleModel.setText(vehicle.getVehicleModel());
        holder.vehicleColor.setText("Color: " + vehicle.getColor());
        holder.vehicleYear.setText("Year: " + vehicle.getYear());
        holder.vehiclePrice.setText("Price: $" + vehicle.getPrice());

        // Loads the vehicle image using Glide, or sets a placeholder if no URL is available
        String imageUrl = vehicle.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder) // Sets placeholder if URL is invalid
                    .into(holder.vehicleImage);
        } else {
            holder.vehicleImage.setImageResource(R.drawable.ic_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        // Returns the total number of items in the vehicle list
        return vehicleList.size();
    }

    // ViewHolder class holds references to each view in the item layout
    static class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView vehicleName, vehicleModel, vehicleColor, vehicleYear, vehiclePrice;
        ImageView vehicleImage;

        VehicleViewHolder(View itemView) {
            super(itemView);
            // Finds views by ID for each item in the RecyclerView
            vehicleName = itemView.findViewById(R.id.vehicleName);
            vehicleModel = itemView.findViewById(R.id.vehicleModel);
            vehicleColor = itemView.findViewById(R.id.vehicleColor);
            vehicleYear = itemView.findViewById(R.id.vehicleYear);
            vehiclePrice = itemView.findViewById(R.id.vehiclePrice);
            vehicleImage = itemView.findViewById(R.id.vehicleImage);
        }
    }
}