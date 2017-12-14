package com.example.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * User: bkzhou
 * Date: 2017/12/13
 * Description:
 */
public class DatabaseManger {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private  DatabaseManger(){

    }
    public DatabaseManger init(Context context){
        initDao(context);
        return this;
    }
    private static final class Holder{
        private final static DatabaseManger INSTANCE  = new DatabaseManger();
    }
    public static DatabaseManger getInstance(){
        return Holder.INSTANCE;
    }
    private void initDao(Context context){
        final ReleaseOpenHelp help = new ReleaseOpenHelp(context,"fasr_ec.db");
        final Database db = help.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }
    public final UserProfileDao getDao(){
        return mDao;
    }
}
