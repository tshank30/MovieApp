package ftelematics.myapplication.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ftelematics.myapplication.R;
import ftelematics.myapplication.activities.MainActivity;
import ftelematics.myapplication.databinding.SplashLayoutBinding;
import ftelematics.myapplication.viewmodel.SplashScreenViewModel;

public class SplashScreen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        SplashLayoutBinding splashLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.splash_layout, container, false);

        SplashScreenViewModel viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel.class);
        viewModel.openHomeScreen().observe(this,new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean bool) {
                HomeScreen homeScreen = new HomeScreen();
                ((MainActivity)getActivity()).unHideActionBar();
                ((MainActivity)getActivity()).changeFragment(homeScreen, false);
            }
        });

        viewModel.openHome();

        return splashLayoutBinding.getRoot();
    }
}
