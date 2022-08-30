package org.techtown.lottoworld;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class WinningHistoryActivity extends AppCompatActivity {
    int pages; // 전체 페이지 수
    int totalItem;
    int page = 0; // 현재 페이지

    public List<WinningNumber> winningNumberList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_history);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        WinningNumAdapter adapter = new WinningNumAdapter();


        try {
            initLoadDB(); //db읽어서 winningnum 리스트에 할당

            totalItem = winningNumberList.size();

            if(totalItem % 10 == 0){ // 전체 페이지 계산
                pages = totalItem / 10;
            }else{  pages = totalItem / 10 + 1; }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount();

                if(lastPosition == totalCount){
                    //아이템 추가 ! 입맛에 맞게 설정하시면됩니다.
                    //
                    adapter.notifyDataSetChanged();}
            }
        });
    }

    private void initLoadDB() throws SQLException {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        winningNumberList = mDbHelper.getLottoData();

        // db 닫기
        mDbHelper.close();
    }
    public void addNumItem(WinningNumAdapter adapter){
        int start = page * 10;
        int end;

        if( totalItem < (page + 1) * 10){
            end = totalItem;
        }else{
            end = (page + 1) * 10;
        }
        for(int i = start; i < end; i++){
            adapter.addItem(winningNumberList.get(i));
        }

    }
}