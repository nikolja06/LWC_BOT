package com.botcompany;


import java.util.ResourceBundle;

public class Answers {

    public static String getAnswer(String question){

        String response = "I don't have answer for your question";

        ResourceBundle bundle = ResourceBundle.getBundle("Messages");

        if (bundle.containsKey(question.replaceAll(" ", "_").toLowerCase().trim())){
            response = bundle.getString(question.replaceAll(" ", "_").toLowerCase().trim());
        }

        return response;
    }
}
