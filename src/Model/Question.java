package Model;

import java.util.ArrayList;

public class Question {

    private String titulo;
    private ArrayList<Option> lstOption;

    public Question(String titulo, ArrayList<Option> lstOption) {
        this.titulo = titulo;
        this.lstOption = lstOption;
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

}
