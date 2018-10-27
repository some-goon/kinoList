package com.bladejs.kinoList;

import info.talacha.filmweb.connection.FilmwebException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TournamentController {
    private ApiHandler handler;
    private List<PersonalFilm> films;

    private Scanner input;

    public TournamentController(){
        handler=new ApiHandler();
        input = new Scanner(System.in);
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
        while(this.films.size()>1){
            duel();
        }
        try {
            System.out.println(this.films.get(0).getTitle());
        }catch(NoTitleException e){
            System.err.println("A problem occurred while getting a film title");
        }
    }

    private void duel(){
        List<PersonalFilm> films=new ArrayList<>();
        for(int i=0; i<this.films.size(); i+=2){
            if(i+1>=this.films.size())
                films.add(this.films.get(i));
            else
                try {
                    films.add(askForChoice(this.films.get(i), this.films.get(i + 1)));
                }catch(NoTitleException e){
                    e.printStackTrace();
                }
        }
        this.films=films;
    }

    private PersonalFilm askForChoice(PersonalFilm a, PersonalFilm b) throws NoTitleException{
        System.out.println(a.getTitle()+" vs "+b.getTitle()+" (type 1 or 2).");
        return getChoiceFromUser(a,b);
    }

    private PersonalFilm getChoiceFromUser(PersonalFilm a, PersonalFilm b){
        switch(input.nextInt()){
            case 1:
                return a;
            case 2:
                break;
            default:
                System.out.println("Błedne dane, spróuj jeszcze raz.");
                return getChoiceFromUser(a,b);
        }
        return b;
    }
}
