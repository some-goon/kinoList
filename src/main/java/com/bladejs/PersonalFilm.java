package com.bladejs;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Vote;

public class PersonalFilm implements Comparable<PersonalFilm>{
    private Film film;
    private Vote vote;

    PersonalFilm(Vote vote, Film film){
        this.film=film;
        this.vote=vote;
    }

    Film getFilm(){
      return film;
    }

    private Vote getVote(){
        return vote;
    }

    public int compareTo(PersonalFilm pfilm){
        return -Integer.compare(vote.getRate(),pfilm.getVote().getRate());
    }

}
