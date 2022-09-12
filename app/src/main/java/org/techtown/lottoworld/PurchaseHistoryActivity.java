package org.techtown.lottoworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.sql.SQLException;

public class PurchaseHistoryActivity extends AppCompatActivity {
    DataBaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        mDBHelper = new DataBaseHelper(getApplicationContext());
        if(mDBHelper != null)
        {
            Log.d("TAG" , " 구매목록에서 열림");
        }

        try {
            mDBHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}