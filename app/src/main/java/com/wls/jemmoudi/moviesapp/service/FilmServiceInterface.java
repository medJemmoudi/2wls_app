package com.wls.jemmoudi.moviesapp.service;

import com.wls.jemmoudi.moviesapp.model.Film;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

public interface FilmServiceInterface {

    public static final String ENDPOINT = "http://pmt.16mb.com/2wls";

    /**
     * it returns a collection of all movies
     * @return List<Film>
     */
    @GET("/films")
    public List<Film> getAllFilms();

    /**
     * it returns a movie using specified title
     * @param String title
     * @return Film
     */
    @GET("/films/{id}")
    public Film getFilmById( @Path("id") int id );

}
