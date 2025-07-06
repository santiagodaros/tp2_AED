package aed;

import java.util.ArrayList;

public class Bloque {
    private int suma = 0;
    private int cantidad = 0;
    private ListaEnlazada<Transaccion> transacciones;
    private Heap<Transaccion> heap;

    public Bloque(Transaccion[] transacciones) {
        this.transacciones = new ListaEnlazada<>();
        ArrayList<Heap<Transaccion>.Handle> heapHandles = new ArrayList<>();
        this.heap = new Heap<>(transacciones,heapHandles);

        for (int i = 0; i < transacciones.length; i++) {
            Transaccion tx = transacciones[i];

            ListaEnlazada<Transaccion>.Handle handleLista = this.transacciones.agregar(tx);
            tx.setHandle(handleLista);

            tx.setHandleHeap(heapHandles.get(i));

            if (!tx.esCreacion()) {
                suma += tx.monto();
                cantidad++;
            }
        }
    }

    public Transaccion txMayorValor() {
        return heap.verMaximo();
    }

    public Transaccion hackearTx() {
        Transaccion tx = heap.sacarMaximo();
        transacciones.eliminar(tx.getHandle());

        if (!tx.esCreacion()) {
            suma -= tx.monto();
            cantidad--;
        }
        return tx;
    }

    public int montoPromedio() {
        if (cantidad == 0) return 0;
        return suma / cantidad;
    }

    public Transaccion[] getSecuenciaTransacciones() {
        Transaccion[] transacciones = new Transaccion[this.transacciones.getLongitud()];
        int i = 0;
        for (Transaccion tx : this.transacciones) {
            transacciones[i] = new Transaccion(tx);
            i++;
        }
        return transacciones;
    }
}