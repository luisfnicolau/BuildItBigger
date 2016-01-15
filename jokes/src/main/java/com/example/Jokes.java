package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Jokes {

    public static String getJoke() {
        ArrayList<String> jokes = new ArrayList<>();
        jokes.add("Joke 1");
        jokes.add("Joke 2");
        jokes.add("Joke 3");
        return jokes.get(new Random().nextInt(3));
    }
}
