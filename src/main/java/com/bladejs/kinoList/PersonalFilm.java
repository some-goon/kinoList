package com.bladejs.kinoList;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Vote;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PersonalFilm implements Comparable<PersonalFilm>{
    private Film film;
    private Vote vote;

    private String title;
    private int userRating;
    private int year;
    private Image cover;
    private List<PersonalFilm> betterThan;

    private boolean gotUserRating;
    private boolean gotYear;
    private boolean gotCover;

    PersonalFilm(Vote vote, Film film){
        this.film=film;
        this.vote=vote;
        gotUserRating=false;
        betterThan=new ArrayList<>();
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

    Image getCover(){
        if(!gotCover) {
            try {
                gotCover = true;
                return cover = ImageIO.read(ApiHandler.getFilmCover(this.film));
            }catch(IOException e){
                gotCover=false;
                e.printStackTrace();
                return null;
            }
        }else
            return cover;
    }

    void winOver(PersonalFilm film){
        betterThan.add(film);
        betterThan.addAll(film.getBetterThan());
    }

    boolean isBetterThan(PersonalFilm film){
        return betterThan.contains(film);
    }

    private List<PersonalFilm> getBetterThan(){
        return betterThan;
    }

    @Override
    public int compareTo(PersonalFilm pfilm){
        return -Integer.compare(vote.getRate(),pfilm.getVote().getRate());
    }

}
