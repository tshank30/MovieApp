package ftelematics.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ftelematics.myapplication.databinding.MovieItemBinding;
import ftelematics.myapplication.interfaces.HomeCallBacks;
import ftelematics.myapplication.viewmodel.MovieViewModel;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeCallBacks homeCallBacks;
    private ArrayList<MovieViewModel> movieList;
    private LayoutInflater layoutInflater;

    public HomeAdapter(HomeCallBacks homeCallBacks) {
        this.homeCallBacks = homeCallBacks;
    }

    public ArrayList<MovieViewModel> getInitialList() {
        return movieList;
    }

    public void setList(ArrayList<MovieViewModel> movieList) {
        this.movieList = movieList;
    }


    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /*public MovieHolder(@NonNull View itemView) {
            super(itemView);
        }*/

        private MovieItemBinding applicationBinding;


        public MovieHolder(MovieItemBinding applicationBinding) {
            super(applicationBinding.getRoot());
            this.applicationBinding = applicationBinding;
            applicationBinding.movieItem.setOnClickListener(this);
        }

        public MovieItemBinding getApplicationBinding() {
            return applicationBinding;
        }

        public void bind(MovieViewModel movieViewModel) {
            applicationBinding.setMovieModel(movieViewModel);
            //applicationBinding.setCallBack(ReviewAdapter.this);
        }

        @Override
        public void onClick(View v) {
            homeCallBacks.openDescription(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //return null;
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(viewGroup.getContext());

        MovieItemBinding applicationBinding = MovieItemBinding.inflate(layoutInflater, viewGroup, false);
        return new MovieHolder(applicationBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MovieViewModel appInfo = movieList.get(i);
        ((MovieHolder) viewHolder).bind(appInfo);
    }

    @Override
    public int getItemCount() {
        if (movieList != null)
            return movieList.size();
        else
            return 0;
    }
}
