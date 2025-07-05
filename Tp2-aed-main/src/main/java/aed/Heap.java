package aed;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

    private ArrayList<T> datos;

    public Heap() {
        datos = new ArrayList<>();
    }

    private int padre(int i) { return (i - 1) / 2; }
    private int hijoIzq(int i) { return 2 * i + 1; }
    private int hijoDer(int i) { return 2 * i + 2; }

    public void agregar(T valor) {
        datos.add(valor);
        heapifyUp(datos.size() - 1);
    }

    public T verMaximo() {
        return datos.isEmpty() ? null : datos.get(0);
    }

    public T sacarMaximo() {
        if (datos.isEmpty()) return null;
        T max = datos.get(0);
        T ultimo = datos.remove(datos.size() - 1);
        if (!datos.isEmpty()) {
            datos.set(0, ultimo);
            heapify(0);
        }
        return max;
    }

    public void heapify(int i) {
        int max = i;
        int izq = hijoIzq(i);
        int der = hijoDer(i);

        if (izq < datos.size() && datos.get(izq).compareTo(datos.get(max)) > 0)
            max = izq;
        if (der < datos.size() && datos.get(der).compareTo(datos.get(max)) > 0)
            max = der;

        if (max != i) {
            swap(i, max);
            heapify(max);
        }
    }

    private void heapifyUp(int i) {
        while (i > 0 && datos.get(i).compareTo(datos.get(padre(i))) > 0) {
            swap(i, padre(i));
            i = padre(i);
        }
    }

    private void swap(int i, int j) {
        T temp = datos.get(i);
        datos.set(i, datos.get(j));
        datos.set(j, temp);
    }

    public boolean estaVacio() {
        return datos.isEmpty();
    }

    public int tamaño() {
        return datos.size();
    }




}
