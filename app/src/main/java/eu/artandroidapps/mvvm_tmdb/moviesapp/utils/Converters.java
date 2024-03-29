package eu.artandroidapps.mvvm_tmdb.moviesapp.utils;

import android.arch.persistence.room.TypeConverter;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Genres;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import eu.artandroidapps.mvvm_tmdb.moviesapp.api.model.Genres;

public class Converters {
    @TypeConverter
    public List<Integer> gettingListFromString(String genreIds) {
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        return new Gson().fromJson(genreIds, listType);
    }

    @TypeConverter
    public List<Genres> gettingGenresListFromString(String genresString) {
        Type listType = new TypeToken<List<Genres>>() {}.getType();
        return new Gson().fromJson(genresString, listType);
    }

    @TypeConverter
    public String writingStringFromGenresList(List<Genres> genres) {
        Gson gson = new Gson();
        String json = gson.toJson(genres);
        return json;
    }

    @TypeConverter
    public String writingStringFromList(List<Integer> list) {

        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
