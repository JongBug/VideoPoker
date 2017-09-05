
public class cartasDaMao {
	private Object naipe;
    private int valor;

    public cartasDaMao(Object naipe, int valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public void setNaipe(Object naipe) {
        this.naipe = naipe;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Object getNaipe() {
        return naipe;
    }

    public int getValor() {
        return valor;
    }

}
