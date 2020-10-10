//package pando.iMobile.phondex.phondex_main.views.activitys;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import java.util.List;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import pando.iMobile.R;
//import pando.iMobile.phondex.phondex_main.db.entitys.Soldier;
//import pando.iMobile.phondex.phondex_main.viewmodel.ArmyViewModel;
//import pando.iMobile.phondex.phondex_main.views.adapter.ArmyAdapter;
//
//public class MainActivity extends AppCompatActivity {
//
//    public ArmyViewModel viewModel;
//
//    @BindView(R.id.army)
//    RecyclerView army_list;
//    @BindView(R.id.add)
//    FloatingActionButton add;
////    Retrofit retrofit;
////    List<Post> post_list;
//
//    public  ArmyAdapter adapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_content);
//        ButterKnife.bind(this);
//        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//        army_list.setLayoutManager(new LinearLayoutManager(this));
//        army_list.setHasFixedSize(true);
//        adapter = new ArmyAdapter();
//        army_list.setAdapter(adapter);
//
//
////        retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/posts").
////                addConverterFactory(GsonConverterFactory.create()).build();
////
////        PlaceHolderApi pha = retrofit.create(PlaceHolderApi.class);
////        Call<List<Post>> call = pha.getAll();
////        call.enqueue(new Callback<List<Post>>() {
////            @Override
////            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
////                if (response.isSuccessful()){
////                    Toast.makeText(MainActivity.this, "Code :"+response.code(), Toast.LENGTH_SHORT).show();
////
////                    post_list = response.body();
////
////                }else{
////                    return;
////                }
////            }
////
////            @Override
////            public void onFailure(Call<List<Post>> call, Throwable t) {
////                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////        });
//
//
//        adapter.setOnItemClickListener(new ArmyAdapter.onItemClickListener() {
//            @Override
//            public void onClick(Soldier soldier) {
//
//                Intent intent = new Intent(MainActivity.this , UpdateSoldierActivity.class);
//                intent.putExtra("id" , soldier.getId());
//                intent.putExtra("name" , soldier.getName());
//                intent.putExtra("age" , soldier.getAge());
//                intent.putExtra("faction" , soldier.getFaction());
//                intent.putExtra("troop" , soldier.getTroop());
//                intent.putExtra("power" , soldier.getPower());
//                startActivity(intent);
//
//
//            }
//        });
//
//        viewModel = ViewModelProviders.of(this).get(ArmyViewModel.class);
//        viewModel.getAllSoldiers().observe(this, new Observer<List<Soldier>>() {
//            @Override
//            public void onChanged(List<Soldier> soldiers) {
//                //update adapter listview
//                adapter.setSoldiers(soldiers);
//            }
//        });
//
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null){
//
//            if (bundle.getString("flag").equals("edit")){
//
//                Soldier soldier = new Soldier(
//                        bundle.getString("name"),
//                        bundle.getString("age"),
//                        bundle.getString("faction"),
//                        bundle.getString("troop"),
//                        bundle.getString("power"));
//                soldier.setId(bundle.getInt("id"));
//                viewModel.update(soldier);
//
//            }else if(bundle.getString("flag").equals("add")){
//                viewModel.insert(new Soldier(
//
//                        bundle.getString("name"),
//                        bundle.getString("age"),
//                        bundle.getString("faction"),
//                        bundle.getString("troop"),
//                        bundle.getString("power")
//
//                ));
//            }
//
//
//        }
//
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this , AddSoldierActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//
//        //delete swiper
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,
//                ItemTouchHelper.LEFT
//                        | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                viewModel.delete(adapter.getCurrent(viewHolder.getAdapterPosition()));
//                Toast.makeText(MainActivity.this, "Soldier Deleted.", Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(army_list);
//
//    }
//
//}
