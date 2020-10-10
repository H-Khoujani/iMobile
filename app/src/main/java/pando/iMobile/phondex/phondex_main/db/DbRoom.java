package pando.iMobile.phondex.phondex_main.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import pando.iMobile.phondex.phondex_main.db.dao.PhoneDao;
import pando.iMobile.phondex.phondex_main.db.entitys.Phone;
import pando.iMobile.utils.l;

/* main database */
@Database(entities = {Phone.class} , version = 1 , exportSchema = false)
public abstract class DbRoom extends RoomDatabase {

    private static final String DB_NAME = "phones.db";

    public abstract PhoneDao phoneDao();

    private static DbRoom instance;

    public static synchronized DbRoom getInstance(Context context){

        if (instance == null){
            //create database on data/data/package-project/army.db

            final File dbFile = context.getDatabasePath(DB_NAME);

            if(!dbFile.exists() && copyDatabaseFile(context ,dbFile.getAbsolutePath())) {

                instance = Room.databaseBuilder(context , DbRoom.class , DB_NAME.split("\\.")[0]).
                        fallbackToDestructiveMigration().
                        addCallback(new Callback(){
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                            }

                            @Override
                            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                super.onOpen(db);
                            }
                        }).allowMainThreadQueries().build();

            }

        }

        return instance;
    }


    private static boolean copyDatabaseFile(Context context , String destinationPath) {
        try{
            AssetManager am = context.getAssets();
            @SuppressLint("SdCardPath") OutputStream os = new FileOutputStream("/data/data/"+
                    context.getPackageName()+"/databases/"+DB_NAME);
            byte[] b = new byte[100];
            int r;
            InputStream is = am.open(DB_NAME);
            while ((r = is.read(b)) != -1) {
                os.write(b, 0, r);
            }
            is.close();
            os.close();
            return true;
        }
        catch(Exception e)
        {
            l.l(e.getMessage());
            return false;
        }
    }


}
