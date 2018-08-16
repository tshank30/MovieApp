package ftelematics.myapplication.utils;

/**
 * Created by shashanktiwari on 20/02/18.
 */

import ftelematics.myapplication.models.MovieModel;
import ftelematics.myapplication.viewmodel.MovieViewModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIService {
    @GET("discover/movie?sort_by=popularity.desc&api_key=2a32acef5fce5ebb3b66cecfe4e89484")
    Call<MovieModel> doGetListResources();

    @GET
    Call<MovieModel> searchMovie(@Url String url);

    @GET("discover/movie?with_genres=18&sort_by=vote_average.desc&vote_count.gte=10&api_key=2a32acef5fce5ebb3b66cecfe4e89484")
    Call<MovieModel> getTopMovies();

}