package org.techtown.lottoworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NumAnalysisAdapter extends RecyclerView.Adapter<NumAnalysisAdapter.ViewHolder>{
    ArrayList<WinningHistory> items = new ArrayList<WinningHistory>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.history_item, parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WinningHistory item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void addItem(WinningHistory item) {
        items.add(item);
    }

    public void setItems(ArrayList<WinningHistory> items) {
        this.items = items;
    }

    public WinningHistory getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, WinningHistory item) {
        items.set(position, item);
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView round;
        TextView winningNums;
        TextView bonusNum;
        TextView rank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            round = itemView.findViewById(R.id.round);
            winningNums = itemView.findViewById(R.id.winningNums);
            bonusNum = itemView.findViewById(R.id.bonusNum);
            rank = itemView.findViewById(R.id.rank);

        }
        public void setItem(WinningHistory item) {
            WinningNumber winningNumber = item.getWinningNumber();
            String roundT = winningNumber.getRound() + "회 당첨번호";

            round.setText(roundT);
            winningNums.setText(winningNumber.numberString());
            bonusNum.setText(String.valueOf(winningNumber.getWinningNums()[6]));
            rank.setText(item.getRank());
        }

    }
}
