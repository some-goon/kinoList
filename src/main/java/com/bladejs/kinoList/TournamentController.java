package com.bladejs.kinoList;

import info.talacha.filmweb.connection.FilmwebException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TournamentController {
    private ApiHandler handler;
    private List<PersonalFilm> films;
    private List<PersonalFilm> finalFilms;
    private List<List<PersonalFilm>> TournamentLists;

    private Scanner input;

    public TournamentController(){
        handler=new ApiHandler();
        input = new Scanner(System.in);
        finalFilms=new ArrayList<>();
        TournamentLists=new ArrayList<>();
    }

    public void login(String login, String password) throws FilmwebException{
            handler.login(login, password);
    }

    public void prepare(){
        try {
            handler.downloadFilmList();
            this.films=handler.getFilms();
        }catch(FilmwebException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void startTournament(){
        while(films.size()>1){
            duel();
        }
        finalFilms.addAll(films);
        films.clear();
        int size=TournamentLists.size();
        if(size>0){
            films.addAll(TournamentLists.get(size - 1));
            TournamentLists.remove(size - 1);
            if(size>1){
                films.addAll(TournamentLists.get(size - 2));
                TournamentLists.remove(size-2);
            }
            startTournament();
        }else{
            for (PersonalFilm film: finalFilms) {
                try {
                    System.out.println(film.getTitle());
                }catch(NoTitleException e){
                    System.err.println("A problem occurred while getting a film title");
                    e.printStackTrace();
                }
            }
        }
    }

    private void duel(){
        PersonalFilm temp;
        List<PersonalFilm> films=new ArrayList<>();
        for(int i=0; i<this.films.size(); i+=2){
            if(i+1>=this.films.size())
                films.add(this.films.get(i));
            else
                try {
                    temp=askForChoice(this.films.get(i), this.films.get(i + 1));
                    films.add(temp);
                }catch(NoTitleException e){
                    e.printStackTrace();
                }
        }
        this.films.removeAll(films);
        List<PersonalFilm> old = new ArrayList<>(this.films);
        this.films=films;
        TournamentLists.add(old);
    }

    private PersonalFilm askForChoice(PersonalFilm a, PersonalFilm b) throws NoTitleException{
        if(a.isBetterThan(b))
            return a;
        else
            if(b.isBetterThan(a))
                return b;
            else {
                System.out.println(a.getTitle() + " vs " + b.getTitle() + " (type 1 or 2).");
                return getChoiceFromUser(a, b);
            }
    }

    private PersonalFilm getChoiceFromUser(PersonalFilm a, PersonalFilm b){
        switch(input.nextInt()){
            case 1:
                a.winOver(b);
                return a;
            case 2:
                b.winOver(a);
                break;
            default:
                System.out.println("Błedne dane, spróuj jeszcze raz.");
                return getChoiceFromUser(a,b);
        }
        return b;
    }
}
