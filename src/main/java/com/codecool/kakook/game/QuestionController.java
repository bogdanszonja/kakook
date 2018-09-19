package com.codecool.kakook.game;

import com.codecool.kakook.util.FileReader;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class QuestionController {

    private Deque<Question> questions = new ArrayDeque<>();

    private static QuestionController instance = new QuestionController();

    private QuestionController() {

    }

    public static QuestionController getInstance() {
        return instance;
    }

    void createQuestions() {
        List<String> file = FileReader.readFile();
        for (int i = 0; i < file.size(); i += 5) {
            questions.add(new Question(file.get(i), file.get(i + 1), file.subList(i + 2, i + 5)));
        }
    }

    public Deque<Question> getQuestions() {
        return questions;
    }

}
