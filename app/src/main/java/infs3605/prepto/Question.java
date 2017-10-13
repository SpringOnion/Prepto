package infs3605.prepto;

/**
 * Created by chenz on 11/10/2017.
 */

public class Question {
    String question, answerA, answerB, answerC, answerD, correctAnswer;
    int quiz;

    public Question() {

    }

    public Question(String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer, int quiz) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.quiz = quiz;
        this.correctAnswer = correctAnswer;
    }

    /*
    protected Question clone (Question question) {
        Question q = new Question();
        q.setCorrectAnswer(question.getCorrectAnswer());
        q.setQuiz(question.getQuiz());
        q.setQuestion(question.getQuestion());
        q.setAnswerA(question.getAnswerA());
        q.setAnswerB(question.getAnswerB());
        q.setAnswerC(question.getAnswerC());
        q.setAnswerD(question.getAnswerD());
        return q;
    }
    */

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getQuiz() {
        return quiz;
    }

    public void setQuiz(int quiz) {
        this.quiz = quiz;
    }
}
