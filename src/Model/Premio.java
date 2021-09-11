package Model;

public class Premio extends Player{
    private int premio;

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
    
}
