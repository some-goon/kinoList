package com.bladejs.kinoList;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Vote;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

class PersonalFilm implements Comparable<PersonalFilm>{
    private Film film;
    private Vote vote;

    private String title;
    private int userRating;
    private int year;
    private URL coverURL;
    private Image cover;
    private boolean gotUserRating;
    private boolean gotYear;
    private boolean gotCoverURL;
    private boolean gotCover;

    PersonalFilm(Vote vote, Film film){
        this.film=film;
        this.vote=vote;
        gotUserRating=false;
    }

    private Film getFilm(){
      return film;
    }

    private Vote getVote(){
        return vote;
    }

    String getTitle() throws NoTitleException {
        if(title==null)
            return title=ApiHandler.getFilmTitle(film);
        else
            return title;
    }

    int getUserRating(){
        if(!gotUserRating) {
            gotUserRating = true;
            return userRating = ApiHandler.getUserRating(vote);
        }
        else
            return userRating;
    }

    Integer getYear(){
        if(!gotYear) {
            gotYear = true;
            return year = ApiHandler.getFilmYear(film);
        }
        else
            return year;
    }

    URL getCoverURL(){
        if(!gotCoverURL) {
            gotCoverURL = true;
            return coverURL = ApiHandler.getFilmCover(film);
        }
        else
            return coverURL;
    }

    Image getCover(){
        if(!gotCover) {
            try {
                gotCover = true;
                return cover = ImageIO.read(getCoverURL());
            }catch(IOException e){
                gotCover=false;
                e.printStackTrace();
                return null;
            }
        }else
            return cover;
    }


    @Override
    public int compareTo(PersonalFilm pfilm){
        return -Integer.compare(vote.getRate(),pfilm.getVote().getRate());
    }

}
