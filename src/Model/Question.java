package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Question{

    private String titulo;
    private ArrayList<Option> lstOption;
    private int option;

    public Question(String titulo, ArrayList<Option> lstOption) {
        this.titulo = titulo;
        this.lstOption = lstOption;
    }

    public Question(Question Question) {
        this.titulo = Question.titulo;
        this.lstOption = Question.lstOption;
    }

    public Question() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Option> getLstOption() {
        return lstOption;
    }

    public void setLstOption(ArrayList<Option> lstOption) {
        this.lstOption = lstOption;
    }

    public void randomoptions() {
        Collections.shuffle(lstOption);
        for (int i = 0; i < 4; i++) {
            if (lstOption.get(i).isCorrect()) {
                setOption(i + 1);
            }
            System.out.println(i + 1 + " . " + lstOption.get(i).getAnswer());
        }
    }

    public boolean validate(int num) {
        return getOption() == num;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

}
