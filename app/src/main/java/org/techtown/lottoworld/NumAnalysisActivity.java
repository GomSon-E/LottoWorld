package org.techtown.lottoworld;

import static org.techtown.lottoworld.IntroActivity.winningNumberList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumAnalysisActivity extends AppCompatActivity {
    int pages; // 전체 페이지 수
    int totalItem;
    int page = 0; // 현재 페이지

    List<WinningHistory> historyList = new ArrayList<>();
    TextView winningNum;
    TextView total;
    TextView even;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_analysis);

        totalItem = historyList.size();

        if(totalItem % 10 == 0){ // 전체 페이지 계산
            pages = totalItem / 10;
        }else{  pages = totalItem / 10 + 1; }

        int[] nums = {5, 16, 28, 30, 35, 45, -1};


        winningNum = findViewById(R.id.winningNum);

        WinningNumber winningNumber = new WinningNumber();
        winningNumber.setWinningNums(nums);

        total = findViewById(R.id.total);
        even = findViewById(R.id.analysis);
        total.setText("총합:" + winningNumber.getTotal());
        even.setText("짝홀:" + winningNumber.getEven()+"/"+( 6 - winningNumber.getEven()) );
        winningNum.setText(winningNumber.numberString());

        //history 리스트를 만들음
        compareNums(nums);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        NumAnalysisAdapter adapter = new NumAnalysisAdapter();

        addNumItem(adapter);

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

                if(lastPosition == totalCount -1 ){
                    //아이템 추가 ! 입맛에 맞게 설정하시면됩니다.
                    addNumItem(adapter);
                    adapter.notifyDataSetChanged();}
            }
        });

    }

    public void compareNums(int[] nums){

        int cnt; // 같은 숫자의 갯수

        for(WinningNumber wNum : winningNumberList){
            cnt = 0;
            int win_idx, cmp_idx;
            win_idx = cmp_idx = 0;

            while(win_idx <= 5 && cmp_idx <= 5){
                if(wNum.getWinningNums()[win_idx] == nums[cmp_idx]){
                    cnt++;
                    win_idx++;
                    cmp_idx++;
                }else if(wNum.getWinningNums()[win_idx] < nums[cmp_idx]){
                    win_idx++;
                }else{
                    cmp_idx++;
                }
            }
            setRank(cnt, wNum, nums);
        }

    }
    public void setRank(int cnt, WinningNumber wNum,int[] nums ){ // 등수 지정해주는 메소드

        switch(cnt){
            case 6 : // 6개 다 맞을 때 1등
                historyList.add(new WinningHistory(wNum, 1));
                Log.d("1등", cnt + "개 맞음");
                break;
            case 5: // 보너스 번호를 포함한 경우 2등, 아 경우 3등
                if(Arrays.asList(nums).contains(wNum.getWinningNums()[6])){
                    historyList.add(new WinningHistory(wNum, 2));
                    Log.d("2등", cnt + "개 맞음");
                }
                else{
                    historyList.add(new WinningHistory(wNum, 3));
                    Log.d("3등", cnt + "개 맞음");
                }
                break;
            case 4:// 4개를 맞춘 경우 4등
                historyList.add(new WinningHistory(wNum, 4));
                Log.d("4등", cnt + "개 맞음");
                break;
            case 3: // 3개를 맞춘 경우 5등
                historyList.add(new WinningHistory(wNum, 5));
                Log.d("5등", cnt + "개 맞음");
                break;
            default:
                break;
        }
    }

    public void addNumItem(NumAnalysisAdapter adapter){

        int start = page * 10;
        int end;

        if( totalItem < (page + 1) * 10){
            end = totalItem;
        }else{
            end = (page + 1) * 10;
        }
        Log.d("오류확인용 ㅋ",start + ", " + end);

        for(int i = start; i < end; i++){
            adapter.addItem(historyList.get(i));
        }
        page ++;

    }
}