package ftelematics.myapplication.utils.db;


import android.support.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

import ftelematics.myapplication.interfaces.AppDataSource;
import ftelematics.myapplication.utils.AppExecutors;
import ftelematics.myapplication.viewmodel.MovieViewModel;

public class TaskLocalDataSource implements AppDataSource {

    private static volatile TaskLocalDataSource INSTANCE;

    private MovieInfoDao mTasksDao;
    private AppExecutors mAppExecutors;


    private TaskLocalDataSource(@NonNull AppExecutors appExecutors,
                                @NonNull MovieInfoDao tasksDao) {
        mAppExecutors = appExecutors;
        mTasksDao = tasksDao;
    }

    public static TaskLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                  @NonNull MovieInfoDao tasksDao) {
        if (INSTANCE == null) {
            synchronized (TaskLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaskLocalDataSource(appExecutors, tasksDao);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void addMovies(List<MovieViewModel> movies) {
        Runnable runnable = () -> {
            mTasksDao.deleteAll();
            mTasksDao.addMovie(movies);
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public List<MovieViewModel> getMovies(AppDataSource.HomeTaskCallbacks homeTaskCallbacks) {
        Runnable runnable = () -> {
            homeTaskCallbacks.moviesLoadedFromDB(new ArrayList<>(mTasksDao.getMovies()));
        };

        mAppExecutors.diskIO().execute(runnable);
        return null;
    }
}
