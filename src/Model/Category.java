package Model;

import java.util.ArrayList;

public class Category {

    private String name;
    private ArrayList<Question> lstQuestion;

    public Category(String name, ArrayList<Question> lstQuestion) {
        this.name = name;
        this.lstQuestion = lstQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getLstQuestion() {
        return lstQuestion;
    }

    public void setLstQuestion(ArrayList<Question> lstQuestion) {
        this.lstQuestion = lstQuestion;
    }

}
