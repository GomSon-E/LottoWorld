package org.techtown.lottoworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class SelfInputActivity extends AppCompatActivity {
    
    // Todo : local DB 에서 latest Round 를 가져온 후
    // Self Input 에서 쓰게끔
    int latestRound_Temporary = 1029;
    Button purchaseHistoryButton,saveNumbersButton;
    Spinner roundSpinner;
    ArrayList arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_input);

        saveNumbersButton = findViewById(R.id.SaveTicketButton);
        roundSpinner = findViewById(R.id.roundSpinner);
        adapterSetting();
    }

    public void adapterSetting(){
        // Todo : 일단 latest 없이 하드코딩
        for(int i=latestRound_Temporary;i>=1;i--){
            arrayList.add(Integer.toString(i) + "회");
        }
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,
                arrayList);
        roundSpinner.setAdapter(arrayAdapter);
        roundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Todo : 회차에 맞는 번호 DB에서 가져오기
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}