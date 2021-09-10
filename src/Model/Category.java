
package Model;

public class Category {
    private String name;
    private Question [] Question;

    public Category(String name, Question[] Question) {
        this.name = name;
        this.Question = Question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question[] getQuestion() {
        return Question;
    }

    public void setQuestion(Question[] Question) {
        this.Question = Question;
    }
    
    
}
