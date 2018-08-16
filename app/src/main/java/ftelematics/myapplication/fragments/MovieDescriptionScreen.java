package ftelematics.myapplication.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ftelematics.myapplication.R;
import ftelematics.myapplication.databinding.MovieDescLayoutBinding;
import ftelematics.myapplication.viewmodel.MovieViewModel;

public class MovieDescriptionScreen extends Fragment {

    MovieDescLayoutBinding movieDescLayoutBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        movieDescLayoutBinding=DataBindingUtil.inflate(inflater, R.layout.movie_desc_layout, container, false);
        movieDescLayoutBinding.setMovieDescModel((MovieViewModel) getArguments().getParcelable("MovieModel"));
        return movieDescLayoutBinding.getRoot();
    }
}
