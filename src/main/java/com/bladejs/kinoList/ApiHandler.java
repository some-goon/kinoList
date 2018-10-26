package com.bladejs.kinoList;

import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.connection.FilmwebException;
import info.talacha.filmweb.models.*;

import java.util.*;

public class ApiHandler {
    private static FilmwebApi api;
    private List<PersonalFilm> films;
    private User user;

    ApiHandler(){
        api = new FilmwebApi();
        films = new ArrayList<>();
    }

    void login(String login, String password) throws FilmwebException {
        System.out.println("Logowanie...");
        user = api.login(login,password);
    }

    void getMovies() throws FilmwebException {
        System.out.println("Pobieranie ocen...");
        List<Vote> votes = api.getUserVotes(user.getId(), 0, 20);
        System.out.println("Filtrowanie ocen...");
        int i=0;
        for (Vote vote : votes) {
            //if(vote.getType()==ItemType.FILM && (vote.getRate()>8 || vote.isFavourite())){
            if(vote.getType()==ItemType.FILM && i<4){
                films.add(new PersonalFilm(vote,api.getFilmData(vote.getItemId())));
                i++;
                //System.out.println(" "+api.getFilmData(vote.getItemId()).getTitle() + " // " + api.getFilmData(vote.getItemId()).getPolishTitle() + " - " + vote.getRate());
            }
        }
    }
    List<PersonalFilm> getFilms(){
        return films;
    }

    static String getFilmTitle(Film film) throws NoTitleException{
        if(film.getTitle()!=null)
            return film.getTitle();
        else
            if(film.getPolishTitle()!=null)
                return film.getPolishTitle();
        else
            throw new NoTitleException(film);
    }

    static int getUserRating(Vote vote){
        return vote.getRate();
    }

}
