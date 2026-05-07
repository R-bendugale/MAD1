package com.example.personalexpensetracker;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalexpensetracker.adapter.ExpenseAdapter;
import com.example.personalexpensetracker.database.AppDatabase;
import com.example.personalexpensetracker.model.Expense;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private TextView tvTotal;
    private ExpenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);
        tvTotal = findViewById(R.id.tvTotalAmount);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        RecyclerView recyclerView = findViewById(R.id.rvExpenses);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseAdapter();
        recyclerView.setAdapter(adapter);

        // Observe LiveData for updates
        db.expenseDao().getTotalExpenses().observe(this, total -> {
            tvTotal.setText("₹ " + (total != null ? total : 0.0));
        });

        db.expenseDao().getAllExpenses().observe(this, expenses -> {
            adapter.setExpenses(expenses);
        });

        fabAdd.setOnClickListener(v -> showAddExpenseDialog());

        // Setup Swipe-to-Delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Expense expenseToDelete = adapter.getExpenseAt(position);

                // Execute on background thread to prevent UI lag
                Executors.newSingleThreadExecutor().execute(() -> {
                    db.expenseDao().delete(expenseToDelete);
                });

                Toast.makeText(MainActivity.this, "Expense Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void showAddExpenseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_expense, null);
        EditText etTitle = dialogView.findViewById(R.id.etTitle);
        EditText etAmount = dialogView.findViewById(R.id.etAmount);

        builder.setView(dialogView)
                .setTitle("Add New Expense")
                .setPositiveButton("Save", (dialog, which) -> {
                    String t = etTitle.getText().toString();
                    String a = etAmount.getText().toString();
                    if (!t.isEmpty() && !a.isEmpty()) {
                        Executors.newSingleThreadExecutor().execute(() ->
                                db.expenseDao().insert(new Expense(t, Double.parseDouble(a), System.currentTimeMillis())));
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}