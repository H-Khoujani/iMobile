package pando.iMobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cuberto.bubbleicontabbarandroid.TabBubbleAnimator;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import pando.iMobile.phondex.PhondexFragment;
import pando.iMobile.settings.SettingsFragment;
import pando.iMobile.shop_regs.ShopsRegsFragment;
import pando.iMobile.shops_map.ShopsMapFragment;
import pando.iMobile.utils.views.CustomViewPager;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private TabBubbleAnimator tabBubbleAnimator;
    private String[] titles ;
    private int[] colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        titles = new String[]{getString(R.string.your_shops),
                getString(R.string.shops),
                getString(R.string.phondex),
                getString(R.string.settings)};

        colors = new int[]{R.color.home, R.color.clock, R.color.folder, R.color.menu};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }

        getSupportActionBar().hide();


        CustomViewPager viewPager = findViewById(R.id.viewPager);
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

        };
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mFragmentList.add(new ShopsRegsFragment());
                        break;
                    case 1:
                        mFragmentList.add(new ShopsMapFragment());
                        break;
                    case 2:
                        mFragmentList.add(new PhondexFragment());
                        break;
                    case 3:
                        mFragmentList.add(new SettingsFragment());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabBubbleAnimator = new TabBubbleAnimator(tabLayout);
        tabBubbleAnimator.addTabItem(titles[0], R.drawable.ic_grid, colors[0]);
        tabBubbleAnimator.addTabItem(titles[1], R.drawable.ic_clock,colors[1]);
        tabBubbleAnimator.setUnselectedColorId(Color.BLACK);
        tabBubbleAnimator.highLightTab(0);
        viewPager.addOnPageChangeListener(tabBubbleAnimator);
        viewPager.setPagingEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tabBubbleAnimator.onStart((TabLayout) findViewById(R.id.tabLayout));
    }

    @Override
    protected void onStop() {
        super.onStop();
        tabBubbleAnimator.onStop();
    }




}