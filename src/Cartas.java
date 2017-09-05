
public class Cartas {
	private Object naipe;
    private int valor;

    public Cartas(Object naipe, int valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public Object getNaipe() {
        return naipe;
    }

    public int getValor() {
        return valor;
    }

    public void setNaipe(Object naipe) {
        this.naipe = naipe;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
