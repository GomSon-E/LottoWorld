package org.techtown.lottoworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DataBaseHelper extends SQLiteOpenHelper {
    private final static String TAG = "DataBaseHelper"; // Logcat에 출력할 태그이름

    // database 의 파일 경로
    private static String DB_PATH = "";
    private static String DB_NAME = "lotto_data.db";
    private SQLiteDatabase mDataBase;
    private Context mContext;

    // DATABASE_VERSION = 회차 / DB 버전
    public static int DATABASE_VERSION = 1029;
    public static String PURCHASE_HISTORY_TABLE_NAME = "purchase_history_table";

    public DataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DATABASE_VERSION);

        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
        dataBaseCheck();
    }

    private void dataBaseCheck()
    {
        File dbFile = new File(DB_PATH + DB_NAME);

        if (!dbFile.exists())
        {
            dbCopy();
            Log.d(TAG,"Database is copied.");
        }
    }

    // db를 assets에서 복사해온다.
    private void dbCopy()
    {
        try
        {
            File folder = new File(DB_PATH);
            if (!folder.exists())
            {
                folder.mkdir();
            }

            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            String out_filename = DB_PATH + DB_NAME;
            OutputStream outputStream = new FileOutputStream(out_filename);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = inputStream.read(mBuffer)) > 0)
            {
                outputStream.write(mBuffer, 0, mLength);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //데이터베이스를 열어서 쿼리를 쓸수있게만든다.
    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;

        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        Log.d("openDataBase", mPath);
        Log.d("openDataBase", "did");
        return mDataBase != null;
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {

        Log.d(TAG,"onCreate() : DB Creating!");
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        // CHECK WINNING - PURCHASE HISTORY TABLE 생성
        // round = 회차, rank = 순위, 1st~6th = 로또번호 인덱스
        String query =
                "CREATE TABLE IF NOT EXISTS " + PURCHASE_HISTORY_TABLE_NAME + " (" +
                        "round "     + "INTEGER," +
                        "rank"       + "INTEGER," +
                        "1st"        + "INTEGER," +
                        "2nd"        + "INTEGER," +
                        "3rd"        + "INTEGER," +
                        "4th"        + "INTEGER," +
                        "5th"        + "INTEGER," +
                        "6th"        + "INTEGER"  + ")";

        Log.d(TAG,"onOpen() : DB Opening!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d(TAG,"onUpgrade() : DB Schema Modified and Excuting onCreate()");
    }

    @Override
    public synchronized void close()
    {

        Log.d(TAG,"close() : DBhelper closed");
        if (mDataBase != null)
        {
            mDataBase.close();
        }
        super.close();
    }

}
