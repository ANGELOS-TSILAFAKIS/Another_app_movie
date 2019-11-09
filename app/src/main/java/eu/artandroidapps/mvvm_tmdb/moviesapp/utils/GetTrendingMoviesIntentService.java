package eu.artandroidapps.mvvm_tmdb.moviesapp.utils;

import android.app.IntentService;
import android.content.Intent;

public class GetTrendingMoviesIntentService extends IntentService {

    public GetTrendingMoviesIntentService() {
        super("GetTrendingMoviesIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        GetTrendingMoviesTask.executeTask(getApplication(),action);
    }
}
