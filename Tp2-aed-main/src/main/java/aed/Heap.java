package aed;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    private ArrayList<Nodo> heap;

    private class Nodo {
        T valor;
        int indice;

        Nodo(T valor, int indice) {
            this.valor = valor;
            this.indice = indice;
        }
    }

    public class Handle {
        private Nodo nodo;

        private Handle(Nodo nodo) {
            this.nodo = nodo;
        }

        public T getValor() {
            return nodo.valor;
        }

        public int getIndice() {
            return nodo.indice;
        }
    }

    public Heap() {
        heap = new ArrayList<>();
    }

    public Heap(T[] arreglo) {
        heap = new ArrayList<>();
        for (T elem : arreglo) {
            Nodo nodo = new Nodo(elem, heap.size());
            heap.add(nodo);
        }
        construirHeap();
    }

    private int padre(int i) { return (i - 1) / 2; }
    private int hijoIzq(int i) { return 2 * i + 1; }
    private int hijoDer(int i) { return 2 * i + 2; }

    public Handle agregar(T valor) {
        Nodo nodo = new Nodo(valor, heap.size());
        heap.add(nodo);
        heapifyUp(heap.size() - 1);
        return new Handle(nodo);
    }

    public T verMaximo() {
        return heap.isEmpty() ? null : heap.get(0).valor;
    }

    public T sacarMaximo() {
        if (heap.isEmpty()) return null;
        Nodo maxNodo = heap.get(0);
        if (heap.size() == 1) {
            heap.remove(0);
            return maxNodo.valor;
        }
        Nodo ultimo = heap.remove(heap.size() - 1);
        heap.set(0, ultimo);
        ultimo.indice = 0;
        heapifyDown(0);
        return maxNodo.valor;
    }

    private void heapifyDown(int i) {
        int max = i;
        int izq = hijoIzq(i);
        int der = hijoDer(i);

        if (izq < heap.size() && heap.get(izq).valor.compareTo(heap.get(max).valor) > 0) {
            max = izq;
        }
        if (der < heap.size() && heap.get(der).valor.compareTo(heap.get(max).valor) > 0) {
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
            if (heap.get(i).valor.compareTo(heap.get(p).valor) > 0) {
                swap(i, p);
                i = p;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Nodo temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        heap.get(i).indice = i;
        heap.get(j).indice = j;
    }

    private void construirHeap() {
        for (int i = padre(heap.size() - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public boolean estaVacio() {
        return heap.isEmpty();
    }

    public int tamaño() {
        return heap.size();
    }

    public void reubicar(Handle h) {
        heapifyUp(h.nodo.indice);
        heapifyDown(h.nodo.indice);
    }
}
