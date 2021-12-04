package com.example.braille;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView gth;
    EditText msg;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.btn);
        msg=(EditText)findViewById(R.id.msg);
        gth=(TextView)findViewById(R.id.gotohistory);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference myRef2 = database.getReference();
        myRef.child("message");
        btn.setOnClickListener(v -> {
            String m=msg.getText().toString().trim();
            myRef.child("message").setValue(m);

            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("Message",m);
            hashMap.put("Date", String.valueOf(formattedDate));
            hashMap.put("Time", String.valueOf(currentTime));

            DatabaseReference op=myRef2.child("History").push();
            op.setValue(hashMap);
            Toast.makeText(getApplicationContext(),m,Toast.LENGTH_LONG).show();
        });
        gth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,History.class);
                startActivity(i1);
            }
        });

    }
}