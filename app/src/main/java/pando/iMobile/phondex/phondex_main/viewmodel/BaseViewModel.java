package pando.iMobile.phondex.phondex_main.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import pando.iMobile.phondex.phondex_main.db.entitys.Phone;
import pando.iMobile.phondex.phondex_main.respository.LocalRespository;

public class BaseViewModel extends AndroidViewModel {

    private LocalRespository localRespository;
    private LiveData<List<Phone>> allSoldiers;

    public BaseViewModel(@NonNull Application application, String table){
        super(application);

        localRespository = new LocalRespository(application ,table);

        allSoldiers = localRespository.getAllSoldier();

    }

//    public void insert (Phone phone){
//        localRespository.insert(phone);
//    }
//
//    public void update (Phone phone){
//        localRespository.update(phone);
//    }
//
//    public void delete (Phone phone){
//        localRespository.delete(phone);
//    }

    public LiveData<List<Phone>> getAllSoldiers() {
        return allSoldiers;
    }
}
