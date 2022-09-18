package org.techtown.lottoworld;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter
{
    protected static final String TAG = "DataAdapter";

    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_NAME = "tb_lotto_list";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter open() throws SQLException, java.sql.SQLException {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
        }
        catch (SQLException | java.sql.SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public List getWinningData()
    {
        try
        {
            mDb = mDbHelper.getReadableDatabase();
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + TABLE_NAME;

            // 모델 넣을 리스트 생성
            List winningList = new ArrayList();

            // TODO : 모델 선언
            NumberQuery numberQuery = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    numberQuery = new NumberQuery();

                    // TODO : Record 기술
                    // round, date, 1st, 2nd, 3rd, 4th, 5th, 6th, bonus
                    numberQuery.setRound(mCur.getInt(0));
                    numberQuery.setDate(mCur.getString(1));
                    int first = mCur.getInt(2);
                    int second = mCur.getInt(3);
                    int third = mCur.getInt(4);
                    int fourth = mCur.getInt(5);
                    int fifth = mCur.getInt(6);
                    int sixth = mCur.getInt(7);
                    int bonus = mCur.getInt(8);
                    numberQuery.setNums(new int[]{first,second,third,fourth,fifth,sixth,bonus});

                    // 리스트에 넣기
                    winningList.add(numberQuery);
                }

            }
            return winningList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public void insertWinningNum(String date, NumberQuery wn){
        mDb = mDbHelper.getWritableDatabase();
        int[] nums = wn.getNums();
        String query = "INSERT INTO  tb_lotto_made"
                + " (date, first, second, third, fourth, fifth, sixth) "
                + " VALUES ("
                + " '" + date + "', "
                + nums[0] + ", "
                + nums[1] + ", "
                + nums[2] + ", "
                + nums[3] + ", "
                + nums[4] + ", "
                + nums[5] + "); ";
        Log.d("insertWinningNum" , query);
        mDb.execSQL(query);
    }
}