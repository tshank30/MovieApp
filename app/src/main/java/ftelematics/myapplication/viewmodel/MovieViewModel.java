package ftelematics.myapplication.viewmodel;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.List;

@Entity(tableName = "Movies")
public class MovieViewModel extends BaseObservable implements Parcelable{

    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;
    @SerializedName("popularity")
    @Expose
    @ColumnInfo(name = "popularity")
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "posterPath")
    private String posterPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    /*@SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;*/
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "releaseDate")
    private String releaseDate;

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @BindingAdapter("bind:movieImage")
    public static void loadImage(ImageView view, String posterPath) {
        Glide.with(view.getContext()).load("https://image.tmdb.org/t/p/w185"+ posterPath).into(view);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public MovieViewModel()
    {

    }

    protected MovieViewModel(Parcel in) {
        voteCount=in.readInt();
        id=in.readInt();
        voteAverage=in.readDouble();
        title=in.readString();
        popularity=in.readDouble();
        posterPath=in.readString();
        originalLanguage=in.readString();
        originalTitle=in.readString();
        backdropPath=in.readString();
        overview=in.readString();
        releaseDate=in.readString();
        adult = in.readByte() != 0x00;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(voteCount);
        dest.writeInt(id);
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(backdropPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeByte((byte) (adult ? 0x01 : 0x00));
    }

    public static final Parcelable.Creator<MovieViewModel> CREATOR = new Parcelable.Creator<MovieViewModel>() {
        @Override
        public MovieViewModel createFromParcel(Parcel in) {
            return new MovieViewModel(in);
        }

        @Override
        public MovieViewModel[] newArray(int size) {
            return new MovieViewModel[size];
        }
    };
}

