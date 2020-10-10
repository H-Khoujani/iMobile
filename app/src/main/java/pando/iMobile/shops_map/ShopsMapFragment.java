package pando.iMobile.shops_map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import pando.iMobile.MainActivity;
import pando.iMobile.R;
import pando.iMobile.utils.data.StoreValue;

public class ShopsMapFragment extends Fragment implements OnMapReadyCallback {


    private View parentView;
    private Context thisActivityContext;
    private MainActivity menuActivity;
    private static final int REQUEST_GOOGLE_MAP_FINE_LOCATION = 112;

    private MapView mapView;
    private GoogleMap map;
    private GoogleApiClient googleApiClient;

    private GPSTracker tracker;
    private boolean gps_enable;
    private boolean first_moved = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_map, container, false);
        thisActivityContext = getActivity();
        tracker = new GPSTracker(getActivity());

        Bundle bundle = getArguments();
        if (bundle.getString("gps").equals("on")){gps_enable = true;}else{gps_enable=false;}

        mapView = (MapView) parentView.findViewById(R.id.map_gym);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);



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
            map.setMyLocationEnabled(true);

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
        mapView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        StoreValue.editVal(getActivity() , StoreValue.User.last_user_location ,
                map.getCameraPosition().target.latitude+":"
                        +map.getCameraPosition().target.longitude+":"
                        +map.getCameraPosition().zoom);
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}
