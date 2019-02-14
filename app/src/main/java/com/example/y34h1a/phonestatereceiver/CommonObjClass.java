package com.example.y34h1a.phonestatereceiver;

import android.content.Context;

/**
 * *************************************************************
 * * Created by Md. Azizul Islam on 7/21/2017 at 09:07 PM.
 * * Email: imazizul@gmail.com
 * *
 * * Company: W3engineers Ltd
 * * Last edited by : Md. Azizul Islam on 7/21/2017.
 * *
 * * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 * *************************************************************
 */


public class CommonObjClass {

    private static DbManager databaseHelper;

    public synchronized static DbManager getDatabaseHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DbManager(context);
        }
        return databaseHelper;
    }

}
