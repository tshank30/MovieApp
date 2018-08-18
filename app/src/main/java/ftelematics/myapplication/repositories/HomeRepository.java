package ftelematics.myapplication.repositories;

import android.app.Application;


import java.util.ArrayList;
import java.util.List;

import ftelematics.myapplication.interfaces.AppDataSource;
import ftelematics.myapplication.interfaces.HomeTasks;
import ftelematics.myapplication.models.MovieModel;
import ftelematics.myapplication.utils.APIService;
import ftelematics.myapplication.utils.AppExecutors;
import ftelematics.myapplication.utils.Constants;
import ftelematics.myapplication.utils.Log;
import ftelematics.myapplication.utils.RetrofitClient;
import ftelematics.myapplication.utils.db.MovieDataBase;
import ftelematics.myapplication.utils.db.TaskLocalDataSource;
import ftelematics.myapplication.viewmodel.MovieViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ftelematics.myapplication.utils.Constants.BASE_URL;

public class HomeRepository implements HomeTasks {

    private TaskLocalDataSource taskLocalDataSource;
    private AppDataSource.HomeTaskCallbacks homeTaskCallbacks;

    public HomeRepository(Application application, AppDataSource.HomeTaskCallbacks homeTaskCallbacks) {
        taskLocalDataSource = TaskLocalDataSource.getInstance(new AppExecutors(),
                MovieDataBase.getInstance(application).appInfoDao());
        this.homeTaskCallbacks = homeTaskCallbacks;
    }


    @Override
    public void getMoviesFromDB() {
        taskLocalDataSource.getMovies(homeTaskCallbacks);
    }

    @Override
    public void getMovies() {
        APIService apiInterface = RetrofitClient.getClient(BASE_URL).create(APIService.class);
        Call<MovieModel> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {


                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                MovieModel resource = response.body();
                List<MovieViewModel> datumList = resource.getResults();

                for (MovieViewModel datum : datumList) {
                    displayResponse += datum.getOriginalTitle() + " " + datum.getReleaseDate() + " " + datum.getVoteAverage() + " " + datum.getPosterPath() + "\n";
                }

                Log.d("SearchResponse", displayResponse);


                //responseText.setText(displayResponse);

                ArrayList movieList = new ArrayList<>(datumList);
                taskLocalDataSource.addMovies(movieList);
                homeTaskCallbacks.response(movieList);

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                call.cancel();
            }


        });
    }

    @Override
    public void searchMovies(String query) {
        APIService apiInterface = RetrofitClient.getClient(BASE_URL).create(APIService.class);
        Call<MovieModel> call = apiInterface.searchMovie(Constants.SEARCH_URL + query);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {


                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                MovieModel resource = response.body();
                List<MovieViewModel> datumList = resource.getResults();

                for (MovieViewModel datum : datumList) {
                    displayResponse += datum.getOriginalTitle() + " " + datum.getReleaseDate() + " " + datum.getVoteAverage() + " " + datum.getPosterPath() + "\n";
                }

                Log.d("SearchResponse", displayResponse);


                //responseText.setText(displayResponse);

                homeTaskCallbacks.response(new ArrayList<>(datumList));

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                call.cancel();
            }


        });
    }

    @Override
    public void getTopMovies() {
        APIService apiInterface = RetrofitClient.getClient(BASE_URL).create(APIService.class);
        Call<MovieModel> call = apiInterface.getTopMovies();
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {


                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                MovieModel resource = response.body();
                List<MovieViewModel> datumList = resource.getResults();

                for (MovieViewModel datum : datumList) {
                    displayResponse += datum.getOriginalTitle() + " " + datum.getReleaseDate() + " " + datum.getVoteAverage() + " " + datum.getPosterPath() + "\n";
                }

                Log.e("TopMoviesResponse", displayResponse);


                //responseText.setText(displayResponse);

                ArrayList movieList = new ArrayList<>(datumList);
                homeTaskCallbacks.response(movieList);

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                call.cancel();
            }


        });
    }

}
