package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Jokes {

    public static String getJoke() {
        ArrayList<String> jokes = new ArrayList<>();
        jokes.add("A local movie theatre was robbed of $600 worth of merchandise \n" +
                "The suspects stole 3 medium popcorns, 1 bag of skittles and 4 small diet cokes.");
        jokes.add("I beat a black belt at karate.\n" + "My next challenger is a green sock.");
        jokes.add("A man is dying of cancer... \n" + "He tells his wife, Honey, if things start looking bad, please just turn off my life support.\"\n" +
                "A tear rolls down her cheek as he grasps her hand and continues, Then turn it back on again and see if that fixes it.");
        jokes.add("Broken pencils...\n" + "Are pointless.");
        jokes.add("The past, present, and future walk into a bar.\n It was tense");
        return jokes.get(new Random().nextInt(5));
    }
}
