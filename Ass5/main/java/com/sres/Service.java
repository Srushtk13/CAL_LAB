package com.sres;

import jakarta.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String owner;
    private String model;
    private String type;

    @Column(name = "date")
    private String date;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
