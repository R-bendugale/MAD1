package com.example.personalexpensetracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalexpensetracker.R;
import com.example.personalexpensetracker.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {

    private List<Expense> expenses = new ArrayList<>();

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new ExpenseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder holder, int position) {
        Expense currentExpense = expenses.get(position);
        holder.tvTitle.setText(currentExpense.getTitle());
        holder.tvAmount.setText("₹ " + currentExpense.getAmount());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    // Crucial for the list to update when LiveData changes
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        notifyDataSetChanged();
    }

    // Crucial for Swipe-to-Delete to identify the item
    public Expense getExpenseAt(int position) {
        return expenses.get(position);
    }

    class ExpenseHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvAmount;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }
}