package pando.iMobile.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import pando.iMobile.R;

public class SettingsFragment extends Fragment {


    private View parentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
    parentView = inflater.inflate(R.layout.activity_main , container , false);



    return parentView;

        }
}
