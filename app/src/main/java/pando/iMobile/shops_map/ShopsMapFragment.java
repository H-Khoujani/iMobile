package pando.iMobile.shops_map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.library.banner.BannerLayout;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.heinrichreimersoftware.materialdrawer.DrawerView;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerHeaderItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;

import java.util.Objects;

import pando.iMobile.MainActivity;
import pando.iMobile.R;
import pando.iMobile.utils.data.StoreValue;
import pando.iMobile.utils.data.adapters.LocalDataAdapter;


public class ShopsMapFragment extends Fragment implements OnMapReadyCallback {


    private View parentView;
    private Context thisActivityContext;
    private static final int REQUEST_GOOGLE_MAP_FINE_LOCATION = 112;

    private MapView mapView;
    private GoogleMap map;
    private GoogleApiClient googleApiClient;

    private GPSTracker tracker;
    private boolean gps_enable;
    private boolean first_moved = false;

    private DrawerView drawer;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_map, container, false);

        BannerLayout recyclerBanner =  parentView.findViewById(R.id.recycler);
        recyclerBanner.setAdapter(new LocalDataAdapter());

        drawerLayout = (DrawerLayout) parentView.findViewById(R.id.drawerLayout);
        drawer = (DrawerView) parentView.findViewById(R.id.drawer);

        thisActivityContext = getActivity();
        tracker = new GPSTracker(getActivity());

        initDrawer();

//        Bundle bundle = getArguments();
//        if (bundle.getString("gps").equals("on")){gps_enable = true;}else{gps_enable=false;}
//
////        mapView = (MapView) parentView.findViewById(R.id.map_gym);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(getActivity());



        return parentView;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(thisActivityContext, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(thisActivityContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            map = googleMap;
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            map.setMyLocationEnabled(true);

            initFirstCamera();



            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {



                    return true;
                }
            });


        } else {
            ActivityCompat.requestPermissions((Activity) thisActivityContext,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_GOOGLE_MAP_FINE_LOCATION);
        }

    }


    public void initFirstCamera(){
        if (gps_enable && !first_moved) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(tracker.getLatitude()
                            , tracker.longitude), 15f));
                    first_moved = true;
                }
            }, 1000);

        } else {
            //load last stored location


            String location = StoreValue.getVal(getActivity() , StoreValue.User.last_user_location , "");
            double lat = Double.parseDouble(location.split(":")[0]);
            double lng = Double.parseDouble(location.split(":")[1]);
            LatLng latLng = new LatLng(lat , lng);
            float zoom = Float.parseFloat(location.split(":")[2]);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));

            first_moved = true;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        mapView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
//        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        StoreValue.editVal(getActivity() , StoreValue.User.last_user_location ,
                map.getCameraPosition().target.latitude+":"
                        +map.getCameraPosition().target.longitude+":"
                        +map.getCameraPosition().zoom);
//        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        mapView.onLowMemory();
    }

    public void initDrawer(){
        drawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            public void onDrawerClosed(View view) {
            }
            public void onDrawerOpened(View drawerView) {
            }
        };

        drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        drawerLayout.addDrawerListener(drawerToggle);
        drawerLayout.closeDrawer(drawer);


        drawer.addItem(new DrawerItem()
                .setTextPrimary(getString(R.string.lorem_ipsum_short))
                .setTextSecondary(getString(R.string.lorem_ipsum_long))
        );

        drawer.addItem(new DrawerItem()
                .setImage(ContextCompat.getDrawable(getActivity(), R.drawable.ic_menu))
                .setTextPrimary(getString(R.string.lorem_ipsum_short))
                .setTextSecondary(getString(R.string.lorem_ipsum_long))
        );

        drawer.addDivider();

        drawer.addItem(new DrawerItem()
                .setRoundedImage(getActivity() ,BitmapFactory.decodeResource(getResources() , R.drawable.add_plus))
                .setTextPrimary(getString(R.string.lorem_ipsum_short))
                .setTextSecondary(getString(R.string.lorem_ipsum_long))
        );

        drawer.addItem(new DrawerHeaderItem().setTitle(getString(R.string.lorem_ipsum_short)));

        drawer.addItem(new DrawerItem()
                .setTextPrimary(getString(R.string.lorem_ipsum_short))
        );

        drawer.addItem(new DrawerItem()
                .setRoundedImage(getActivity() ,BitmapFactory.decodeResource(getResources() , R.drawable.add_plus), DrawerItem.SMALL_AVATAR)
                .setTextPrimary(getString(R.string.lorem_ipsum_short))
                .setTextSecondary(getString(R.string.lorem_ipsum_long), DrawerItem.THREE_LINE)
        );

        drawer.selectItem(1);
        drawer.setOnItemClickListener(new DrawerItem.OnItemClickListener() {
            @Override
            public void onClick(DrawerItem item, long id, int position) {
                drawer.selectItem(position);
                Toast.makeText(getActivity(), "Clicked item #" + position, Toast.LENGTH_SHORT).show();
            }
        });


//        drawer.addFixedItem(new DrawerItem()
//                .setRoundedImage(getActivity() ,BitmapFactory.decodeResource(getResources() , R.drawable.add_plus), DrawerItem.SMALL_AVATAR)
//                .setTextPrimary(getString(R.string.lorem_ipsum_short))
//        );
//
//        drawer.addFixedItem(new DrawerItem()
//                .setImage(getContext().getResources().getDrawable(R.drawable.add_plus))
//                .setTextPrimary(getString(R.string.lorem_ipsum_short))
//        );

//        drawer.setOnFixedItemClickListener(new DrawerItem.OnItemClickListener() {
//            @Override
//            public void onClick(DrawerItem item, long id, int position) {
//                drawer.selectFixedItem(position);
//                Toast.makeText(getActivity(), "Clicked fixed item #" + position, Toast.LENGTH_SHORT).show();
//            }
//        });


        drawer.addProfile(new DrawerProfile()
                .setId(1)
                .setRoundedAvatar(getActivity() ,BitmapFactory.decodeResource(getResources() , R.drawable.add_plus))
                .setBackground(getContext().getResources().getDrawable(R.drawable.add_plus))
                .setName(getString(R.string.lorem_ipsum_short))
                .setDescription(getString(R.string.lorem_ipsum_medium))
        );

        drawer.addProfile(new DrawerProfile()
                .setId(2)
                .setRoundedAvatar(getActivity() ,BitmapFactory.decodeResource(getResources() , R.drawable.add_plus))
                .setBackground(getContext().getResources().getDrawable(R.drawable.add_plus))
                .setName(getString(R.string.lorem_ipsum_short))
        );

        drawer.addProfile(new DrawerProfile()
                .setId(3)
                .setRoundedAvatar(getActivity() ,BitmapFactory.decodeResource(getResources() , R.drawable.add_plus))
                .setBackground(getContext().getResources().getDrawable(R.drawable.add_plus))
                .setName(getString(R.string.lorem_ipsum_short))
                .setDescription(getString(R.string.lorem_ipsum_medium))
        );


        drawer.setOnProfileClickListener(new DrawerProfile.OnProfileClickListener() {
            @Override
            public void onClick(DrawerProfile profile, long id) {
                Toast.makeText(getActivity(), "Clicked profile *" + id, Toast.LENGTH_SHORT).show();
            }
        });
        drawer.setOnProfileSwitchListener(new DrawerProfile.OnProfileSwitchListener() {
            @Override
            public void onSwitch(DrawerProfile oldProfile, long oldId, DrawerProfile newProfile, long newId) {
                Toast.makeText(getActivity(), "Switched from profile *" + oldId + " to profile *" + newId, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
