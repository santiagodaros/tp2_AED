package aed;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    private ArrayList<Handle> datos;

    public Heap() {
        datos = new ArrayList<>();
    }

    public Heap(T[] arreglo) {
        datos = new ArrayList<>();
        for (T elem : arreglo) {
            Handle handle = new Handle(elem, datos.size());
            datos.add(handle);
        }
        construirHeap();
    }

    private int padre(int i) { return (i - 1) / 2; }
    private int hijoIzq(int i) { return 2 * i + 1; }
    private int hijoDer(int i) { return 2 * i + 2; }

    public class Handle {
        private T valor;
        private int indice;

        private Handle(T valor, int indice) {
            this.valor = valor;
            this.indice = indice;
        }

        public T getValor() {
            return valor;
        }

        public int getIndice() {
            return indice;
        }
    }

    public Handle agregar(T valor) {
        Handle handle = new Handle(valor, datos.size());
        datos.add(handle);
        heapifyUp(datos.size() - 1);
        return handle;
    }

    public T verMaximo() {
        return datos.isEmpty() ? null : datos.get(0).valor;
    }

    public T sacarMaximo() {
        if (datos.isEmpty()) return null;
        Handle maxHandle = datos.get(0);
        if (datos.size() == 1) {
            datos.remove(0);
            return maxHandle.valor;
        }
        Handle ultimo = datos.remove(datos.size() - 1);
        datos.set(0, ultimo);
        ultimo.indice = 0;
        heapifyDown(0);
        return maxHandle.valor;
    }

    private void heapifyDown(int i) {
        int max = i;
        int izq = hijoIzq(i);
        int der = hijoDer(i);

        if (izq < datos.size() && datos.get(izq).valor.compareTo(datos.get(max).valor) > 0) {
            max = izq;
        }
        if (der < datos.size() && datos.get(der).valor.compareTo(datos.get(max).valor) > 0) {
            max = der;
        }

        if (max != i) {
            swap(i, max);
            heapifyDown(max);
        }
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int p = padre(i);
            if (datos.get(i).valor.compareTo(datos.get(p).valor) > 0) {
                swap(i, p);
                i = p;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Handle temp = datos.get(i);
        datos.set(i, datos.get(j));
        datos.set(j, temp);
        datos.get(i).indice = i;
        datos.get(j).indice = j;
    }

    private void construirHeap() {
        for (int i = padre(datos.size() - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public boolean estaVacio() {
        return datos.isEmpty();
    }

    public int tamaño() {
        return datos.size();
    }

    public void reubicar(Handle h) {
        heapifyUp(h.indice);
        heapifyDown(h.indice);
    }
}