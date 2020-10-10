//package pando.iMobile.phondex.phondex_main.views.activitys;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import pando.iMobile.R;
//
//public class AddSoldierActivity extends AppCompatActivity {
//
//    EditText name , age , faction , troop, power;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.insert_dl);
//
//        name = findViewById(R.id.name);
//        age = findViewById(R.id.age);
//        faction= findViewById(R.id.faction);
//        troop = findViewById(R.id.troop);
//        power = findViewById(R.id.power);
//        power.setText("350");
//
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AddSoldierActivity.this , MainActivity.class);
//            intent.putExtra("name" , name.getText().toString());
//            intent.putExtra("age" , age.getText().toString());
//            intent.putExtra("faction" , faction.getText().toString());
//            intent.putExtra("troop" , troop.getText().toString());
//            intent.putExtra("power" , power.getText().toString());
//            intent.putExtra("flag" , "add");
//            startActivity(intent);
//
//            }
//        });
//
//
//
//
//
//    }
//}
