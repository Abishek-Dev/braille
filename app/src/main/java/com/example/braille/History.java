package com.example.braille;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class History extends AppCompatActivity {

    private RecyclerView recyclerView;
    HistoryAdapter
            adapter;
    TextView back;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mbase
                = FirebaseDatabase.getInstance().getReference("History");
        back=(TextView)findViewById(R.id.back);

        String mGroupId = mbase.push().getKey();
        Log.i("uid", mGroupId);

        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        List<GetHistory> arr= new ArrayList<>();
        FirebaseRecyclerOptions<GetHistory> options
                = new FirebaseRecyclerOptions.Builder<GetHistory>()
                .setQuery(mbase, GetHistory.class)
                .build();
        Log.d("User", "Name: " + options);
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new HistoryAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(History.this,MainActivity.class);
                startActivity(i1);
            }
        });

    }
    private void fun(String key){

    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}