package com.example.firstdatabase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name_id, nim_id;

    public MyAdapter(Context context, ArrayList name_id, ArrayList nim_id) {
        this.context = context;
        this.name_id = name_id;
        this.nim_id = nim_id;

    }
    private int selectedPosition = -1; // Menyimpan posisi item yang dipilih


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override


    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.nim_id.setText(String.valueOf(nim_id.get(position)));

        // Atur background berdasarkan posisi yang dipilih
        if (position == selectedPosition) {
            holder.itemView.setBackgroundResource(R.drawable.card_pressed); // Jika terklik, gunakan background terklik
        } else {
            holder.itemView.setBackgroundResource(R.drawable.card_normal); // Jika tidak, gunakan background normal
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Periksa apakah item yang diklik sama dengan yang sudah dipilih
                if (selectedPosition == holder.getAdapterPosition()) {
                    selectedPosition = -1; // Reset posisi terpilih jika item yang sama diklik lagi
                } else {
                    selectedPosition = holder.getAdapterPosition(); // Simpan posisi yang dipilih
                }

                String selectedName = String.valueOf(name_id.get(holder.getAdapterPosition()));
                ((Userlist) context).onCardClicked(selectedName);
                notifyDataSetChanged(); // Notifikasi adapter untuk memperbarui tampilan
            }
        });
    }




    @Override
    public int getItemCount() {
        return name_id.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id,nim_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            nim_id = itemView.findViewById(R.id.textnim);

        }
    }

    public int getSelectedPosition() {
        return selectedPosition; // Mengembalikan posisi yang dipilih
    }

    public void resetSelection() {
        selectedPosition = -1; // Reset posisi terpilih
        notifyDataSetChanged(); // Notifikasi untuk memperbarui tampilan
    }
}
