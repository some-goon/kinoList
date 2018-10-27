package com.bladejs.kinoList.test;

import com.bladejs.kinoList.TournamentController;
import info.talacha.filmweb.connection.FilmwebException;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        boolean check=true;
        Scanner input=new Scanner(System.in);
        TournamentController tournament = new TournamentController();
        while(check) {
            try {
                tournament.login(input.nextLine(), input.nextLine());
                check=false;
            } catch (FilmwebException e) {
                if (e.getCode() == 20)
                    System.err.println("Błędne dane, spróbuj ponownie");
                else {
                    System.err.println("Nie można zalogować");
                    System.exit(2);
                }
            }
        }
        tournament.prepare();
        tournament.startTournament();
    }
}
