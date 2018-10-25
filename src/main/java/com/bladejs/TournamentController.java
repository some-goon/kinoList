package com.bladejs;

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
        System.out.println(this.films.get(0).getFilm().getTitle());
    }

    private void startTournament(){
        List<PersonalFilm> films=new ArrayList<>();
        for(int i=0; i<this.films.size(); i+=2){
            if(i+1>=this.films.size())
                films.add(this.films.get(i));
            else
                films.add(askForChoice(this.films.get(i), this.films.get(i+1)));
        }
        this.films=films;
    }

    private PersonalFilm askForChoice(PersonalFilm a, PersonalFilm b){
        String titleA, titleB;
        String temp;
        if((temp=a.getFilm().getTitle())!=null)
            titleA=temp;
        else
            titleA=a.getFilm().getPolishTitle();
        if((temp=b.getFilm().getTitle())!=null)
            titleB=temp;
        else
            titleB=b.getFilm().getPolishTitle();
        System.out.println(titleA+" vs "+titleB+" (type 1 or 2).");
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
