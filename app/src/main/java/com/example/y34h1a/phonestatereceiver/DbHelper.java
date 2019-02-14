package com.example.y34h1a.phonestatereceiver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_Call_Locator = "dbCallLocator";


    public static final String TABLE_INFO = "tbl_call_info";

    public static final String COL_ID = "id";
    public static final String COL_PHN_NU = "phone_number";
    public static final String COL_LATITUDE = "latitude";
    public static final String COL_LONGITUDE = "longitude";
    public static final String COL_PLACE = "place";

    public static final int VERSION = 1;

    private final String CREATE_INFO_DB = "create table if not exists " + TABLE_INFO + " ( "
            + COL_ID + " integer primary key autoincrement, "
            + COL_PHN_NU + " text, "
            + COL_LATITUDE + " text, "
            + COL_LONGITUDE + " text "
            + " ); ";

    public DbHelper(Context context) {
        super(context, DB_Call_Locator, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INFO_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table " + TABLE_INFO);
    }
}
