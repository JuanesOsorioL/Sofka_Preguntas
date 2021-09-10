
package Model;

public class Question {
    private String titulo;
    private Option[] Option;

    public Question(String titulo, Option[] Option) {
        this.titulo = titulo;
        this.Option = Option;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Option[] getOption() {
        return Option;
    }

    public void setOption(Option[] Option) {
        this.Option = Option;
    }
    
    
    
}
