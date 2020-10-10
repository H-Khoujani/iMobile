package pando.iMobile.phondex.phondex_main.respository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import pando.iMobile.phondex.phondex_main.db.DbRoom;
import pando.iMobile.phondex.phondex_main.db.Queries;
import pando.iMobile.phondex.phondex_main.db.dao.PhoneDao;
import pando.iMobile.phondex.phondex_main.db.entitys.Phone;

public class LocalRespository {

    public static final String SELECT = "select";
    public static final String SEARCH = "search";

    private LiveData<List<Phone>> allPhones;
    private PhoneDao phoneDao;
    public static Context context;

    public LocalRespository(Application application ,String table ){
        context = application.getApplicationContext();
        DbRoom dbRoom = DbRoom.getInstance(context);
        phoneDao = dbRoom.phoneDao();
        allPhones = phoneDao.getPhones(Queries.SELECT_ALL(table));
    }

    public LiveData<List<Phone>> getAllSoldier(){
        return allPhones;
    }


    private static class OperationTasker extends AsyncTask<Phone, Void , Void> {

        private PhoneDao phoneDao;
        private final String operation;
        public OperationTasker(PhoneDao phoneDao, String operation) {
            this.phoneDao = phoneDao;
            this.operation = operation;

        }

        @Override
        protected Void doInBackground(Phone... phones) {

            switch (operation){

                case SELECT:
                    phoneDao.getPhones(Queries.SELECT_ALL(phones[0].getBrand()));
                    break;
                case SEARCH:
                    phoneDao.search(Queries.SELECT_PHONE(phones[0].getBrand() , phones[0].getId()));
                    break;

            }


            return null;
        }

    }


}
