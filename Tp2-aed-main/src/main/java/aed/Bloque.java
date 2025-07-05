package aed;

import java.util.ArrayList;

public class Bloque {
    private int suma = 0;
    private int cantidad = 0;
    private ListaEnlazada<Transaccion> transacciones;
    private Heap<Transaccion> heap;

    public Bloque(Transaccion[] transacciones) {
        this.transacciones = new ListaEnlazada<>();
        this.heap = new Heap<>();
        ArrayList<ListaEnlazada<Transaccion>.Handle> handles = new ArrayList<>();
        for (Transaccion tx : transacciones) {
            Transaccion.Handle handle = this.transacciones.agregar(tx);
            tx.setHandle(handle);
            handles.add(handle);

            if (!tx.esCreacion()) {
                suma += tx.monto();
                cantidad++;
            }
        }
        Transaccion[] arregloTx = new Transaccion[handles.size()];
        for (int i = 0; i < handles.size(); i++) {
            arregloTx[i] = handles.get(i).getValor();
        }

        this.heap = new Heap<>(arregloTx);

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