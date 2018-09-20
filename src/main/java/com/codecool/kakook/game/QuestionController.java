package com.codecool.kakook.game;

import com.codecool.kakook.util.FileReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
            List<Answer> answers = new ArrayList<>();
            answers.add(new Answer(file.get(i + 1)));
            answers.add(new Answer(file.get(i + 2)));
            answers.add(new Answer(file.get(i + 3)));
            answers.add(new Answer(file.get(i + 4)));
            String description = file.get(i);
            questions.add(new Question(description, answers.get(0), answers));
        }
    }

    public Deque<Question> getQuestions() {
        return questions;
    }

}
