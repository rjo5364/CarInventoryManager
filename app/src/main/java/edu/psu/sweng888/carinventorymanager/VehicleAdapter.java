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
    private List<Vehicle> vehicleList;

    public VehicleAdapter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);

        holder.vehicleName.setText(vehicle.getVehicleName());
        holder.vehicleModel.setText(vehicle.getVehicleModel());
        holder.vehicleColor.setText("Color: " + vehicle.getColor());
        holder.vehicleYear.setText("Year: " + vehicle.getYear());
        holder.vehiclePrice.setText("Price: $" + vehicle.getPrice());

        // Use Glide to load the image URL or display a placeholder if the URL is empty
        String imageUrl = vehicle.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder) // Placeholder if URL is invalid
                    .into(holder.vehicleImage);
        } else {
            holder.vehicleImage.setImageResource(R.drawable.ic_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView vehicleName, vehicleModel, vehicleColor, vehicleYear, vehiclePrice;
        ImageView vehicleImage;

        VehicleViewHolder(View itemView) {
            super(itemView);
            vehicleName = itemView.findViewById(R.id.vehicleName);
            vehicleModel = itemView.findViewById(R.id.vehicleModel);
            vehicleColor = itemView.findViewById(R.id.vehicleColor);
            vehicleYear = itemView.findViewById(R.id.vehicleYear);
            vehiclePrice = itemView.findViewById(R.id.vehiclePrice);
            vehicleImage = itemView.findViewById(R.id.vehicleImage);
        }
    }
}