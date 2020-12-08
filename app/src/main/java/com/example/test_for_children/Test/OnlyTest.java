package com.example.test_for_children.Test;

public class OnlyTest {

    private String question;
    private String[] answers;
    private int isAnswer;
    private int answerForUser;
    public static final int COUNT_ANSWER = 4;

    public OnlyTest() {
        this.question = "";
        this.answers = emptyAnswers();
        this.isAnswer = -1;
        this.answerForUser = -1;
    }

    public OnlyTest(String question, String[] answers, int isAnswer) {
        this.question = question;
        this.answers = answers;
        this.isAnswer = isAnswer;
        this.answerForUser = -1;
    }

    public static String[] emptyAnswers(){
        String[] answers = new String[COUNT_ANSWER];
        answers[0] = "";
        answers[1] = "";
        answers[2] = "";
        answers[3] = "";
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(int isAnswer) {
        this.isAnswer = isAnswer;
    }

    public int getAnswerForUser() {
        return answerForUser;
    }

    public void setAnswerForUser(int answerForUser) {
        this.answerForUser = answerForUser;
    }
}
