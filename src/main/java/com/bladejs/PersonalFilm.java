package com.bladejs;

import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.models.Vote;

public class PersonalFilm implements Comparable<PersonalFilm>{
    private Film f;
    private Vote v;

    PersonalFilm(Vote v, Film f){
        this.f=f;
        this.v=v;
    }

    Film getF(){
      return f;
    }

    Vote getV(){
        return v;
    }

    public int compareTo(PersonalFilm pfilm){
        return -Integer.compare(v.getRate(),pfilm.getV().getRate());
    }

}
