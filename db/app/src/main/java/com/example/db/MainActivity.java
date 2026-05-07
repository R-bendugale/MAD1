package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button b1, b2;
    TextView t1;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        t1 = findViewById(R.id.textView3);

        try {
            db = openOrCreateDatabase("StudentDB", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Temp(id INTEGER, name TEXT)");
        } catch (SQLException e) {
            Toast.makeText(this, "Database Error", Toast.LENGTH_SHORT).show();
        }

        // INSERT BUTTON
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText eid = findViewById(R.id.editText1);
                EditText ename = findViewById(R.id.editText2);

                ContentValues values = new ContentValues();
                values.put("id", eid.getText().toString());
                values.put("name", ename.getText().toString());

                long result = db.insert("Temp", null, values);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                    eid.setText("");
                    ename.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Insert Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // DISPLAY BUTTON
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str = "";
                Cursor c = db.rawQuery("SELECT * FROM Temp", null);

                if (c.moveToFirst()) {
                    do {
                        str = str + c.getString(0) + "  " + c.getString(1) + "\n";
                    } while (c.moveToNext());
                } else {
                    str = "No Records Found";
                }

                t1.setText(str);
                c.close();
            }
        });
    }
}