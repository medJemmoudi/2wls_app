package com.wls.jemmoudi.moviesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wls.jemmoudi.moviesapp.model.Film;
import com.wls.jemmoudi.moviesapp.service.FilmServiceInterface;

import retrofit.RestAdapter;


public class ShowMovieActivity extends AppCompatActivity {

    private int filmId;

    // UI Components
    private ImageView cover;
    private TextView  title;
    private TextView  rating;
    private TextView  synopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);

        Intent intent = getIntent();
        this.filmId = intent.getIntExtra("filmId", 1);

        this.cover    = (ImageView) findViewById( R.id.cover );
        this.title    = (TextView) findViewById( R.id.singleTitle );
        this.rating   = (TextView) findViewById( R.id.rating );
        this.synopsis = (TextView) findViewById( R.id.synopsis );

        new LoadFilmTask().execute();
    }

    private class LoadFilmTask extends AsyncTask<Void, Void, Film> {

        private ProgressDialog pDialog;
        private Film loadedMovie;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShowMovieActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();

            loadedMovie = null;
        }

        @Override
        protected Film doInBackground ( Void... params ) {
            FilmServiceInterface filmService = new RestAdapter
                    .Builder()
                    .setEndpoint(FilmServiceInterface.ENDPOINT)
                    .build()
                    .create(FilmServiceInterface.class);

            this.loadedMovie = filmService.getFilmById( filmId );

            return loadedMovie;
        }

        @Override
        protected void onPostExecute(Film film) {
            // Dismiss the progress dialog
            if (pDialog.isShowing()) pDialog.dismiss();

            // Render the view
            if ( loadedMovie != null ) {

                title.setText( loadedMovie.getTitle() );
                rating.setText( loadedMovie.getRating() );
                synopsis.setText( loadedMovie.getSynopsis() );

                Picasso.with( getBaseContext() )
                        .load( FilmServiceInterface.ENDPOINT + loadedMovie.getPicture() )
                        .into( cover );

            }
        }
    }
}
