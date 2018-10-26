package com.bladejs.kinoList;

public class GetMoviesException extends Exception {
    public GetMoviesException(){
        super("Downloading user's scores failed.");
    }
}
