package com.example.unome;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultQuestions {

    public HashMap<String, ArrayList<String>> getDefaultQsAs(){
        HashMap<String, ArrayList<String>> defaultQuestions = new HashMap<String, ArrayList<String>>();
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<ArrayList<String>> answers = new ArrayList<>();

        questions.add("Which drink do I prefer?");
        ArrayList<String> a1 = new ArrayList<String>();
        a1.add("Tequila");
        a1.add("Scotch");
        a1.add("Vodka");
        a1.add("Whisky");
        answers.add(a1);

        questions.add("Which sport do I watch?");
        ArrayList<String> a2 = new ArrayList<String>();
        a1.add("Soccer");
        a1.add("Basketball");
        a1.add("Baseball");
        a1.add("Hockey");
        answers.add(a2);

        questions.add("What is my favorite color?");
        ArrayList<String> a3 = new ArrayList<String>();
        a1.add("Blue");
        a1.add("Green");
        a1.add("Yellow");
        a1.add("White");
        answers.add(a3);

        for(int i=0; i<3; i++){
            defaultQuestions.put(questions.get(i), answers.get(i));
        }

        return  defaultQuestions;
    }

}
