package edu.psu.sweng888.carinventorymanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Vehicle {
    private String vehicleName;
    private String vehicleModel;
    private String color;
    private int year;
    private int price;
    private String imageUrl; // New field for the image URL

    // Default constructor required for calls to DataSnapshot.getValue(Vehicle.class)
    public Vehicle() {}

    public Vehicle(String vehicleName, String vehicleModel, String color, int year, int price, String imageUrl) {
        this.vehicleName = vehicleName;
        this.vehicleModel = vehicleModel;
        this.color = color;
        this.year = year;
        this.price = price;
        this.imageUrl = imageUrl; // Initialize the image URL
    }

    // Getters and setters
    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl; // Getter for imageUrl
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl; // Setter for imageUrl
    }
}