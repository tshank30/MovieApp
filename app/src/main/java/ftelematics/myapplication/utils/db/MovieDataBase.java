package ftelematics.myapplication.utils.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ftelematics.myapplication.viewmodel.MovieViewModel;


@Database(entities = {MovieViewModel.class}, version = 1, exportSchema = false)
public abstract class MovieDataBase extends RoomDatabase {

    private static MovieDataBase INSTANCE;

    public abstract MovieInfoDao appInfoDao();

    private static final Object sLock = new Object();

    public static MovieDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDataBase.class, "movies.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}