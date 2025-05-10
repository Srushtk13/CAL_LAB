package com.vsms.model;

public class Vehicle {
    private int id;
    private String ownerName;
    private String vehicleNumber;
    private String model;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
}