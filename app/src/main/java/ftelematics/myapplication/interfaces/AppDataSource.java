package ftelematics.myapplication.interfaces;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import ftelematics.myapplication.models.MovieModel;
import ftelematics.myapplication.viewmodel.MovieViewModel;

public interface AppDataSource {

    interface HomeTaskCallbacks
    {
        LiveData<ArrayList<MovieViewModel>> getMovies();
        LiveData<ArrayList<MovieViewModel>> getTopMovies();
        void response(ArrayList<MovieViewModel> movieModel);
        void searchMovies(String query);
        void moviesLoadedFromDB(ArrayList<MovieViewModel> movieList);
    }

    void addMovies(List<MovieViewModel> movies);
    List<MovieViewModel> getMovies(AppDataSource.HomeTaskCallbacks homeTaskCallbacks);


}
