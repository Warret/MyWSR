package com.example.wsr_tren.OtdelAndBank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wsr_tren.R;

import java.util.ArrayList;

public class GraphicAdapter extends RecyclerView.Adapter <GraphicAdapter.GraphicViewHolder>{

    private ArrayList<RecyclerOtdelBank> graphics;

    public GraphicAdapter(ArrayList<RecyclerOtdelBank> graphics) {
        this.graphics = graphics;
    }

    @NonNull
    @Override
    public GraphicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_otdandbank_adapter, parent, false);
       return  new GraphicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GraphicViewHolder holder, int position) {
        RecyclerOtdelBank recyclerOtdelBank = graphics.get(position);
        holder.textViewStreet.setText(recyclerOtdelBank.getStreet());
        holder.textViewState.setText(recyclerOtdelBank.getState());
        holder.textViewTime.setText(recyclerOtdelBank.getTime());

        if (holder.textViewState.getText().equals("Работает") || holder.textViewState.getText().equals("работает")){
            holder.textViewState.setTextColor(holder.textViewState.getResources().getColor(R.color.colorAccent3443));
        }else {
            holder.textViewState.setTextColor(holder.textViewState.getResources().getColor(R.color.colorPrimaryDark));
        }




    }

    @Override
    public int getItemCount() {
        return graphics.size();
    }

    class GraphicViewHolder extends RecyclerView.ViewHolder {

        TextView textViewStreet;
        TextView textViewState;
        TextView textViewTime;


        public GraphicViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStreet = itemView.findViewById(R.id.textViewStreet);
            textViewState = itemView.findViewById(R.id.textViewState);
            textViewTime = itemView.findViewById(R.id.textViewTime);

        }
    }

}
