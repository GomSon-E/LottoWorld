package org.techtown.lottoworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

public class WinningNumPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_nums);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        WinningNumAdapter adapter = new WinningNumAdapter();

        adapter.addItem(new WinningNumber(1030, "2010-10-19", new int[] {13, 25, 30, 31, 37, 41, 24} ));
        adapter.addItem(new WinningNumber(1030, "2010-10-29", new int[] {13, 25, 30, 31, 37, 41, 24} ));
        adapter.addItem(new WinningNumber(1030, "2010-10-29", new int[] {13, 25, 30, 31, 37, 41, 24} ));
        adapter.addItem(new WinningNumber(1030, "2010-10-29", new int[] {13, 25, 30, 31, 37, 41, 24} ));
        adapter.addItem(new WinningNumber(1030, "2010-10-29", new int[] {13, 25, 30, 31, 37, 41, 24} ));
        adapter.addItem(new WinningNumber(1030, "2010-10-29", new int[] {13, 25, 30, 31, 37, 41, 24} ));
        adapter.addItem(new WinningNumber(1030, "2010-10-29", new int[] {1, 2, 3, 4, 5, 6, 7} ));

        recyclerView.setAdapter(adapter);
    }
}