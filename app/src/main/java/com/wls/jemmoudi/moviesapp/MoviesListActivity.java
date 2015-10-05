package com.wls.jemmoudi.moviesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wls.jemmoudi.moviesapp.adapter.FilmAdapter;
import com.wls.jemmoudi.moviesapp.model.Film;
import com.wls.jemmoudi.moviesapp.service.FilmServiceInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

public class MoviesListActivity extends AppCompatActivity {

    private ListView moviesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        moviesListView = (ListView) findViewById( R.id.MoviesListView );

        new FilmsTask().execute();
        bindEvent();

    }

    private class FilmsTask extends AsyncTask<Void, Void, List<Film>> {
        private ProgressDialog pDialog;
        private List<Film> collection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MoviesListActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();

            collection = new ArrayList<Film>();
        }

        @Override
        protected List<Film> doInBackground(Void... params) {

            FilmServiceInterface filmService = new RestAdapter.Builder()
                    .setEndpoint( FilmServiceInterface.ENDPOINT )
                    .build()
                    .create(FilmServiceInterface.class);
            collection = filmService.getAllFilms();
            return collection;
        }

        @Override
        protected void onPostExecute(List<Film> films) {
            // Dismiss the progress dialog
            if (pDialog.isShowing()) pDialog.dismiss();

            // Populate listView
            FilmAdapter adapter = new FilmAdapter(getBaseContext(), R.layout.list_item, collection);
            moviesListView.setAdapter( adapter );
        }
    }

    private void bindEvent () {
        this.moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int filmId = ++position;
                Intent intent = new Intent(getApplicationContext(), ShowMovieActivity.class);
                intent.putExtra("filmId", filmId);
                startActivity( intent );
            }
        });
    }
}
