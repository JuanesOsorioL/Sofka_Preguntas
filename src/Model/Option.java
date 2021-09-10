
package Model;

public class Option {
    private String respuesta;
    private boolean Correcta;

    public Option(String respuesta, boolean Correcta) {
        this.respuesta = respuesta;
        this.Correcta = Correcta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isCorrecta() {
        return Correcta;
    }

    public void setCorrecta(boolean Correcta) {
        this.Correcta = Correcta;
    }
    
    
}
