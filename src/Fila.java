
public class Fila {
	   
    private Object fila[] = new Object[52];
    private int ini = -1;
    private int fim = -1;

    public void mostraFila() {
        for (int i = ini; i < fim + 1; i++) {
            System.out.print(fila[i] + " ");
        }
    }

    public boolean isEmpty() {
        if (fim == -1 || ini > fim) {
            return true;
        }
        return false;
    }

    public boolean full() {
        if (fim == fila.length - 1) {
            return true;
        }
        return false;
    }

    public void enqueue(Object valor) {
        if (isEmpty()) {
            ini++;
            fim++;
        } else {
            fim++;
        }
        fila[fim] = valor;
    }

    public Object dequeue() {
        if (ini == fim) {
            Object temp = fila[ini];
            ini = -1;
            fim = -1;
            return temp;
        } else if (!isEmpty()) {
            ini++;
            return fila[ini - 1];
        } else {
            return -1;
        }
    }
    public void goEmpyt(){
            ini = -1;
            fim = -1;
    }
    
    

    public Object front() {
        if (isEmpty()) {
            return 0;
        } else {
            return fila[ini];
        }
    }

    public int size() {
        if (isEmpty()) {
            return -1;
        } else {
            return fim;
        }
    }
}
