package com.bladejs.kinoList;

import info.talacha.filmweb.models.Film;

class NoTitleException extends Exception{
    NoTitleException(Film film){
        super("No title found for film "+film);
    }
}
