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

    public Heap(T[] arreglo) { // O(n) //Constructor para transacciones
        heap = new ArrayList<>(); // O(1)
        for (int i=0; i<arreglo.length; i++) { // O(n)
            Nodo nodo = new Nodo(arreglo[i], i); // O (1)
            heap.add(nodo); // O(1)
        }
        construirHeap(); // O(log n) 
    }
    public Heap(T[] arreglo, ArrayList<Heap<T>.Handle> handlearray) { // O(n) //Constructor para usuarios
        heap = new ArrayList<>(); // O(1)
        for (int i=0; i<arreglo.length; i++) { // O(n)
            Nodo nodo = new Nodo(arreglo[i], i); // O (1)
            heap.add(nodo); // O(1)
            handlearray.add(new Handle(nodo)); // O(1)
        }
        construirHeap(); // O(log n) 
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

    public T sacarMaximo() { // O
        if (heap.isEmpty()) return null; // O(1)
        Nodo maxNodo = heap.get(0); // O(1)
        if (heap.size() == 1) { // O(1)
            heap.remove(0); // O(1)
            return maxNodo.valor; // O(1)
        }
        Nodo ultimo = heap.remove(heap.size() - 1); // O(1) porque es el ultimo elemento esto reduce el tamaño del array en 1, si uso remove en otro que no sea el ultimo es O(n)
        heap.set(0, ultimo); // O(1)
        ultimo.indice = 0; // O(1)
        heapifyDown(0); // O(log n)
        return maxNodo.valor;
    }

    private void heapifyDown(int i) { // O(log n)
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

    private void heapifyUp(int i) { // O(log n)
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

    private void construirHeap() { // O(n)
        for (int i = padre(heap.size() - 1); i >= 0; i--) { // O(n)
            heapifyDown(i); // O(log n)
        }
    }

    public boolean estaVacio() { // O(1)
        return heap.isEmpty();
    }

    public int tamaño() { // O(1)
        return heap.size();
    }

    public void reubicar(Handle handle) { // O(log n)
    int i = handle.nodo.indice; // Obtengo el indice
    
    // Veo si tiene que subir
    if (i > 0 && handle.nodo.valor.compareTo(heap.get(padre(i)).valor) > 0) {
        heapifyUp(i);
    } 
    // Si no , baja o se mantiene
    else {
        heapifyDown(i);
    }
}
    }
