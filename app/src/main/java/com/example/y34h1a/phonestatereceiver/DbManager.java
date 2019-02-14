package com.example.y34h1a.phonestatereceiver;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;


public class DbManager {

    private SQLiteDatabase mDatabase;
    private DbHelper dbHelper;

    public DbManager(Context context) {
        dbHelper = new DbHelper(context.getApplicationContext());
        mDatabase = dbHelper.getReadableDatabase();
    }


    private String[] column_call_info = {
            DbHelper.COL_ID,
            DbHelper.COL_PHN_NU,
            DbHelper.COL_LATITUDE,
            DbHelper.COL_LONGITUDE
    };

    //-------------SET PHONE INFO-------------------

    public void setCallInfo(CallInfo callInfo) {
        String sql = "INSERT INTO " + (DbHelper.TABLE_INFO + " VALUES (?,?,?,?);");
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        statement.clearBindings();
        statement.bindString(2, callInfo.getPhnNumber());
        statement.bindString(3, callInfo.getLatitute());
        statement.bindString(4, callInfo.getLongitude());
        statement.execute();
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }


    public List<CallInfo> getCallInfos(){
        List<CallInfo> callInfos = new ArrayList<>();
        Cursor cursor = mDatabase.query(DbHelper.TABLE_INFO, column_call_info,
                null, null, null, null, DbHelper.COL_ID + " DESC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                CallInfo callInfo = new CallInfo();
                callInfo.setPhnNumber(cursor.getString(cursor.getColumnIndex(DbHelper.COL_PHN_NU)));
                callInfo.setLatitute(cursor.getString(cursor.getColumnIndex(DbHelper.COL_LATITUDE)));
                callInfo.setLongitude(cursor.getString(cursor.getColumnIndex(DbHelper.COL_LONGITUDE)));
                callInfos.add(callInfo);
            } while (cursor.moveToNext());
        }


        cursor.close();

        return callInfos;
    }
}