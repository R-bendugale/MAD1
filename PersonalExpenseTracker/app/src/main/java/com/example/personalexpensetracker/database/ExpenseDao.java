package com.example.personalexpensetracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.personalexpensetracker.model.Expense;
import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert
    void insert(Expense expense);

    @Delete
    void delete(Expense expense);

    @Query("SELECT * FROM expense_table ORDER BY timestamp DESC")
    LiveData<List<Expense>> getAllExpenses();

    @Query("SELECT SUM(amount) FROM expense_table")
    LiveData<Double> getTotalExpenses();
}