package infs3605.prepto;

/**
 * Created by chenz on 14/10/2017.
 */

public class Result {

    int id, quiz, questionID;
    String correctAnswer, result, student, questionText;

    public Result() {
    }

    public Result(int id, int quiz, int questionID, String correctAnswer, String result, String student, String questionText) {
        this.id = id;
        this.quiz = quiz;
        this.questionID = questionID;
        this.correctAnswer = correctAnswer;
        this.result = result;
        this.student = student;
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuiz() {
        return quiz;
    }

    public void setQuiz(int quiz) {
        this.quiz = quiz;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
