package ftelematics.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ftelematics.myapplication.repositories.HomeRepository;
import ftelematics.myapplication.interfaces.AppDataSource;

public class HomeScreenViewModel extends AndroidViewModel implements AppDataSource.HomeTaskCallbacks {

    private HomeRepository homeRepository;
    private MutableLiveData<ArrayList<MovieViewModel>> movieListLiveData;
    private ArrayList<MovieViewModel> movieList;

    public HomeScreenViewModel(@NonNull Application application) {
        super(application);
        homeRepository = new HomeRepository(application, this);
        movieList=new ArrayList<>();
    }


    @Override
    public LiveData<ArrayList<MovieViewModel>> getMovies() {
        movieListLiveData = new MutableLiveData<>();
        homeRepository.getMoviesFromDB();
        homeRepository.getMovies();
        return movieListLiveData;
    }

    @Override
    public LiveData<ArrayList<MovieViewModel>> getTopMovies() {
        movieListLiveData = new MutableLiveData<>();
        homeRepository.getTopMovies();
        return movieListLiveData;
    }

    @Override
    public void response(ArrayList<MovieViewModel> movieModel) {
        this.movieListLiveData.postValue(movieModel);
        movieList.clear();
        movieList=movieModel;
    }

    @Override
    public void searchMovies(String query) {
        homeRepository.searchMovies(query);
    }

    @Override
    public void moviesLoadedFromDB(ArrayList<MovieViewModel> movieList) {
        this.movieListLiveData.postValue(movieList);
        this.movieList.clear();
        this.movieList=movieList;
    }


    public ArrayList<MovieViewModel> getMovieList()
    {
        return movieList;
    }

}
