package eu.artandroidapps.mvvm_tmdb.moviesapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Genres;

import java.util.List;

@Dao
public interface GenresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Genres genre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Genres> genres);

    @Query("SELECT * FROM genres_table")
    LiveData<List<Genres>> getAllGenres();
}

