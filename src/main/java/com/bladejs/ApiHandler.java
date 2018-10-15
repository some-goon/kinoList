package com.bladejs;

import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.connection.FilmwebException;
import info.talacha.filmweb.models.*;

import java.util.*;

public class ApiHandler {
    private static FilmwebApi api = new FilmwebApi();
    private List<PersonalFilm> films=new ArrayList<>();
    private User user;

    void login(String login, String password) throws FilmwebException {
        System.out.println("Logowanie...");
        user = api.login(login,password);
    }

    void getMovies() throws FilmwebException {
        System.out.println("Pobieranie ocen...");
        List<Vote> votes = api.getUserVotes(user.getId(), 0, 20);
        System.out.println("Filtrowanie ocen...");
        for (Vote vote : votes) {
            if(vote.getType()==ItemType.FILM && (vote.getRate()>8 || vote.isFavourite())){
                films.add(new PersonalFilm(vote,api.getFilmData(vote.getItemId())));
                //System.out.println(" "+api.getFilmData(vote.getItemId()).getTitle() + " // " + api.getFilmData(vote.getItemId()).getPolishTitle() + " - " + vote.getRate());
            }
        }
    }
    List<PersonalFilm> getFilms(){
        return films;
    }

}
