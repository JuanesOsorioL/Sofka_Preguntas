package Model;

public class Premio extends Player {

    private int premio;
    private static int INITIAL = 1000000;

    public Premio(int premio, String Nombre) {
        super(Nombre);
        this.premio = premio;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    public int winQuestion(int level) {
        if (level == 1) {
            premio = premio * INITIAL;
        } else {
            premio = premio * level;
        }
        return premio;
    }

}
