package com.example.personalexpensetracker.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private double amount;
    private long timestamp;

    public Expense(String title, double amount, long timestamp) {
        this.title = title;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}