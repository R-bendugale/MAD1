package com.example.filehandling;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    TextView tv;
    EditText ed1;
    String data;
    private String file = "mydata.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        ed1=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textview2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=ed1.getText().toString();
                try {
                    FileOutputStream fOut = openFileOutput(file,MODE_APPEND);
                    fOut.write(data.getBytes());
                    fOut.close();
                    Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }  });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput(file);
                    int c;
                    String temp="";
                    while( (c = fin.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }
                    tv.setText(temp);
                    Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                }
            }  });
    } }
