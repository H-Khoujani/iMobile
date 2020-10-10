//package pando.iMobile.phondex.phondex_main.views.activitys;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import pando.iMobile.phondex.phondex_main.R;
//
//public class UpdateSoldierActivity extends AppCompatActivity {
//
//    private EditText name , age , faction , troop, power;
//    private int mId;
//    private String mName , mAge , mFaction , mTroop, mPower;
//    private Button update;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.update_dl);
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            mName = bundle.getString("name");
//            mId = bundle.getInt("id");
//            mAge = bundle.getString("age");
//            mFaction = bundle.getString("faction");
//            mTroop = bundle.getString("troop");
//            mPower = bundle.getString("power");
//
//
//            name = findViewById(R.id.name);
//            name.setText(mName);
//            age = findViewById(R.id.age);
//            age.setText(mAge);
//            faction = findViewById(R.id.faction);
//            faction.setText(mFaction);
//            troop = findViewById(R.id.troop);
//            troop.setText(mTroop);
//            power = findViewById(R.id.power);
//            power.setText(mPower);
//            update = findViewById(R.id.button);
//            update.setText("Update");
//            update.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(UpdateSoldierActivity.this , MainActivity.class);
//                    intent.putExtra("id" , mId);
//                    intent.putExtra("flag" , "edit");
//                    intent.putExtra("name" , name.getText().toString());
//                    intent.putExtra("age" , age.getText().toString());
//                    intent.putExtra("faction" , faction.getText().toString());
//                    intent.putExtra("troop" , troop.getText().toString());
//                    intent.putExtra("power" , power.getText().toString());
//                    startActivity(intent);
//
//                }
//            });
//
//        }
//
//
//    }
//}
