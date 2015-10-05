package com.wls.jemmoudi.moviesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wls.jemmoudi.moviesapp.R;
import com.wls.jemmoudi.moviesapp.model.Film;
import com.wls.jemmoudi.moviesapp.service.FilmServiceInterface;

import java.util.List;

public class FilmAdapter extends ArrayAdapter<Film> {

    private Context context;

    public FilmAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public FilmAdapter(Context context, int resource, List<Film> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item, null);
        }

        Film item = this.getItem(position);

        if ( item != null ) {

            TextView titleTV  = (TextView) v.findViewById( R.id.movieTitle );
            TextView typeTV   = (TextView) v.findViewById( R.id.movieType );
            ImageView thumbIV = (ImageView) v.findViewById( R.id.thumbnail );

            if ( titleTV != null ) {
                String title = ( item.getTitle().length() > 25 ) ? item.getTitle().substring(0, 24) : item.getTitle() ;
                titleTV.setText( title );
            }

            if ( typeTV != null ) typeTV.setText( item.getType() );

            if ( thumbIV != null ) {
                Picasso.with( this.getContext() )
                        .load( FilmServiceInterface.ENDPOINT + item.getPicture() )
                        .into( thumbIV );
            }

        }

        return v;
    }

}
