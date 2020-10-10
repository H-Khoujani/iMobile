package pando.iMobile.tools_activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdvanceSearchActivity extends AppCompatActivity {

    private String searchType;
    public AdvanceSearchActivity(String searchType){
        this.searchType = searchType;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);



    }



}
