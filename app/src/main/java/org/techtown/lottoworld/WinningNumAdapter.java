package org.techtown.lottoworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WinningNumAdapter extends RecyclerView.Adapter<WinningNumAdapter.ViewHolder>{
    ArrayList<WinningNumber> items = new ArrayList<WinningNumber>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.winning_num_item, parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WinningNumber item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void addItem(WinningNumber item) {
        items.add(item);
    }

    public void setItems(ArrayList<WinningNumber> items) {
        this.items = items;
    }

    public WinningNumber getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, WinningNumber item) {
        items.set(position, item);
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView round;
        TextView winningNums;
        TextView bonusNum;
        TextView statics;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            round = itemView.findViewById(R.id.round);
            winningNums = itemView.findViewById(R.id.winningNums);
            bonusNum = itemView.findViewById(R.id.bonusNum);
            statics = itemView.findViewById(R.id.statics);

        }
        public void setItem(WinningNumber item) {
            String roundT = item.getRound() + "회 당첨번호";
            String staticT = "총합:" + item.getTotal() + " 짝홀:" + item.getEven() + "/" + item.getOdd();

            round.setText(roundT);
            winningNums.setText(item.numberString());
            bonusNum.setText(Integer.toString(item.getWinningNums()[6]));
            statics.setText(staticT);
        }


    }
}
