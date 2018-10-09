package com.bladejs;

public class FilmNode {
    private PersonalFilm first, second;
    private FilmNode child, parent;

    FilmNode(PersonalFilm a, PersonalFilm b){
        first=a;
        second=b;
    }

    FilmNode(PersonalFilm a){
        first=a;
    }

    public PersonalFilm getFirst() {
        return first;
    }

    public PersonalFilm getSecond() {
        return second;
    }

    public FilmNode getChild() {
        return child;
    }

    public void setChild(FilmNode child) {
        this.child = child;
    }

    public FilmNode getParent() {
        return parent;
    }

    public void setParent(FilmNode parent) {
        this.parent = parent;
    }
}
