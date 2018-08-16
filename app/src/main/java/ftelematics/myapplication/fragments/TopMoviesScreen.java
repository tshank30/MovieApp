package ftelematics.myapplication.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ftelematics.myapplication.R;
import ftelematics.myapplication.activities.MainActivity;
import ftelematics.myapplication.adapters.HomeAdapter;
import ftelematics.myapplication.databinding.HomeLayoutBinding;
import ftelematics.myapplication.interfaces.HomeCallBacks;
import ftelematics.myapplication.viewmodel.HomeScreenViewModel;
import ftelematics.myapplication.viewmodel.MovieViewModel;

public class TopMoviesScreen extends Fragment implements HomeCallBacks {

    private HomeAdapter homeAdapter;
    private HomeLayoutBinding homeLayoutBinding;
    private HomeScreenViewModel homeScreenViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVM();
        start();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        homeLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.home_layout, container, false);
        initView();
        return homeLayoutBinding.getRoot();
    }

    void initView() {
        homeAdapter = new HomeAdapter(this);
        homeAdapter.setList(homeScreenViewModel.getMovieList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        homeLayoutBinding.appRecyclerView.setLayoutManager(gridLayoutManager);
        homeLayoutBinding.appRecyclerView.setAdapter(homeAdapter);

    }

    void initVM() {
        homeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
    }

    void start() {
        homeScreenViewModel.getTopMovies().observe(getActivity(), new Observer<ArrayList<MovieViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MovieViewModel> movies) {
                homeAdapter.setList(movies);
                homeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void search(String query) {
        homeScreenViewModel.searchMovies(query);
    }

    @Override
    public void openDescription(int position) {
        MovieDescriptionScreen movieDescriptionScreen = new MovieDescriptionScreen();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MovieModel", homeAdapter.getInitialList().get(position));
        movieDescriptionScreen.setArguments(bundle);
        ((MainActivity) getActivity()).changeFragment(movieDescriptionScreen, true);
    }
}