package Model;

import java.util.ArrayList;
import java.util.Random;

public class Category{

  
    private String name;
    private int level;
    private ArrayList<Question> lstQuestion;

    public Category(String name, ArrayList<Question> lstQuestion, int level) {
        this.name = name;
        this.level = level;
        this.lstQuestion = lstQuestion;
    }

    public Category(Category category) {
        this.name = category.name;
        this.level = category.level;
        this.lstQuestion = category.lstQuestion;
    }

    public Category() {

    }

    public Category(ArrayList<Question> lstQuestion) {
        this.lstQuestion = lstQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Question> getLstQuestion() {
        return lstQuestion;
    }

    public void setLstQuestion(ArrayList<Question> lstQuestion) {
        this.lstQuestion = lstQuestion;
    }

    public Question randomQuestion() {
        Random r = new Random();
        int NumQuestion = r.nextInt(lstQuestion.size());
        return lstQuestion.get(NumQuestion);
    }

}
