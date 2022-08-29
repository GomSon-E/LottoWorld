package org.techtown.lottoworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.sql.SQLException;
import java.util.List;

public class WinningHistoryActivity extends AppCompatActivity {

    public List<WinningNumber> winningNumberList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_history);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        WinningNumAdapter adapter = new WinningNumAdapter();

        try {
            initLoadDB();
            for(WinningNumber wn :winningNumberList){
                adapter.addItem(wn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(adapter);
    }

    private void initLoadDB() throws SQLException {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        winningNumberList = mDbHelper.getLottoData();

        // db 닫기
        mDbHelper.close();
    }
}