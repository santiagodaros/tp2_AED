package aed;


public class Bloque {
    private int suma = 0;
    private int cantidad = 0;
    private ListaEnlazada<Transaccion> transacciones;
    private Heap<Transaccion> heap;

    public Bloque(Transaccion[] transacciones) { //  O(n)
        this.transacciones = new ListaEnlazada<>();
        this.heap = new Heap<>(transacciones); // O(n)

        for (int i = 0; i < transacciones.length; i++) { // O(n)
            Transaccion tx = transacciones[i]; // O(1)
            ListaEnlazada<Transaccion>.Handle handleLista = this.transacciones.agregar(tx); // O(1)
            tx.setHandle(handleLista); // O(1) 

            if (!tx.esCreacion()) { // O(1)
                suma += tx.monto();  // O(1) 
                cantidad++;         // O(1)
            }
        }
    }

    public Transaccion txMayorValor() { // O(1)
        return heap.verMaximo();
    }

    public Transaccion hackearTx() { // O(log n)
        Transaccion tx = heap.sacarMaximo(); // O(log n)
        transacciones.eliminar(tx.getHandle()); // O(1)

        if (!tx.esCreacion()) {
            suma -= tx.monto();
            cantidad--;
        }
        return tx;
    }

    public int montoPromedio() { // O(1)
        if (cantidad == 0) return 0; // O(1)
        return suma / cantidad; // O(1)
    }

    public Transaccion[] getSecuenciaTransacciones() { // O(n)
        Transaccion[] transacciones = new Transaccion[this.transacciones.getLongitud()];
        int i = 0;
        for (Transaccion tx : this.transacciones) { // O(n)
            transacciones[i] = new Transaccion(tx);
            i++;
        }
        return transacciones;
    }
}