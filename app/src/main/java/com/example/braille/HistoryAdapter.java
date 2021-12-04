package com.example.braille;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HistoryAdapter extends FirebaseRecyclerAdapter<
        GetHistory, HistoryAdapter.HistoryViewholder> {

    public HistoryAdapter(
            @NonNull FirebaseRecyclerOptions<GetHistory> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull HistoryViewholder holder,
                     int position, @NonNull GetHistory model)
    {

        holder.Message.setText(String.valueOf((model.getMessage())));

        holder.Date.setText(String.valueOf((model.getDate())));
        holder.Time.setText(String.valueOf(model.getTime()));
        Log.d("User", "Date: " + model.getDate());
    }

    @NonNull
    @Override
    public HistoryViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_card, parent, false);
        return new HistoryAdapter.HistoryViewholder(view);
    }

    class HistoryViewholder
            extends RecyclerView.ViewHolder {
        TextView Message, Date, Time,back;
        public HistoryViewholder(@NonNull View itemView)
        {
            super(itemView);

//            back=itemView.findViewById(R.id.back);
//            back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i1=new Intent(v.getContext(),MainActivity.class);
//                    v.getContext().startActivity(i1);
//                }
//            });
            Message= itemView.findViewById(R.id.Message);
            Date = itemView.findViewById(R.id.Date);
            Time = itemView.findViewById(R.id.Time);
        }
    }


}
