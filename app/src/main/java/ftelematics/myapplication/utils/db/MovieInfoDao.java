package ftelematics.myapplication.utils.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ftelematics.myapplication.models.MovieModel;
import ftelematics.myapplication.viewmodel.MovieViewModel;


@Dao
public interface MovieInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMovie(List<MovieViewModel> movies);

    @Query("DELETE FROM Movies")
    void deleteAll();

    @Query("SELECT  * FROM Movies ORDER BY popularity DESC")
    List<MovieViewModel> getMovies();

    /*@Query("SELECT PkgName FROM AppDetails")
    List<String> getPackageNames();*/
}
