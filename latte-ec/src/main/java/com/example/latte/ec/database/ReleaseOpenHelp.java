package com.example.latte.ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * User: bkzhou
 * Date: 2017/12/13
 * Description:
 */
public class ReleaseOpenHelp extends DaoMaster.OpenHelper{
    public ReleaseOpenHelp(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelp(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
