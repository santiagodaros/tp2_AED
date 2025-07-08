package aed;

public class Blockchain {
    ListaEnlazada<Bloque> blockchain;
    Bloque ultimoBloque;

    public Blockchain(){ // O(1)
        blockchain= new ListaEnlazada<>();
        ultimoBloque=null;
    }

    public void agregarBloque(Transaccion[] transacciones){ // O(n)
        blockchain.agregar(new Bloque(transacciones)); // O(n)
        ultimoBloque = blockchain.obtenerUltimo(); // O(1)
    }

    public Bloque ultimoBloque() { // O(1)
        return ultimoBloque;
    }

}
