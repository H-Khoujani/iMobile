package pando.iMobile.phondex.phondex_main.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.RawQuery;

import java.util.List;

import pando.iMobile.phondex.phondex_main.db.entitys.Phone;

@Dao
public interface PhoneDao{

    @RawQuery
    LiveData<List<Phone>> getPhones(String query);

    @RawQuery
    LiveData<Phone> search(String Query);

    //onConflict = OnConflictStrategy.IGNORE
    @Insert()
    void insertSoldier(Phone phone);

}
