package com.bladejs;

import java.util.ArrayList;
import java.util.List;

public class TournamentController {
    ArrayList<FilmNode>

    void groupIntoNodes(List<PersonalFilm> things){
        for(int i=0; i<things.size(); i+=2){
            if(i+1<things.size()){
                new FilmNode(things.get(i), things.get(i+1));
            }
        }
    }

}
