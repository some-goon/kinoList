package com.bladejs.kinoList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TournamentController {
    private List<PersonalFilm> films;

    private Scanner input;

    TournamentController(List<PersonalFilm> films){
        input = new Scanner(System.in);
        this.films=films;
        while(this.films.size()>1){
            startTournament();
        }
        try {
            System.out.println(this.films.get(0).getTitle());
        }catch(NoTitleException e){
            System.err.println("A problem occurred while getting a film title");
        }
    }

    private void startTournament(){
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
